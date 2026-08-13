param(
    [string]$SnapshotsDir = "build/percy-snapshots",
    [switch]$InstallIfMissing
)

function Check-CommandExists($cmd) {
    return (Get-Command $cmd -ErrorAction SilentlyContinue) -ne $null
}

Write-Host "Percy saved-snapshots uploader"
Write-Host "Snapshots dir: $SnapshotsDir"

if (-not (Test-Path $SnapshotsDir)) {
    Write-Error "No snapshots directory found at: $SnapshotsDir. Run tests first or check fallback snapshots path."; exit 1
}

$pngs = Get-ChildItem -Path $SnapshotsDir -Recurse -Filter *.png | Select-Object -ExpandProperty FullName
if (-not $pngs -or $pngs.Count -eq 0) {
    Write-Error "No PNG snapshots found under $SnapshotsDir"; exit 1
}

Write-Host "Found snapshots:"
$pngs | ForEach-Object { Write-Host "  $_" }

# Ensure percy CLI exists (try npx first)
$percyCmd = $null
if (Check-CommandExists 'percy') { $percyCmd = 'percy' }
elseif (Check-CommandExists 'npx') { $percyCmd = 'npx' }

if (-not $percyCmd) {
    Write-Host "Percy CLI and npx not found on PATH."
    if ($InstallIfMissing.IsPresent) {
        if (Check-CommandExists 'npm') {
            Write-Host "Attempting to install @percy/cli globally via npm..."
            npm install -g @percy/cli
            if (Check-CommandExists 'percy') { $percyCmd = 'percy' }
        } else {
            Write-Error "npm not found; please install Node.js/npm or install @percy/cli on another machine and upload from there."; exit 1
        }
    } else {
        Write-Error "Percy CLI (percy) or npx not found. Re-run with -InstallIfMissing to attempt automatic install (requires npm)."; exit 1
    }
}

# If we have npx but not percy, we'll use npx @percy/cli upload (npx will fetch it if needed)
if ($percyCmd -eq 'npx') {
    Write-Host "Using npx to run @percy/cli. This may download @percy/cli if it is not installed locally."
    # Recommended invocation: npx percy upload <dir>
    $args = @('percy', 'upload', $SnapshotsDir)
    Write-Host "Running: npx $($args -join ' ')"
    $proc = Start-Process -FilePath npx -ArgumentList $args -NoNewWindow -Wait -PassThru
    if ($proc.ExitCode -ne 0) { Write-Error "npx percy upload failed with exit code $($proc.ExitCode). Check output above."; exit $proc.ExitCode }
    Write-Host "percy upload completed (exit code $($proc.ExitCode))."
    exit 0
}

# If percy exists natively, attempt percy upload
if ($percyCmd -eq 'percy') {
    Write-Host "Using global percy CLI to upload snapshots."
    $args = @('upload', $SnapshotsDir)
    Write-Host "Running: percy $($args -join ' ')"
    $proc = Start-Process -FilePath percy -ArgumentList $args -NoNewWindow -Wait -PassThru
    if ($proc.ExitCode -ne 0) { Write-Error "percy upload failed with exit code $($proc.ExitCode). Check output above."; exit $proc.ExitCode }
    Write-Host "percy upload completed (exit code $($proc.ExitCode))."
    exit 0
}

Write-Error "Unhandled state: no percy command executed."; exit 1


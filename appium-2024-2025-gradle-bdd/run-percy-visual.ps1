param(
    [switch]$UseBrowserStack,
    [string]$PercyToken,
    [string]$PercyBuildName,
    [string]$BsUser,
    [string]$BsKey,
    [string]$BsApp,
    [string]$BsDevice,
    [string]$BsOsVersion,
    [string]$BsProject,
    [string]$BsBuild,
    [string]$BsSessionName
)

# Wrapper to run visual tests under Percy agent. It will attempt to install Percy CLI if Node is available
# This version includes a preflight check that prints environment information and versions.
# Usage:
#  .\run-percy-visual.ps1 -UseBrowserStack -PercyToken "<token>" -PercyBuildName "Visual Testing" -BsUser "..." -BsKey "..." -BsApp "bs://..."

function Check-CommandExists {
    param([string]$cmd)
    $which = Get-Command $cmd -ErrorAction SilentlyContinue
    return $which -ne $null
}

function Print-Version {
    param([string]$cmd, [string]$args)
    try {
        $out = & $cmd $args 2>&1
        if ($LASTEXITCODE -ne 0 -and $out -eq $null) { Write-Host "$cmd: (no output, exit $LASTEXITCODE)"; return }
        Write-Host "$cmd: $out"
    } catch {
        Write-Host "$cmd: (error invoking)": $_
    }
}

Write-Host "----- Percy Preflight Check -----"
Write-Host "OS: $([System.Environment]::OSVersion)"
Write-Host "PowerShell version: $($PSVersionTable.PSVersion)"

# Print Node/NPM/Percy info if present
if (Check-CommandExists -cmd 'node') {
    Print-Version node "--version"
} else { Write-Host "node: missing" }

if (Check-CommandExists -cmd 'npm') {
    Print-Version npm "--version"
} else { Write-Host "npm: missing" }

if (Check-CommandExists -cmd 'percy') {
    Print-Version percy "--version"
} else { Write-Host "percy: missing" }

Write-Host "--------------------------------"

# Prepare env vars
if ($PercyToken) { $env:PERCY_TOKEN = $PercyToken; Write-Host "PERCY_TOKEN set for session" }
if ($PercyBuildName) { $env:PERCY_BUILD_NAME = $PercyBuildName; $env:PERCY_BUILD = $PercyBuildName; Write-Host "PERCY_BUILD_NAME=$PercyBuildName" }
if ($UseBrowserStack) {
    if ($BsUser) { $env:BROWSERSTACK_USERNAME = $BsUser }
    if ($BsKey) { $env:BROWSERSTACK_ACCESS_KEY = $BsKey }
    if ($BsApp) { $env:BROWSERSTACK_APP_ID = $BsApp }
    if ($BsDevice) { $env:BROWSERSTACK_DEVICE = $BsDevice }
    if ($BsOsVersion) { $env:BROWSERSTACK_OS_VERSION = $BsOsVersion }
    if ($BsProject) { $env:BROWSERSTACK_PROJECT = $BsProject }
    if ($BsBuild) { $env:BROWSERSTACK_BUILD = $BsBuild }
    if ($BsSessionName) { $env:BROWSERSTACK_SESSION_NAME = $BsSessionName }
}

# Ensure Percy CLI exists; try to install automatically if node is available
if (-not (Check-CommandExists -cmd 'percy')) {
    Write-Host "Percy CLI not found on PATH."
    if (Check-CommandExists -cmd 'node' -and Check-CommandExists -cmd 'npm') {
        Write-Host "Node/npm detected — attempting to install @percy/cli globally (may require elevation)..."
        try {
            npm install -g @percy/cli
            if (-not (Check-CommandExists -cmd 'percy')) {
                Write-Error "Percy CLI installation failed or percy is not in PATH. Please install manually: npm install -g @percy/cli"; exit 1
            } else { Write-Host "Percy CLI installed successfully." }
        } catch {
            Write-Error "Automatic Percy install failed: $_. Please run: npm install -g @percy/cli"; exit 1
        }
    } else {
        Write-Error "Node/npm not found. Install Node.js and then run: npm install -g @percy/cli"; exit 1
    }
} else {
    Write-Host "Percy CLI is available. Good to go."
}

# Verify required env vars for BrowserStack if used
if ($UseBrowserStack) {
    if (-not $env:BROWSERSTACK_USERNAME -or -not $env:BROWSERSTACK_ACCESS_KEY) {
        Write-Error "BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY must be set for BrowserStack runs."; exit 1
    }
}

if (-not $env:PERCY_TOKEN) {
    Write-Warning "PERCY_TOKEN is not set. Percy will not be able to create builds.";
}

# Run the tests under Percy agent
Write-Host "Starting Percy agent and running tests..."
percy exec -- .\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"

Write-Host "Done. Check Percy dashboard for build: $($env:PERCY_BUILD_NAME) or default naming. If you don't see a build, check the console output for agent logs and network errors."

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

# Example runner script for PowerShell to run visual tests locally or on BrowserStack.
# Usage examples:
#   .\run-visual.ps1 -PercyToken "<token>"
#   .\run-visual.ps1 -UseBrowserStack -BsUser "<user>" -BsKey "<key>" -BsApp "bs://<app-id>" -PercyToken "<token>"

if ($PercyToken) {
    $env:PERCY_TOKEN = $PercyToken
    Write-Host "PERCY_TOKEN set for this session"
}

if ($PercyBuildName) {
    $env:PERCY_BUILD = $PercyBuildName
    $env:PERCY_BUILD_NAME = $PercyBuildName
    Write-Host "PERCY_BUILD_NAME set to: $PercyBuildName"
}

if ($UseBrowserStack) {
    if ($BsUser) { $env:BROWSERSTACK_USERNAME = $BsUser }
    if ($BsKey) { $env:BROWSERSTACK_ACCESS_KEY = $BsKey }
    if ($BsApp) { $env:BROWSERSTACK_APP_ID = $BsApp }
    if ($BsDevice) { $env:BROWSERSTACK_DEVICE = $BsDevice }
    if ($BsOsVersion) { $env:BROWSERSTACK_OS_VERSION = $BsOsVersion }
    if ($BsProject) { $env:BROWSERSTACK_PROJECT = $BsProject }
    if ($BsBuild) { $env:BROWSERSTACK_BUILD = $BsBuild }
    if ($BsSessionName) { $env:BROWSERSTACK_SESSION_NAME = $BsSessionName }

    if (-not $env:BROWSERSTACK_USERNAME -or -not $env:BROWSERSTACK_ACCESS_KEY) {
        Write-Error "BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY must be provided as parameters or environment variables for BrowserStack runs."; exit 1
    }
    Write-Host "Running tests on BrowserStack with device: $($env:BROWSERSTACK_DEVICE)"
} else {
    Write-Host "Running tests locally (Appium). Ensure Appium server is running if using local device/emulator."
}

if (-not $env:PERCY_TOKEN) {
    Write-Warning "PERCY_TOKEN is not set. Percy SDK may not upload snapshots. The fallback will save images to build/percy-snapshots/.";
}

# Run the Cucumber runner via Gradle
.\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"

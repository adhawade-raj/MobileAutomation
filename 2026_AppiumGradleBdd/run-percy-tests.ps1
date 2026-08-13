#!/usr/bin/env powershell

# Percy Visual Test Runner Script for Windows PowerShell
# This script sets up the environment and runs Appium tests with Percy

param(
    [string]$PercyToken = $env:PERCY_TOKEN,
    [string]$PercyBuildName = $env:PERCY_BUILD_NAME,
    [string]$AppiumUrl = $env:APPIUM_URL,
    [switch]$Help
)

if ($Help) {
    Write-Host @"
Percy Visual Test Runner
Usage: .\run-percy-tests.ps1 [options]

Options:
  -PercyToken <token>        Percy authentication token (or set PERCY_TOKEN env var)
  -PercyBuildName <name>     Percy build name (or set PERCY_BUILD_NAME env var)
  -AppiumUrl <url>           Appium server URL (default: http://127.0.0.1:4723/wd/hub)
  -Help                      Show this help message

Examples:
  # Run with Percy token
  .\run-percy-tests.ps1 -PercyToken "your_token_here"

  # Run with environment variables
  `$env:PERCY_TOKEN = "your_token_here"; .\run-percy-tests.ps1

  # Run with local Appium server
  .\run-percy-tests.ps1 -PercyToken "your_token_here" -AppiumUrl "http://127.0.0.1:4723/wd/hub"

  # For BrowserStack:
  `$env:BROWSERSTACK_USERNAME = "bs_user"; `$env:BROWSERSTACK_ACCESS_KEY = "bs_key"; `$env:PERCY_TOKEN = "your_token_here"; .\run-percy-tests.ps1
"@
    exit 0
}

Write-Host ""
Write-Host "================================"
Write-Host "  Percy Visual Test Runner"
Write-Host "================================"
Write-Host ""

# Validate Percy token
if (-not $PercyToken -or $PercyToken -eq "") {
    Write-Host "Error: PERCY_TOKEN not provided"
    Write-Host ""
    Write-Host "Set your Percy token using one of these methods:"
    Write-Host "  1. Pass as parameter: -PercyToken 'your_token'"
    Write-Host "  2. Set environment variable: `$env:PERCY_TOKEN = 'your_token'"
    Write-Host ""
    Write-Host "Get your token from: https://percy.io/builds"
    Write-Host ""
    exit 1
}

# Set environment variables
$env:PERCY_TOKEN = $PercyToken
if ($PercyBuildName) {
    $env:PERCY_BUILD_NAME = $PercyBuildName
}
else {
    $env:PERCY_BUILD_NAME = "Visual Test Build - $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
}

if ($AppiumUrl) {
    $env:APPIUM_URL = $AppiumUrl
}
else {
    $env:APPIUM_URL = "http://127.0.0.1:4723/wd/hub"
}

Write-Host "Environment Configuration:"
Write-Host "  PERCY_TOKEN: $(if ($PercyToken.Length -gt 8) { $PercyToken.Substring(0, 4) + '...' + $PercyToken.Substring($PercyToken.Length - 4) } else { '****' })"
Write-Host "  PERCY_BUILD_NAME: $($env:PERCY_BUILD_NAME)"
Write-Host "  APPIUM_URL: $($env:APPIUM_URL)"
Write-Host ""

# Check if npm/npx is available
Write-Host "Checking for npm/npx..."
$npmCheck = npm -v 2>$null
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error: npm is not installed or not in PATH"
    Write-Host "Please install Node.js from https://nodejs.org/"
    exit 1
}
Write-Host "npm version: $npmCheck"
Write-Host ""

# Check if gradlew exists
if (-not (Test-Path ".\gradlew.bat")) {
    Write-Host "Error: gradlew.bat not found in current directory"
    Write-Host "Make sure you're in the project root directory"
    exit 1
}

Write-Host "Starting Percy Test Execution..."
Write-Host "================================"
Write-Host ""

# Run the Percy test task
Write-Host "Running: .\gradlew.bat percyTest --info"
Write-Host ""

& ".\gradlew.bat" percyTest --info

$exitCode = $LASTEXITCODE

Write-Host ""
Write-Host "================================"
if ($exitCode -eq 0) {
    Write-Host "Percy tests completed successfully!"
    Write-Host ""
    Write-Host "Snapshots saved to: build/percy-snapshots/"
    Write-Host "Check Percy dashboard: https://percy.io/builds"
}
else {
    Write-Host "Percy tests failed with exit code: $exitCode"
    Write-Host ""
    Write-Host "Troubleshooting:"
    Write-Host "  1. Check PERCY_TOKEN is valid"
    Write-Host "  2. Ensure Appium server is running (for local tests)"
    Write-Host "  3. Check build/percy-snapshots/ for saved screenshots"
}
Write-Host "================================"
Write-Host ""

exit $exitCode


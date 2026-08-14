#!/usr/bin/env powershell

# Quick Build Test Script
# This script compiles the project to ensure all dependencies are correct

Write-Host ""
Write-Host "================================"
Write-Host "  Percy Project Build Test"
Write-Host "================================"
Write-Host ""

if (-not (Test-Path ".\gradlew.bat")) {
    Write-Host "❌ Error: gradlew.bat not found" -ForegroundColor Red
    exit 1
}

Write-Host "Compiling project..."
Write-Host ""

# Run gradle build
.\gradlew.bat build --info 2>&1 | Tee-Object -Variable buildOutput | ForEach-Object {
    Write-Host $_
}

$buildExitCode = $LASTEXITCODE

Write-Host ""
Write-Host "================================"
if ($buildExitCode -eq 0) {
    Write-Host "✓ Build successful!" -ForegroundColor Green
    Write-Host ""
    Write-Host "Project is ready for testing."
    Write-Host ""
    Write-Host "To run Percy visual tests:"
    Write-Host "  1. Set your Percy token:"
    Write-Host "     `$env:PERCY_TOKEN = 'your_token_here'" -ForegroundColor Yellow
    Write-Host "  2. Start Appium server (in another terminal):"
    Write-Host "     appium" -ForegroundColor Yellow
    Write-Host "  3. Run the tests:"
    Write-Host "     .\run-percy-tests.ps1" -ForegroundColor Yellow
} else {
    Write-Host "❌ Build failed with exit code: $buildExitCode" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please check the output above for compilation errors."
}
Write-Host "================================"
Write-Host ""

exit $buildExitCode


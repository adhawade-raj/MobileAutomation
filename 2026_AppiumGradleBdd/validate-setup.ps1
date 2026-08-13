#!/usr/bin/env powershell

# Percy Setup Validation Script
# This script checks if all prerequisites are installed and configured

Write-Host ""
Write-Host "================================"
Write-Host "  Percy Setup Validator"
Write-Host "================================"
Write-Host ""

$checks = @()
$allPassed = $true

# Function to add check result
function Add-Check($name, $passed, $message) {
    global:$allPassed = $global:allPassed -and $passed
    $icon = if ($passed) { "✓" } else { "✗" }
    $color = if ($passed) { "Green" } else { "Red" }
    Write-Host "$icon $name" -ForegroundColor $color
    if ($message) {
        Write-Host "  → $message" -ForegroundColor Gray
    }
}

Write-Host "Checking Prerequisites..."
Write-Host ""

# Check Node.js
$nodeCheck = node -v 2>$null
$nodePassed = $LASTEXITCODE -eq 0
Add-Check "Node.js" $nodePassed $(if ($nodePassed) { "Version: $nodeCheck" } else { "Install from https://nodejs.org/" })

# Check npm
$npmCheck = npm -v 2>$null
$npmPassed = $LASTEXITCODE -eq 0
Add-Check "npm" $npmPassed $(if ($npmPassed) { "Version: $npmCheck" } else { "Install Node.js" })

# Check Java
$javaCheck = java -version 2>&1 | Select-Object -First 1
$javaPassed = $LASTEXITCODE -eq 0
Add-Check "Java" $javaPassed $(if ($javaPassed) { "Version: $javaCheck" } else { "Install Java JDK" })

# Check gradle wrapper
$gradlewExists = Test-Path ".\gradlew.bat"
Add-Check "Gradle Wrapper" $gradlewExists $(if ($gradlewExists) { "File: .\gradlew.bat" } else { "gradlew.bat not found" })

Write-Host ""
Write-Host "Checking Project Files..."
Write-Host ""

# Check build.gradle
$buildGradleExists = Test-Path ".\build.gradle"
Add-Check "build.gradle" $buildGradleExists

# Check package.json
$packageJsonExists = Test-Path ".\package.json"
Add-Check "package.json" $packageJsonExists $(if ($packageJsonExists) { "Percy CLI configured" } else { "Will be created on first run" })

# Check .percy.yml
$percyYmlExists = Test-Path ".\.percy.yml"
Add-Check ".percy.yml" $percyYmlExists $(if ($percyYmlExists) { "Percy configuration present" } else { "Will be used by Percy CLI" })

# Check test files
$testFeatureExists = Test-Path ".\src\test\resources\features\visual.feature"
Add-Check "Test Feature File" $testFeatureExists "visual.feature"

$testHooksExists = Test-Path ".\src\test\java\org\example\hooks\TestHooks.java"
Add-Check "Test Hooks" $testHooksExists "Appium driver setup"

$percyHelperExists = Test-Path ".\src\test\java\org\example\percys\PercyHelper.java"
Add-Check "Percy Helper" $percyHelperExists "Screenshot capture"

$visualStepsExists = Test-Path ".\src\test\java\org\example\steps\VisualSteps.java"
Add-Check "Visual Steps" $visualStepsExists "Test steps"

Write-Host ""
Write-Host "Checking Environment Variables..."
Write-Host ""

$percyToken = $env:PERCY_TOKEN
$percyTokenSet = -not [string]::IsNullOrEmpty($percyToken)
Add-Check "PERCY_TOKEN" $percyTokenSet $(if ($percyTokenSet) { "Token is set" } else { "⚠ Required for upload. Set with: `$env:PERCY_TOKEN = 'your_token'" })

$percyBuild = $env:PERCY_BUILD_NAME
$percyBuildSet = -not [string]::IsNullOrEmpty($percyBuild)
Add-Check "PERCY_BUILD_NAME" $percyBuildSet $(if ($percyBuildSet) { "Build name: $percyBuild" } else { "Optional" })

$appiumUrl = $env:APPIUM_URL
Add-Check "APPIUM_URL" $true $(if ($appiumUrl) { "URL: $appiumUrl" } else { "Will use default: http://127.0.0.1:4723/wd/hub" })

Write-Host ""
Write-Host "Checking Build Dependencies..."
Write-Host ""

if (Test-Path ".\build.gradle") {
    $buildGradleContent = Get-Content ".\build.gradle" -Raw
    $percyDependency = $buildGradleContent -match "percy-java-selenium"
    Add-Check "Percy Java SDK" $percyDependency "percy-java-selenium in dependencies"

    $cucumberDependency = $buildGradleContent -match "cucumber-java"
    Add-Check "Cucumber" $cucumberDependency "cucumber-java in dependencies"

    $appiumDependency = $buildGradleContent -match "java-client"
    Add-Check "Appium Java Client" $appiumDependency "io.appium:java-client in dependencies"
}

Write-Host ""
Write-Host "Quick Command Reference..."
Write-Host ""

Write-Host "To run Percy tests:" -ForegroundColor Cyan
Write-Host "  `$env:PERCY_TOKEN = 'your_token_here'" -ForegroundColor Yellow
Write-Host "  .\run-percy-tests.ps1" -ForegroundColor Yellow
Write-Host ""

Write-Host "To set up Appium for local testing:" -ForegroundColor Cyan
Write-Host "  appium" -ForegroundColor Yellow
Write-Host ""

Write-Host "To start Android emulator:" -ForegroundColor Cyan
Write-Host "  emulator -avd <device_name>" -ForegroundColor Yellow
Write-Host ""

Write-Host "To check connected devices:" -ForegroundColor Cyan
Write-Host "  adb devices" -ForegroundColor Yellow
Write-Host ""

Write-Host "================================"
if ($allPassed) {
    Write-Host "✓ All checks passed! Ready to run tests." -ForegroundColor Green
} else {
    Write-Host "✗ Some checks failed. See above for details." -ForegroundColor Red
}
Write-Host "================================"
Write-Host ""

if ($percyTokenSet) {
    Write-Host "Next step: Run visual tests with:" -ForegroundColor Green
    Write-Host "  .\run-percy-tests.ps1" -ForegroundColor Yellow
} else {
    Write-Host "Next step: Set your Percy token and run:" -ForegroundColor Green
    Write-Host "  `$env:PERCY_TOKEN = 'your_token_here'" -ForegroundColor Yellow
    Write-Host "  .\run-percy-tests.ps1" -ForegroundColor Yellow
}

Write-Host ""


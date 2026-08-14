# Percy Testing - Copy & Paste Commands

This file contains ready-to-use commands for running Percy visual tests.

## 🎯 Start Here - Basic Setup (3 Commands)

### Command 1: Get Your Percy Token
Go to: https://percy.io/builds
- Login or create account
- Select/create a project
- Copy the **Project Token**

### Command 2: Set Environment Variables
```powershell
$env:PERCY_TOKEN = "paste_your_token_here"
$env:PERCY_BUILD_NAME = "Visual Testing"
```

### Command 3: Run Tests
```powershell
.\run-percy-tests.ps1
```

---

## 🚀 Complete Examples

### Local Appium Testing
```powershell
# Terminal 1: Start Appium server
appium

# Terminal 2: Run Percy tests
$env:PERCY_TOKEN = "your_token_here"
.\run-percy-tests.ps1
```

### BrowserStack Testing
```powershell
$env:BROWSERSTACK_USERNAME = "your_bs_username"
$env:BROWSERSTACK_ACCESS_KEY = "your_bs_access_key"
$env:BROWSERSTACK_APP_ID = "bs://your_app_id"
$env:PERCY_TOKEN = "your_percy_token"
$env:PERCY_BUILD_NAME = "BrowserStack Visual Test"

.\run-percy-tests.ps1
```

### Custom Appium URL
```powershell
$env:PERCY_TOKEN = "your_token"
$env:APPIUM_URL = "http://192.168.1.100:4723/wd/hub"
.\run-percy-tests.ps1
```

### Custom Device
```powershell
$env:PERCY_TOKEN = "your_token"
$env:DEVICE_NAME = "Pixel_6_API_31"
.\run-percy-tests.ps1
```

### Custom App Path
```powershell
$env:PERCY_TOKEN = "your_token"
$env:APP_PATH = "C:\path\to\your\app.apk"
.\run-percy-tests.ps1
```

---

## 🔧 Pre-Flight Checks

### Validate Your Setup
```powershell
.\validate-setup.ps1
```

### Test Project Compilation
```powershell
.\test-build.ps1
```

### Check Connected Devices
```powershell
adb devices
```

### Start Android Emulator
```powershell
emulator -avd emulator-5554
```

### Start Appium Server
```powershell
appium
```

---

## 📊 Run Specific Tests

### Run Only Visual Tests
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"
```

### Run with Debug Output
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --debug --info --stacktrace
```

### Dry Run (Show Snapshots Without Uploading)
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --dry-run
```

---

## 🛠️ Gradle Tasks

### Build Project
```powershell
.\gradlew.bat build
```

### Clean Build
```powershell
.\gradlew.bat clean
```

### Run All Tests
```powershell
.\gradlew.bat test
```

### Percy Test Task
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --info
```

---

## 📸 View Results

### Local Screenshots
```powershell
# List saved screenshots
ls build/percy-snapshots/

# Open directory
explorer build/percy-snapshots/
```

### Percy Dashboard
```
https://percy.io/builds
```

### Test Reports
```powershell
# Open HTML report
start build/reports/tests/test/index.html
```

---

## 🐛 Troubleshooting Commands

### Check If Percy Server Is Running
```powershell
curl http://localhost:5338/healthcheck
```

### List Android Devices
```powershell
adb devices
```

### Unlock Android Emulator
```powershell
adb shell input keyevent 82
```

### Clear Gradle Cache
```powershell
.\gradlew.bat clean
```

### Reinstall Dependencies
```powershell
rm -r node_modules
npm install
.\gradlew.bat clean build
```

### View Detailed Error Logs
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --stacktrace -d
```

---

## 🔄 Multi-Step Workflow Example

```powershell
# Step 1: Validate setup
Write-Host "Validating setup..."
.\validate-setup.ps1

# Step 2: Clean previous build
Write-Host "Cleaning build..."
.\gradlew.bat clean

# Step 3: Check devices
Write-Host "Checking Android devices..."
adb devices

# Step 4: Set environment
Write-Host "Setting environment..."
$env:PERCY_TOKEN = "your_token_here"
$env:PERCY_BUILD_NAME = "Full Workflow Test - $(Get-Date -Format 'yyyy-MM-dd HH:mm')"

# Step 5: Run tests
Write-Host "Running Percy tests..."
.\run-percy-tests.ps1

# Step 6: Check results
Write-Host "Results saved to: build/percy-snapshots/"
ls build/percy-snapshots/
```

---

## 🎯 One-Liner Commands

### Quick Test (All-in-One)
```powershell
$env:PERCY_TOKEN = "your_token"; .\run-percy-tests.ps1
```

### BrowserStack One-Liner
```powershell
$env:BROWSERSTACK_USERNAME = "user"; $env:BROWSERSTACK_ACCESS_KEY = "key"; $env:BROWSERSTACK_APP_ID = "bs://app"; $env:PERCY_TOKEN = "token"; .\run-percy-tests.ps1
```

### Validate Then Run
```powershell
.\validate-setup.ps1; if ($?) { $env:PERCY_TOKEN = "your_token"; .\run-percy-tests.ps1 }
```

---

## 📝 Environment Variable Cheat Sheet

```powershell
# Minimal Setup
$env:PERCY_TOKEN = "your_token_here"

# Full Setup (Local)
$env:PERCY_TOKEN = "your_token"
$env:PERCY_BUILD_NAME = "My Build"
$env:APPIUM_URL = "http://127.0.0.1:4723/wd/hub"
$env:DEVICE_NAME = "emulator-5554"

# Full Setup (BrowserStack)
$env:PERCY_TOKEN = "your_token"
$env:PERCY_BUILD_NAME = "BrowserStack Build"
$env:BROWSERSTACK_USERNAME = "username"
$env:BROWSERSTACK_ACCESS_KEY = "key"
$env:BROWSERSTACK_APP_ID = "bs://app_id"
$env:BROWSERSTACK_DEVICE = "Google Pixel 6"
$env:BROWSERSTACK_OS_VERSION = "12"
```

---

## 🚦 Common Workflow Scenarios

### Scenario 1: First Time Setup
```powershell
# 1. Validate
.\validate-setup.ps1

# 2. Get token from https://percy.io/builds

# 3. Set token
$env:PERCY_TOKEN = "your_token_from_step_2"

# 4. Start Appium (new terminal)
appium

# 5. Run tests (original terminal)
.\run-percy-tests.ps1

# 6. View results at https://percy.io/builds
```

### Scenario 2: Development Iteration
```powershell
# 1. Set token once
$env:PERCY_TOKEN = "your_token"

# 2. Make code changes
# (edit your test scenarios)

# 3. Re-run tests
.\run-percy-tests.ps1

# 4. Check results
ls build/percy-snapshots/
```

### Scenario 3: CI/CD Integration
```powershell
# In CI environment, set these as secrets/variables:
# PERCY_TOKEN = "your_token"
# BROWSERSTACK_USERNAME = "bs_user"
# BROWSERSTACK_ACCESS_KEY = "bs_key"

# Then run:
$env:PERCY_TOKEN = $env:PERCY_TOKEN
.\gradlew.bat percyTest --info
```

---

## 💡 Pro Tips

### Tip 1: Save Commands in a Script
Create `my-test-run.ps1`:
```powershell
$env:PERCY_TOKEN = "your_token"
$env:PERCY_BUILD_NAME = "Daily Run - $(Get-Date -Format 'yyyy-MM-dd')"
.\run-percy-tests.ps1
```

Then run: `.\my-test-run.ps1`

### Tip 2: Multiple Test Runs
```powershell
$env:PERCY_TOKEN = "your_token"

for ($i = 1; $i -le 3; $i++) {
    Write-Host "Run $i of 3"
    .\run-percy-tests.ps1
    Start-Sleep -Seconds 10
}
```

### Tip 3: Conditional Runs
```powershell
.\validate-setup.ps1
if ($?) {
    Write-Host "Setup valid, running tests..."
    $env:PERCY_TOKEN = "your_token"
    .\run-percy-tests.ps1
} else {
    Write-Host "Setup validation failed, fix issues above first"
}
```

### Tip 4: Archive Results
```powershell
$timestamp = Get-Date -Format "yyyy-MM-dd_HHmmss"
Copy-Item build/percy-snapshots -Destination "percy-results-$timestamp" -Recurse
```

---

## 📞 Getting Help

### Check Percy Docs
```powershell
start https://docs.percy.io/
```

### Check Appium Docs
```powershell
start https://appium.io/docs/
```

### View Test Output
```powershell
start build/reports/tests/test/index.html
```

### Check Percy Dashboard
```powershell
start https://percy.io/builds
```

---

**Ready to run Percy tests?** Start with:
```powershell
$env:PERCY_TOKEN = "your_token_here"
.\run-percy-tests.ps1
```


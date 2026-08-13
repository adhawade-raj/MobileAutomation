# Percy Visual Testing - Setup Complete ✓

Your Appium + Percy visual testing project is now fully configured and ready to run!

## 📋 What Was Done

### 1. **Enhanced PercyHelper.java**
   - ✓ Improved screenshot capture with better error handling
   - ✓ Added local screenshot persistence as fallback
   - ✓ Enhanced logging with visual indicators (✓, ✗)
   - ✓ Proper directory creation and file management
   - ✓ Works with or without PERCY_TOKEN set

### 2. **Improved Visual Steps & Hooks**
   - ✓ Added detailed logging throughout test execution
   - ✓ Better error messages for troubleshooting
   - ✓ Clear output showing test progress
   - ✓ Automatic driver initialization and cleanup

### 3. **New Scripts & Configuration**
   - ✓ `run-percy-tests.ps1` - Easy-to-use test runner with validation
   - ✓ `validate-setup.ps1` - Pre-flight checks for your environment
   - ✓ `.percy.yml` - Percy configuration for app testing
   - ✓ Enhanced Gradle configuration with better logging

### 4. **Documentation**
   - ✓ `PERCY_QUICK_START.md` - Complete guide with examples
   - ✓ This setup summary

## 🚀 Quick Start

### **Run Tests in 3 Steps**

```powershell
# Step 1: Set your Percy token (get from percy.io dashboard)
$env:PERCY_TOKEN = "your_percy_token_here"

# Step 2: Start Appium server (in another terminal)
appium

# Step 3: Run the tests
.\run-percy-tests.ps1
```

## 📸 What Happens During Test Run

1. **Setup Phase**
   - Driver is initialized
   - Appium connects to device/emulator
   - App is launched

2. **Test Phase**
   - Your feature file scenarios run
   - Screenshots are captured
   - Screenshots are saved locally to `build/percy-snapshots/`
   - Screenshots are uploaded to Percy (if PERCY_TOKEN is set)

3. **Teardown Phase**
   - Driver is closed gracefully
   - Test results are reported

## 📂 Output Locations

- **Screenshots**: `build/percy-snapshots/`
- **Test Reports**: `build/reports/`
- **Percy Results**: https://percy.io/builds (after upload)

## 🔍 Troubleshooting

### **Check Setup**
```powershell
.\validate-setup.ps1
```

### **Run with Debug Info**
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --debug --stacktrace
```

### **Just Save Screenshots Locally (No Upload)**
- Simply don't set `PERCY_TOKEN`
- Screenshots will still be saved to `build/percy-snapshots/`
- You can upload them manually later

## 📝 Test Feature File

Your tests are defined in: `src/test/resources/features/visual.feature`

```gherkin
Feature: Visual snapshot

  @visual
  Scenario: Open app and take snapshot
    Given the app is launched
    When I take a Percy snapshot "Home Screen"
    Then the test completes
```

## 🔧 For BrowserStack Users

```powershell
# Set BrowserStack credentials
$env:BROWSERSTACK_USERNAME = "your_username"
$env:BROWSERSTACK_ACCESS_KEY = "your_key"
$env:BROWSERSTACK_APP_ID = "bs://your_app_id"
$env:PERCY_TOKEN = "your_percy_token"

# Run tests
.\run-percy-tests.ps1
```

## 📦 Gradle Tasks

| Task | Command | Purpose |
|------|---------|---------|
| `percyTest` | `.\gradlew.bat percyTest` | Run tests with Percy (Recommended) |
| `test` | `.\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"` | Run tests directly |
| `build` | `.\gradlew.bat build` | Build project |
| `clean` | `.\gradlew.bat clean` | Clean build files |

## 🎯 Next Steps

1. ✓ **Validate your setup**
   ```powershell
   .\validate-setup.ps1
   ```

2. ✓ **Get your Percy token**
   - Visit https://percy.io/builds
   - Create/select a project
   - Copy the project token

3. ✓ **Start Appium server** (if using local)
   ```powershell
   appium
   ```

4. ✓ **Run your first test**
   ```powershell
   $env:PERCY_TOKEN = "your_token_here"
   .\run-percy-tests.ps1
   ```

5. ✓ **Check results on Percy dashboard**
   - Visit https://percy.io/builds
   - View the build you just created
   - See visual comparisons

## 💡 Key Features

- ✓ **Automatic screenshot capture** from Appium driver
- ✓ **Local screenshot persistence** as fallback
- ✓ **Percy CLI integration** for reliable uploading
- ✓ **Detailed logging** throughout execution
- ✓ **Works with or without Percy token** (saves locally if not set)
- ✓ **BrowserStack compatible** for remote testing
- ✓ **Error recovery** with helpful messages

## 📚 Learn More

- **Percy Docs**: https://docs.percy.io/
- **Appium Docs**: https://appium.io/docs/
- **Cucumber Docs**: https://cucumber.io/docs/

## ✅ Verification Checklist

Before running tests, verify:
- [ ] Node.js & npm installed
- [ ] Java JDK installed
- [ ] Gradle wrapper available
- [ ] `build.gradle` has Percy dependencies
- [ ] `.percy.yml` is present
- [ ] Test feature file exists
- [ ] Percy token available (optional but recommended)

## 🎉 You're All Set!

Your Percy + Appium visual testing project is ready to go. Start with:

```powershell
$env:PERCY_TOKEN = "your_token_here"
.\run-percy-tests.ps1
```

Happy visual testing! 📸

---

For detailed instructions, see: `PERCY_QUICK_START.md`
For setup validation, run: `.\validate-setup.ps1`


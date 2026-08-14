# Percy Visual Testing Setup - Quick Start Guide

This guide explains how to run Percy visual tests with your Appium project.

## Prerequisites

1. **Node.js & npm** - [Download from nodejs.org](https://nodejs.org/)
2. **Android SDK/Emulator** - For local Appium testing
3. **Appium Server** - Running on `http://127.0.0.1:4723/wd/hub` (for local tests)
4. **Percy Account** - [Create at percy.io](https://percy.io/)
5. **Percy Token** - From your Percy project dashboard

## Quick Start

### 1. Get Your Percy Token

1. Go to [percy.io](https://percy.io/)
2. Sign in to your account
3. Select or create a project
4. Copy the **Project Token** from the dashboard

### 2. Run Visual Tests

#### **Option A: Using the PowerShell Script (Recommended for Windows)**

```powershell
# Set your Percy token
$env:PERCY_TOKEN = "your_percy_token_here"

# Run the visual tests
.\run-percy-tests.ps1
```

**With custom build name:**
```powershell
$env:PERCY_TOKEN = "your_token"
$env:PERCY_BUILD_NAME = "My Build Name"
.\run-percy-tests.ps1
```

#### **Option B: Using Gradle Directly**

```powershell
# Set environment variables
$env:PERCY_TOKEN = "your_token_here"
$env:PERCY_BUILD_NAME = "Visual Testing"

# Run the test
.\gradlew.bat percyTest --info --stacktrace
```

#### **Option C: Using the Standard Gradle Test**

```powershell
# Set environment variables
$env:PERCY_TOKEN = "your_token_here"

# Run tests
.\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner" --info
```

### 3. For BrowserStack Users

Set additional environment variables:

```powershell
$env:BROWSERSTACK_USERNAME = "your_bs_username"
$env:BROWSERSTACK_ACCESS_KEY = "your_bs_access_key"
$env:BROWSERSTACK_APP_ID = "bs://your_app_id"
$env:PERCY_TOKEN = "your_percy_token"

# Run the tests
.\run-percy-tests.ps1
```

## Output

### Screenshots Location
All screenshots are saved to:
```
build/percy-snapshots/
```

### Successful Run Output
```
[PercyHelper] Capturing screenshot: Home Screen
[PercyHelper] Screenshot captured at: C:\...\temp\xyz.png
[PercyHelper] Screenshot persisted to: C:\...\build\percy-snapshots\Home_Screen.png
[PercyHelper] Uploading to Percy via CLI: percy snapshot "..." --name="Home Screen"
[PercyHelper] ✓ Percy snapshot upload successful for: Home Screen
```

### Check Results
After tests complete, check your Percy dashboard:
- Visit: https://percy.io/builds
- Select your project
- View the build you just ran
- Compare visual changes

## Troubleshooting

### Error: "PERCY_TOKEN not provided"
**Solution:** Set your token before running:
```powershell
$env:PERCY_TOKEN = "your_token_here"
```

### Error: "npm is not installed or not in PATH"
**Solution:** Install Node.js from https://nodejs.org/

### Error: "Appium server is not running"
**Solution:** Start Appium server:
```powershell
# In another terminal
appium
```

### Screenshots saved but not uploading
**Possible causes:**
- Percy token is invalid
- Percy server not running (check `percy exec` in logs)
- Network connectivity issue

**Check manually saved screenshots:**
```powershell
ls build/percy-snapshots/
```

### "Failed to take Percy snapshot"
**Check:**
1. Appium driver is properly initialized
2. Android emulator/device is unlocked
3. App is running on the device
4. Check console output for detailed error message

## Environment Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `PERCY_TOKEN` | Your Percy project token (**required**) | `abc123xyz` |
| `PERCY_BUILD_NAME` | Custom build name for this run | `Visual Test Build` |
| `APPIUM_URL` | Appium server URL (local tests) | `http://127.0.0.1:4723/wd/hub` |
| `DEVICE_NAME` | Android device name | `emulator-5554` |
| `APP_PATH` | Path to APK file | `src/main/java/org/example/apk/General-Store.apk` |
| `BROWSERSTACK_USERNAME` | BrowserStack username | `your_bs_user` |
| `BROWSERSTACK_ACCESS_KEY` | BrowserStack access key | `your_bs_key` |
| `BROWSERSTACK_APP_ID` | BrowserStack app ID | `bs://12345abc` |

## File Structure

```
project/
├── src/test/resources/features/
│   └── visual.feature              # Test scenarios
├── src/test/java/org/example/
│   ├── hooks/
│   │   └── TestHooks.java          # Driver setup/teardown
│   ├── steps/
│   │   └── VisualSteps.java        # Test step definitions
│   ├── percys/
│   │   └── PercyHelper.java        # Screenshot capture & upload
│   ├── runners/
│   │   └── CucumberVisualRunner.java # Test runner
│   └── support/
│       └── DriverHolder.java       # Driver storage
├── build/
│   └── percy-snapshots/            # Saved screenshots
├── .percy.yml                      # Percy configuration
└── run-percy-tests.ps1             # PowerShell runner script
```

## Gradle Tasks

### `percyTest` (Recommended)
Runs tests with Percy CLI management:
```powershell
.\gradlew.bat percyTest --info
```

### `test`
Runs Cucumber tests directly:
```powershell
.\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"
```

## Advanced Usage

### Dry Run (preview screenshots without uploading)
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --dry-run
```

### Debug Mode
```powershell
$env:PERCY_TOKEN = "your_token"
.\gradlew.bat percyTest --debug --info
```

### Custom Appium URL
```powershell
$env:PERCY_TOKEN = "your_token"
$env:APPIUM_URL = "http://192.168.1.100:4723/wd/hub"
.\run-percy-tests.ps1
```

## Support

- **Percy Documentation:** https://docs.percy.io/
- **Appium Documentation:** https://appium.io/docs/
- **Percy Discord Community:** https://discord.gg/percy

## Next Steps

1. ✓ Set up Percy token
2. ✓ Install dependencies (`npm install` via Gradle)
3. ✓ Start Appium server (for local tests)
4. ✓ Run the tests: `.\run-percy-tests.ps1`
5. ✓ View results on Percy dashboard

Happy visual testing! 📸


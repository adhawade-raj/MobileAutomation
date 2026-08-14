Percy + BrowserStack visual test integration

This project contains a small example of integrating Percy visual snapshots into an Appium/Cucumber-JUnit test.

Important: Do NOT commit secrets (PERCY_TOKEN, BROWSERSTACK credentials) into the repository.

Set Percy token locally (PowerShell):

# Do NOT store the token in the repo. Set it in your shell or pass it to the runner script:
$env:PERCY_TOKEN = "<your_percy_token_here>"

Run the visual scenarios (PowerShell):

$env:PERCY_TOKEN = "<your_token_here>"; .\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"

To run on BrowserStack, set the BrowserStack environment variables as well:

$env:BROWSERSTACK_USERNAME = "<bs_user>"; $env:BROWSERSTACK_ACCESS_KEY = "<bs_key>"; $env:BROWSERSTACK_APP_ID = "<app-id-if-uploaded>"; $env:PERCY_TOKEN = "<your_token>"; .\gradlew.bat test --tests "org.example.runners.CucumberVisualRunner"

Notes on files:
- `src/test/java/org/example/hooks/TestHooks.java` creates an AndroidDriver. It will connect to BrowserStack if `BROWSERSTACK_USERNAME` and `BROWSERSTACK_ACCESS_KEY` are provided. Additional BrowserStack options: `BROWSERSTACK_DEVICE`, `BROWSERSTACK_OS_VERSION`, `BROWSERSTACK_PROJECT`, `BROWSERSTACK_BUILD`, `BROWSERSTACK_SESSION_NAME`.
- `src/test/java/org/example/percys/PercyHelper.java` will try to call the Percy Java SDK if present, otherwise it will save local PNGs to `build/percy-snapshots/` which you can upload with Percy CLI in CI.

Quick Percy upload of saved snapshots

If for some reason the Percy agent couldn't run during tests, snapshots are saved under:

build/percy-snapshots/

You can upload those manually using the helper script included in the repo:

PowerShell (install Node/npm or use the -InstallIfMissing flag):

```powershell
# Attempt to install percy globally if missing and upload
.\percy-upload-saved.ps1 -InstallIfMissing
```

Or run without auto-install if you have npx/percy available:

```powershell
.\percy-upload-saved.ps1
```

Run Percy as part of Gradle (recommended)

```powershell
# set env vars for this session
$env:PERCY_TOKEN = "<your_token>"
$env:PERCY_BUILD_NAME = "Visual Testing"
$env:BROWSERSTACK_USERNAME = "<bs_user>"
$env:BROWSERSTACK_ACCESS_KEY = "<bs_key>"
$env:BROWSERSTACK_APP_ID = "bs://<your_app_id>"

# Run Gradle-managed Percy test task (installs local percy-cli via npm)
.\gradlew.bat percyTest --info --stacktrace
```

CI recommendation:
- Use environment variables to set `PERCY_TOKEN` and BrowserStack credentials in the CI environment.
- If `percy-java-selenium` is not available or suitable, use the saved PNG fallback and upload via Percy CLI in a separate CI step.

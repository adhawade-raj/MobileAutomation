# 📑 Percy Setup - File Index

This document lists all the files that were modified or created, with their purposes.

## 🚀 START HERE

**File**: `START_HERE.txt`
- **Purpose**: Quick start guide with 3-step setup
- **Read if**: You just want to get started immediately
- **Time**: 2 minutes

---

## 📖 DOCUMENTATION (Read in This Order)

### 1. SETUP_COMPLETE.md
- **Purpose**: Overview of what was done and why
- **Contains**: 
  - Summary of changes
  - Quick start checklist
  - Architecture overview
  - Verification steps
- **Read if**: You want to understand the changes
- **Time**: 5 minutes

### 2. PERCY_QUICK_START.md
- **Purpose**: Detailed getting started guide
- **Contains**:
  - Step-by-step instructions
  - Environment variable reference
  - Troubleshooting tips
  - Gradle task reference
- **Read if**: You want complete instructions
- **Time**: 10 minutes

### 3. PERCY_SETUP_SUMMARY.md
- **Purpose**: Technical deep dive
- **Contains**:
  - What each file does
  - How the system works
  - Architecture diagram
  - Integration points
- **Read if**: You want technical details
- **Time**: 15 minutes

### 4. COMMANDS.md
- **Purpose**: Copy & paste ready commands
- **Contains**:
  - Ready-to-use command examples
  - Common workflows
  - Troubleshooting commands
  - Pro tips and scripts
- **Read if**: You need specific commands
- **Use**: Copy commands directly

---

## 🛠️ HELPER SCRIPTS (Execute When Needed)

### validate-setup.ps1
- **Purpose**: Check that your environment is ready
- **When to run**: Before running tests for the first time
- **Command**: `.\validate-setup.ps1`
- **Output**: Shows which prerequisites are installed
- **Time to run**: 10 seconds

### run-percy-tests.ps1
- **Purpose**: Main test runner (RECOMMENDED)
- **When to run**: Every time you want to run tests
- **Command**: `.\run-percy-tests.ps1`
- **Requires**: PERCY_TOKEN environment variable set
- **Output**: Runs tests and shows results
- **Time to run**: 2-5 minutes

### test-build.ps1
- **Purpose**: Test that project compiles
- **When to run**: If you're having compilation issues
- **Command**: `.\test-build.ps1`
- **Output**: Build result and diagnostics
- **Time to run**: 1-2 minutes

---

## 💻 JAVA SOURCE FILES (Modified)

### src/test/java/org/example/percys/PercyHelper.java
- **What changed**: Complete rewrite with better error handling
- **Key features**:
  - Captures screenshots from Appium driver
  - Saves to local directory
  - Uploads to Percy via CLI
  - Graceful error recovery
- **Uses**: Apache Commons IO for file operations
- **Important methods**: `public static void snapshot(AndroidDriver driver, String name)`

### src/test/java/org/example/steps/VisualSteps.java
- **What changed**: Added detailed logging and validation
- **Key features**:
  - Driver initialization check
  - Clear step descriptions
  - Better error messages
- **Steps implemented**:
  - Given: the app is launched
  - When: I take a Percy snapshot
  - Then: the test completes

### src/test/java/org/example/hooks/TestHooks.java
- **What changed**: Enhanced logging and error reporting
- **Key features**:
  - Detailed setup/teardown logging
  - Better configuration display
  - Improved error messages with hints
  - Session ID tracking
- **Methods**:
  - setUp() - Initializes driver
  - tearDown() - Closes driver gracefully

---

## ⚙️ CONFIGURATION FILES (Modified/Created)

### build.gradle
- **What changed**: Enabled test output logging
- **Key setting**: `showStandardStreams = true`
- **Why**: To see Percy upload output in console
- **Already had**: percyTest task and npm integration

### .percy.yml
- **Status**: Created new
- **Purpose**: Percy configuration for app testing
- **Contains**:
  - Config version 2
  - App testing mode settings
  - Discovery disabled

### package.json
- **Status**: Already existed
- **Percy CLI**: @percy/cli: ^1.28.0
- **Purpose**: npm dependency management

---

## 📋 FEATURE & TEST FILES (Existing)

### src/test/resources/features/visual.feature
- **Status**: Already exists (not modified)
- **Purpose**: Cucumber BDD scenarios
- **Scenarios**: Open app and take snapshot
- **Tags**: @visual

### src/test/java/org/example/runners/CucumberVisualRunner.java
- **Status**: Already exists (not modified)
- **Purpose**: JUnit test runner for Cucumber
- **Runs**: Tests tagged with @visual

### src/test/java/org/example/support/DriverHolder.java
- **Status**: Already exists (not modified)
- **Purpose**: Shared driver instance
- **Used by**: All hooks and steps

---

## 📂 DIRECTORY STRUCTURE AFTER RUNNING TESTS

```
project/
├── build/
│   └── percy-snapshots/
│       ├── Home_Screen.png          ← Screenshots saved here
│       ├── Login_Screen.png
│       └── ...
│
├── src/
│   └── test/
│       ├── java/org/example/
│       │   ├── percys/PercyHelper.java         ✓ Enhanced
│       │   ├── steps/VisualSteps.java          ✓ Enhanced
│       │   ├── hooks/TestHooks.java            ✓ Enhanced
│       │   ├── runners/CucumberVisualRunner.java
│       │   └── support/DriverHolder.java
│       │
│       └── resources/features/
│           └── visual.feature
│
├── build.gradle                     ✓ Modified
├── .percy.yml                       ✓ Created
├── package.json                     ✓ (Updated)
│
├── run-percy-tests.ps1              ✓ Created
├── validate-setup.ps1               ✓ Created
├── test-build.ps1                   ✓ Created
│
└── Documentation/
    ├── START_HERE.txt               ✓ Created
    ├── SETUP_COMPLETE.md            ✓ Created
    ├── PERCY_QUICK_START.md         ✓ Created
    ├── PERCY_SETUP_SUMMARY.md       ✓ Created
    ├── COMMANDS.md                  ✓ Created
    └── FILE_INDEX.md                ✓ This file
```

---

## 🎯 HOW TO USE THIS INDEX

### If you're new to the project:
1. Read: START_HERE.txt (2 min)
2. Run: validate-setup.ps1 (1 min)
3. Run: run-percy-tests.ps1 (3-5 min)

### If you want to understand the setup:
1. Read: SETUP_COMPLETE.md (5 min)
2. Read: PERCY_QUICK_START.md (10 min)
3. Skim: PERCY_SETUP_SUMMARY.md (15 min)

### If you need specific commands:
- Open: COMMANDS.md
- Search for what you need
- Copy & paste the command

### If you want technical details:
1. Read: PERCY_SETUP_SUMMARY.md (detailed)
2. Check: PercyHelper.java (implementation)
3. Check: build.gradle (configuration)

### If something isn't working:
1. Run: validate-setup.ps1 (check environment)
2. Read: PERCY_QUICK_START.md → Troubleshooting (10 min)
3. Read: COMMANDS.md → Troubleshooting Commands (copy & paste)

---

## 📊 FILE MODIFICATION SUMMARY

| File | Status | Changes |
|------|--------|---------|
| PercyHelper.java | Modified | Complete rewrite with error handling |
| VisualSteps.java | Modified | Added logging and validation |
| TestHooks.java | Modified | Enhanced logging and error reporting |
| build.gradle | Modified | Enabled test output logging |
| .percy.yml | Created | New Percy configuration |
| run-percy-tests.ps1 | Created | Main test runner script |
| validate-setup.ps1 | Created | Environment validation script |
| test-build.ps1 | Created | Build test script |
| visual.feature | Unchanged | Test scenarios remain same |
| CucumberVisualRunner.java | Unchanged | Test runner unchanged |

---

## 🚀 QUICK NAVIGATION

### Getting Started
- **Fastest**: Read START_HERE.txt
- **Recommended**: Run validate-setup.ps1
- **Run**: run-percy-tests.ps1

### Learning
- **Overview**: SETUP_COMPLETE.md
- **Guide**: PERCY_QUICK_START.md
- **Technical**: PERCY_SETUP_SUMMARY.md

### Reference
- **Commands**: COMMANDS.md
- **This**: FILE_INDEX.md

### Testing
- **Validate**: validate-setup.ps1
- **Run**: run-percy-tests.ps1
- **Check Build**: test-build.ps1

---

## 📝 DOCUMENT READING TIME

| Document | Time | Purpose |
|----------|------|---------|
| START_HERE.txt | 2 min | Quick start |
| SETUP_COMPLETE.md | 5 min | Overview |
| PERCY_QUICK_START.md | 10 min | Full guide |
| PERCY_SETUP_SUMMARY.md | 15 min | Technical details |
| COMMANDS.md | 5 min (ref) | Copy & paste |
| FILE_INDEX.md | 5 min | This guide |
| **Total** | **~45 min** | **Full understanding** |

---

## ✅ VERIFICATION CHECKLIST

Before running tests, verify:

- [ ] Node.js & npm installed (check: `node -v`)
- [ ] Java JDK installed (check: `java -version`)
- [ ] Gradle wrapper present (check: `ls gradlew.bat`)
- [ ] .percy.yml exists (check: `ls .percy.yml`)
- [ ] run-percy-tests.ps1 exists (check: `ls run-percy-tests.ps1`)
- [ ] Test features exist (check: `ls src/test/resources/features/`)
- [ ] Java source files exist (check: `ls src/test/java/org/example/`)

**Run validator**: `.\validate-setup.ps1`

---

## 🎯 NEXT STEPS

1. **Read**: START_HERE.txt
2. **Validate**: `.\validate-setup.ps1`
3. **Get Token**: https://percy.io/builds
4. **Run**: `$env:PERCY_TOKEN = "your_token"; .\run-percy-tests.ps1`
5. **Check Results**: https://percy.io/builds

---

## 💡 TIPS

- **Quick reference**: Keep COMMANDS.md open
- **Troubleshooting**: See PERCY_QUICK_START.md
- **Deep dive**: Read PERCY_SETUP_SUMMARY.md
- **Scripts**: Run validate-setup.ps1 before each test session
- **Output**: Screenshots saved to build/percy-snapshots/

---

## 📞 SUPPORT

- **Percy Docs**: https://docs.percy.io/
- **Appium Docs**: https://appium.io/docs/
- **Cucumber Docs**: https://cucumber.io/docs/
- **This Project**: See START_HERE.txt

---

**You're all set! Start with**: `START_HERE.txt`

Then run: `.\run-percy-tests.ps1`


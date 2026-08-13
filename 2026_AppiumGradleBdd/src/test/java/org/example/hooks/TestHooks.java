package org.example.hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.support.DriverHolder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestHooks {

    private final DriverHolder holder;

    // PicoContainer will inject DriverHolder via constructor
    public TestHooks(DriverHolder holder) {
        this.holder = holder;
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("[TestHooks] ========== STARTING TEST SETUP ==========");
        System.out.println("=".repeat(60));

        // Read environment to decide local Appium vs BrowserStack
        String bsUser = System.getenv("BROWSERSTACK_USERNAME");
        String bsKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        String bsApp = System.getenv("BROWSERSTACK_APP_ID");
        String bsDevice = System.getenv("BROWSERSTACK_DEVICE");
        String bsOsVersion = System.getenv("BROWSERSTACK_OS_VERSION");
        String bsProject = System.getenv("BROWSERSTACK_PROJECT");
        String bsBuild = System.getenv("BROWSERSTACK_BUILD");
        String bsName = System.getenv("BROWSERSTACK_SESSION_NAME");

        String appPath = System.getenv("APP_PATH");
        String deviceName = System.getenv("DEVICE_NAME");
        if (deviceName == null) deviceName = "emulator-5554";

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2");

        URL remoteUrl = null;
        boolean usingBrowserStack = (bsUser != null && bsKey != null && !bsUser.trim().isEmpty() && !bsKey.trim().isEmpty());

        if (usingBrowserStack) {
            // BrowserStack remote hub. Put BrowserStack-specific meta into bstack:options
            // and set platform/device info.
            System.out.println("[TestHooks] ✓ BrowserStack mode detected");
            System.out.println("[TestHooks]   User: " + bsUser);
            System.out.println("[TestHooks]   Device: " + (bsDevice != null && !bsDevice.isEmpty() ? bsDevice : deviceName));
            System.out.println("[TestHooks]   OS Version: " + (bsOsVersion != null && !bsOsVersion.isEmpty() ? bsOsVersion : "default"));

            if (bsDevice != null && !bsDevice.trim().isEmpty()) options.setDeviceName(bsDevice);
            else options.setDeviceName(deviceName);

            if (bsOsVersion != null && !bsOsVersion.trim().isEmpty()) options.setCapability("platformVersion", bsOsVersion);

            if (bsApp != null && !bsApp.trim().isEmpty()) {
                System.out.println("[TestHooks]   App ID: " + bsApp);
                options.setCapability("app", bsApp);
            }

            Map<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("userName", bsUser);
            bstackOptions.put("accessKey", bsKey);
            if (bsProject != null && !bsProject.trim().isEmpty()) bstackOptions.put("projectName", bsProject);
            if (bsBuild != null && !bsBuild.trim().isEmpty()) bstackOptions.put("buildName", bsBuild);
            if (bsName != null && !bsName.trim().isEmpty()) bstackOptions.put("sessionName", bsName);

            options.setCapability("bstack:options", bstackOptions);

            String remote = "https://hub-cloud.browserstack.com/wd/hub";
            try {
                remoteUrl = new URL(remote);
            } catch (MalformedURLException m) {
                System.err.println("[TestHooks] ✗ Malformed BrowserStack hub URL: " + remote + " -> " + m.getMessage());
                throw m;
            }
        } else {
            // Local Appium server
            System.out.println("[TestHooks] ✓ Local Appium mode detected");
            if (appPath == null || appPath.trim().isEmpty()) {
                // Default to included APK in project if user hasn't set APP_PATH
                appPath = System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\apk\\General-Store.apk";
            }
            System.out.println("[TestHooks]   App Path: " + appPath);
            System.out.println("[TestHooks]   Device: " + deviceName);

            options.setApp(appPath);
            if (deviceName != null) options.setDeviceName(deviceName);
            String local = System.getenv("APPIUM_URL");
            if (local == null || local.trim().isEmpty()) local = "http://127.0.0.1:4723/wd/hub";

            System.out.println("[TestHooks]   Appium URL: " + local);

            try {
                remoteUrl = new URL(local);
            } catch (MalformedURLException m) {
                System.err.println("[TestHooks] ✗ Malformed APPIUM_URL: " + local + " -> " + m.getMessage());
                throw m;
            }
        }

        // Log final remote URL and capabilities to help debugging connection issues
        System.out.println("[TestHooks] Remote URL: " + (remoteUrl != null ? remoteUrl.toString() : "<null>"));
        System.out.println("[TestHooks] Creating AndroidDriver...");

        // Create driver with explicit error handling to give clear diagnostics
        try {
            holder.driver = new AndroidDriver(remoteUrl, options);
            System.out.println("[TestHooks] ✓ AndroidDriver created successfully");
            System.out.println("[TestHooks] Session ID: " + holder.driver.getSessionId());
        } catch (Exception e) {
            System.err.println("[TestHooks] ✗ Failed to create AndroidDriver. Reason: " + e.toString());
            e.printStackTrace();
            // Provide some actionable hints
            if (!usingBrowserStack) {
                System.err.println("[TestHooks] Troubleshooting hints:");
                System.err.println("  1. Ensure Appium server is running at " + remoteUrl);
                System.err.println("  2. Ensure Android emulator/device '" + deviceName + "' is available and unlocked");
                System.err.println("  3. Verify APK exists at: " + appPath);
                System.err.println("  4. Run 'adb devices' to see connected devices");
            } else {
                System.err.println("[TestHooks] BrowserStack Troubleshooting:");
                System.err.println("  1. Verify BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY are correct");
                System.err.println("  2. Verify app ID (BROWSERSTACK_APP_ID) is correct and available");
                System.err.println("  3. Check BrowserStack console for more details");
            }
            throw e;
        }

        // Small wait to let app start; tests may replace with explicit waits
        System.out.println("[TestHooks] Waiting for app to initialize (3 seconds)...");
        Thread.sleep(3000);
        System.out.println("[TestHooks] ✓ Setup complete - app ready for testing");
        System.out.println("=".repeat(60) + "\n");
    }

    @After
    public void tearDown() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("[TestHooks] ========== TEARING DOWN TEST ==========");
        System.out.println("=".repeat(60));

        if (holder.driver != null) {
            try {
                System.out.println("[TestHooks] Closing driver...");
                holder.driver.quit();
                System.out.println("[TestHooks] ✓ Driver closed successfully");
            } catch (Exception e) {
                System.err.println("[TestHooks] ✗ Error closing driver: " + e.getMessage());
            }
        } else {
            System.out.println("[TestHooks] Driver is null - nothing to close");
        }

        System.out.println("=".repeat(60) + "\n");
    }
}

package org.example.percys;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Percy visual testing helper — uploads screenshots to Percy server
public class PercyHelper {

    public static void snapshot(AndroidDriver driver, String name) {
        String percyToken = System.getenv("PERCY_TOKEN");
        String percyServerAddress = System.getenv("PERCY_SERVER_ADDRESS");

        System.out.println("[PercyHelper] Snapshot: " + name);

        File screenshotFile = null;
        try {
            // 1. Capture screenshot (fast operation)
            String sanitizedName = name.replaceAll("[^a-zA-Z0-9-_]", "_");
            System.out.println("[PercyHelper] Capturing...");

            screenshotFile = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);

            // 2. Save to local directory (fast operation)
            String buildDir = System.getProperty("user.dir") + File.separator + "build" + File.separator + "percy-snapshots";
            File buildDirFile = new File(buildDir);
            if (!buildDirFile.exists()) {
                buildDirFile.mkdirs();
            }

            File persistedScreenshot = new File(buildDir, sanitizedName + ".png");
            FileUtils.copyFile(screenshotFile, persistedScreenshot);
            System.out.println("[PercyHelper] Saved: " + persistedScreenshot.getName());

            // 3. Upload to Percy (async - non-blocking)
            if (percyToken != null && !percyToken.isEmpty()) {
                String serverUrl = percyServerAddress != null && !percyServerAddress.isEmpty()
                    ? percyServerAddress
                    : "http://localhost:5338";

                // Run upload in background thread to avoid blocking
                Thread uploadThread = new Thread(() -> {
                    try {
                        boolean success = uploadToPercyServer(serverUrl, persistedScreenshot, name);
                        if (success) {
                            System.out.println("[PercyHelper] ✓ " + name + " uploaded");
                        } else {
                            System.out.println("[PercyHelper] ⚠ " + name + " upload failed (saved locally)");
                        }
                    } catch (Exception e) {
                        System.err.println("[PercyHelper] Upload error: " + e.getMessage());
                    }
                });
                uploadThread.setDaemon(true);
                uploadThread.start();
            } else {
                System.out.println("[PercyHelper] ✓ Saved (PERCY_TOKEN not set)");
            }

        } catch (Exception e) {
            System.err.println("[PercyHelper] Error: " + e.getMessage());

            // Fallback: save locally
            if (screenshotFile != null && screenshotFile.exists()) {
                try {
                    String sanitizedName = name.replaceAll("[^a-zA-Z0-9-_]", "_");
                    String buildDir = System.getProperty("user.dir") + File.separator + "build" + File.separator + "percy-snapshots";
                    File buildDirFile = new File(buildDir);
                    if (!buildDirFile.exists()) {
                        buildDirFile.mkdirs();
                    }
                    File outFile = new File(buildDir, sanitizedName + ".png");
                    FileUtils.copyFile(screenshotFile, outFile);
                    System.out.println("[PercyHelper] ✓ Saved locally: " + outFile.getName());
                } catch (IOException ioEx) {
                    System.err.println("[PercyHelper] Failed to save: " + ioEx.getMessage());
                }
            }
        }
    }

    private static boolean uploadToPercyServer(String serverUrl, File screenshotFile, String snapshotName) {
        try {
            // Use Percy SDK endpoint for app snapshots (faster than base64)
            // Send metadata only - Percy server accesses file directly
            String jsonPayload = String.format(
                "{\"name\":\"%s\",\"filepath\":\"%s\"}",
                snapshotName.replace("\"", "\\\""),
                screenshotFile.getAbsolutePath().replace("\\", "\\\\")
            );

            // POST to Percy server's app snapshot endpoint
            URL url = new URL(serverUrl + "/percy/app-screenshot");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(5000);

            System.out.println("[PercyHelper] Posting to: " + url);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("[PercyHelper] Response code: " + responseCode);

            if (responseCode >= 200 && responseCode < 300) {
                System.out.println("[PercyHelper] ✓ Snapshot sent to Percy");
                return true;
            } else {
                System.out.println("[PercyHelper] Response code " + responseCode + " - trying alternative endpoint...");
                return tryAlternativeUpload(serverUrl, screenshotFile, snapshotName);
            }

        } catch (Exception e) {
            System.err.println("[PercyHelper] Error with app-screenshot endpoint: " + e.getMessage());
            return tryAlternativeUpload(serverUrl, screenshotFile, snapshotName);
        }
    }

    private static boolean tryAlternativeUpload(String serverUrl, File screenshotFile, String snapshotName) {
        try {
            // Fallback: Try direct file snapshot endpoint
            URL url = new URL(serverUrl + "/percy/snapshot");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(5000);

            // Send just metadata with file path
            String jsonPayload = String.format(
                "{\"name\":\"%s\",\"file_path\":\"%s\"}",
                snapshotName.replace("\"", "\\\""),
                screenshotFile.getAbsolutePath().replace("\\", "\\\\")
            );

            System.out.println("[PercyHelper] Trying fallback endpoint...");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("[PercyHelper] Fallback response code: " + responseCode);

            if (responseCode >= 200 && responseCode < 300) {
                System.out.println("[PercyHelper] ✓ Snapshot sent via fallback");
                return true;
            } else {
                System.err.println("[PercyHelper] ✗ Both endpoints failed");
                return false;
            }

        } catch (Exception e) {
            System.err.println("[PercyHelper] Error on fallback endpoint: " + e.getMessage());
            return false;
        }
    }
}

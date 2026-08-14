package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class SimpleVisualTest {

    public static void main(String[] args) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setApp("D:\\Workspace\\IntelliJIdea\\2026_AppiumGradleBdd\\src\\main\\java\\org\\example\\apk\\General-Store.apk");

        AndroidDriver driver =
                new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);

        Thread.sleep(5000);

        // Take screenshot
        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File actual = new File("actualD:\\Workspace\\IntelliJIdea\\2026_AppiumGradleBdd\\src\\main\\java\\org\\example\\diff/home.png");
        actual.getParentFile().mkdirs();
        src.renameTo(actual);

        File baseline = new File("D:\\Workspace\\IntelliJIdea\\2026_AppiumGradleBdd\\src\\main\\java\\org\\example\\baseline\\home.png");

        // Create baseline if not exists
        if (!baseline.exists()) {
            baseline.getParentFile().mkdirs();
            actual.renameTo(baseline);
            System.out.println("✅ Baseline created. Run again.");
            driver.quit();
            return;
        }

        BufferedImage img1 = ImageIO.read(baseline);
        BufferedImage img2 = ImageIO.read(actual);

        int width = Math.min(img1.getWidth(), img2.getWidth());
        int height = Math.min(img1.getHeight(), img2.getHeight());

        BufferedImage diffImage =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int diffCount = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel1 = img1.getRGB(x, y);
                int pixel2 = img2.getRGB(x, y);

                if (pixel1 != pixel2) {
                    diffImage.setRGB(x, y, Color.RED.getRGB());
                    diffCount++;
                } else {
                    diffImage.setRGB(x, y, pixel1);
                }
            }
        }

        double diffPercent =
                (double) diffCount / (width * height) * 100;

        if (diffPercent > 0.5) { // threshold
            new File("diff").mkdirs();
            ImageIO.write(diffImage, "png",
                    new File("D:\\Workspace\\IntelliJIdea\\2026_AppiumGradleBdd\\src\\main\\java\\org\\example\\diff\\home_diff.png"));
            System.out.println("❌ VISUAL FAILED: " + diffPercent + "% difference");
        } else {
            System.out.println("✅ VISUAL PASSED");
        }

        driver.quit();
    }
}

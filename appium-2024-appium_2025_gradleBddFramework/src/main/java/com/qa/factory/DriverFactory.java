package com.qa.factory;

import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverFactory {
    AndroidUtils androidUtils;
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

     Properties prop;
    String configFilePath = Paths.get("src", "test", "resources", "testData", "config.properties").toAbsolutePath().toString();
    String chromeDriverPath = Paths.get("src", "test", "resources", "testData", "chromedriver.exe").toAbsolutePath().toString();
    String apkFile = Paths.get("src", "test", "resources", "testData", "General-Store.apk").toAbsolutePath().toString();
    String ipAddress;
    String port;


    public void initConfigProperties() {
        String configContent = null;
        try {
            configContent = FileUtils.readFileToString(new File(configFilePath), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Load the properties using StringReader
        prop = new Properties();
        try {
            prop.load(new StringReader(configContent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Retrieve property values
        ipAddress = prop.getProperty("ipAddress");
        System.out.println("IP Address is: " + ipAddress);

        port = prop.getProperty("port");
        System.out.println("Running Port is: " + port);
    }


    public void initDriver() {

        initConfigProperties();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("deviceName"));
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        options.setApp(apkFile);
            System.out.println("Thread ID for setting driver: " + Thread.currentThread().getId());
        try {
            driver = new AndroidDriver(new URL("http://" + ipAddress + ":" + port), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Driver set for thread: " + Thread.currentThread().getId());
        androidUtils = new AndroidUtils(driver);
        androidUtils.implicitTimeout(10);
    }
}
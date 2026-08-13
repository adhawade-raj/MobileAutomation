package com.qa.factory;

import com.google.inject.Provider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverFactory implements Provider<AndroidDriver> {
    private static AndroidDriver driver;
    private Properties prop;

    private void loadConfigProperties() {
        prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("testData/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config properties", e);
        }
    }

    @Override
    public AndroidDriver get() {
        if (driver == null) {
            loadConfigProperties();
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(prop.getProperty("deviceName"));
            options.setApp(Paths.get("src", "test", "resources", "testData", "General-Store.apk").toAbsolutePath().toString());

            try {
                driver = new AndroidDriver(new URL("http://" + prop.getProperty("ipAddress") + ":" + prop.getProperty("port")), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Appium Server URL", e);
            }
        }
        return driver;
    }
}

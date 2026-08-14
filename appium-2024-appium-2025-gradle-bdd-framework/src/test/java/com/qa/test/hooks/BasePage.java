package com.qa.test.hooks;

import com.qa.factory.DriverFactory;
import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected static AndroidDriver driver;
    public static AndroidUtils androidUtils;

    static final Logger logger = LogManager.getLogger(BasePage.class);

    // Constructor should not use 'this' driver, use the static driver from DriverFactory
    public BasePage() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            logger.info("******* Driver fetched in BasePage: " + (driver != null));
        }
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Please check your setup.");
        }
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        androidUtils = new AndroidUtils(driver);
    }
}
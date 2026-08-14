package com.qa.test.hooks;

import com.google.inject.Inject;
import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final AndroidDriver driver;
    public final AndroidUtils androidUtils;
    static final Logger logger = LogManager.getLogger(BasePage.class);

    @Inject
    public BasePage(AndroidDriver driver, AndroidUtils androidUtils) {
        this.driver = driver;
        this.androidUtils = androidUtils;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        logger.info("******* Driver initialized in BasePage: " + (driver != null));
    }
}

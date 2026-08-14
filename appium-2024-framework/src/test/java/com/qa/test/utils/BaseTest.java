package com.qa.test.utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.qa.page.objects.CartPage;
import com.qa.page.objects.FormPage;
import com.qa.page.objects.ProductPage;
import com.qa.utils.AndroidActions;
import com.qa.utils.AndroidUtils;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	public AndroidDriver driver;
	public AndroidUtils androidUtils;
	public FormPage formPage;
	public AndroidActions androidActions;
	public ProductPage productPage;
	public CartPage cartPage;

	@BeforeClass(alwaysRun = true)
	public void initDriver() {
		androidUtils = new AndroidUtils(driver);
		driver = androidUtils.initDriver();
		formPage = new FormPage(driver);
		androidActions = new AndroidActions(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
	}

	@AfterClass()
	public void tearDown() {
		driver.quit();
	}

}

package com.qa.TestUtils;

import org.testng.annotations.BeforeClass;
import com.qa.PageObjects.CartPage;
import com.qa.PageObjects.FormPage;
import com.qa.PageObjects.ProductPage;
import com.qa.Utils.AndroidActions;
import com.qa.Utils.AndroidUtils;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	public AndroidDriver driver;
	public AndroidUtils androidUtils;
	public FormPage formPage;
	public AndroidActions androidActions;
	public ProductPage productPage;
	public CartPage cartPage;
	
	
	@BeforeClass(alwaysRun=true)
	public void initDriver() {
		androidUtils = new AndroidUtils(driver);
		driver = androidUtils.initDriver();
		formPage = new FormPage(driver);
		androidActions = new AndroidActions(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
	}
	
	
//	@AfterClass()
//	public void tearDown() {
//		driver.quit();	
//	}
	
}

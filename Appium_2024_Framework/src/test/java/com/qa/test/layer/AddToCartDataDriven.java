package com.qa.test.layer;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.test.utils.BaseTest;

public class AddToCartDataDriven extends BaseTest {

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] { { new String[] { "Jordan 6 Rings", "Air Jordan 4 Retro" } } };
	}

	@Test(dataProvider = "getProducts")
	public void addToCartTest_DataProvider(String[] productNames) {
		formPage.formFill("Argentina", "Raj", "male");

		/** With Stream Product Selection */
		productPage.addMultipleProductsToCart(productNames);

		/** Need to change this hard-coded value */
		int productCount = productPage.verifyCountProduct();
		Assert.assertTrue(productCount == 2);
		productPage.shopButton();
		String title = formPage.nextPageTitle();
		Assert.assertTrue(title.contains("Cart"), "Next Page is not Loaded");
	}

}

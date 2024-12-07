package com.qa.test.layer;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.test.utils.BaseTest;

public class AddToCartUsingStream extends BaseTest {

	@Test(enabled=false)
	public void addToCartTest() {
		formPage.countrySelection("Argentina");
		formPage.setName("Raj");
		formPage.genderSelection("male");
		formPage.letsShopButton();
		
		/**With Stream Product Selection*/
		productPage.productSelection_AddToCart_WithStream("Jordan 6 Rings");
		productPage.productSelection_AddToCart_WithStream("Jordan Lift Off");

		/**Need to change this hard-coded value*/
		int productCount = productPage.verifyCountProduct();
		Assert.assertTrue(productCount==2);
		productPage.shopButton();
		String title = formPage.nextPageTitle();
		Assert.assertTrue(title.contains("Cart"), "Next Page is not Loaded");
	}
	
	

	
	
}

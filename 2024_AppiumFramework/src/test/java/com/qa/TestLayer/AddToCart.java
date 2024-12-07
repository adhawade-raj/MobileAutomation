package com.qa.TestLayer;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.TestUtils.BaseTest;

public class AddToCart extends BaseTest {
	
	String cartStatusAfterAddingProducts;
	
	@Test
	public void addToCartTest() {
		formPage.countrySelection("Argentina");
		formPage.setName("Raj");
		formPage.genderSelection("male");
		formPage.letsShopButton();
		
		/**Without Stream Product Selection*/
		productPage.productSelection_AddToCart("Jordan 6 Rings");
//		refactoring required for below validation
//		cartStatusAfterAddingProducts = productPage.verifyCartButtonState();
//		Assert.assertEquals(cartStatusAfterAddingProducts, "ADDED TO CART");
		
		productPage.productSelection_AddToCart("Jordan Lift Off");
//		refactoring required for below validation
//		cartStatusAfterAddingProducts = productPage.verifyCartButtonState();
//		Assert.assertEquals(cartStatusAfterAddingProducts, "ADDED TO CART");
		
		/**Need to change this hard-coded value*/
		int productCount = productPage.verifyCountProduct();
		Assert.assertTrue(productCount==2);
		
		productPage.shopButton();
		
		String title = formPage.nextPageTitle();
		Assert.assertTrue(title.contains("Cart"), "Next Page is not Loaded");
	}
	

}
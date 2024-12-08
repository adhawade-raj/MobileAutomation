package com.qa.test.layer;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.test.utils.BaseTest;

public class CartPageTest extends BaseTest {

	 
	
	@Test
	public void verifyCartTotalTest() {
		formPage.countrySelection("Argentina");
		formPage.setName("Raj");
		formPage.genderSelection("male");
		formPage.letsShopButton();
		productPage.productSelection_AddToCart("Jordan 6 Rings");
		productPage.productSelection_AddToCart("Jordan Lift Off");
		
		/**Need to change this hard-coded value*/
		int productCount = productPage.verifyCountProduct();
		Assert.assertTrue(productCount==2);
		
		productPage.shopButton();
		
		double actualTotalBill = cartPage.captureTotalBillFromCart();
		double expectedTotalBill = cartPage.totalOfProducts();
		
		Assert.assertEquals(actualTotalBill, expectedTotalBill);
		
		cartPage.selectCheckBox();
		cartPage.proceedButton();
		
	}
	
}

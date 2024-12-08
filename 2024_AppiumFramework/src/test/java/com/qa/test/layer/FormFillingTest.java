package com.qa.test.layer;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.test.utils.BaseTest;

public class FormFillingTest extends BaseTest{

	
	
	@Test(enabled=true,priority=2)
	public void fillForm_PositiveTest() {
		
		formPage.countrySelection("Argentina");
		formPage.setName("Raj");
		formPage.genderSelection("male");
		formPage.letsShopButton();
		
		String title = formPage.nextPageTitle();
		Assert.assertTrue(title.contains("Products"), "Next Page is not Loaded");
	}
	
	
	@Test(enabled=true,priority=1)
	public void fillFormTest_NegativeTest() {
		
		formPage.countrySelection("Argentina");
		formPage.genderSelection("male");
		formPage.letsShopButton();
		
		String errorMessage = formPage.captureErrrorMessage();
		System.out.println("Error message is : "+errorMessage);
		Assert.assertEquals(errorMessage, "Please enter your name");
//		Assert.assertTrue(errorMessage.contains("Please enter your name"), errorMessage+" : is a  Wrong Error Message");
	}
}

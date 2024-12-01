package TestLayer;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.TestUtils.BaseTest;

public class FormFillingTest extends BaseTest{

	
	
	@Test
	public void fillFormTest() {
		
		formPage.countrySelection("Argentina");
		formPage.setName("Raj");
		formPage.genderSelection("male");
		formPage.letsShopButton();
		
		String title = formPage.nextPageTitle();
		Assert.assertTrue(title.contains("Products"), "Next Page is not Loaded");

	}
}

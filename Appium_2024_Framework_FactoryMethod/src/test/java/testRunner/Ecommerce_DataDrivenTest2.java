package testRunner;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.android.FormPage;

public class Ecommerce_DataDrivenTest2  extends BaseTest{

//	FormPage formPage; 
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup()
	{
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
	}
	
	
	@Test(dataProvider="getData")
	public void formFillTest_DataDriven(String name, String gender, String country) {
		FormPage formPage = new FormPage(driver);
		formPage.setCountrySelection(country);
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.submitButton();
	}
	
	
	@DataProvider()
	public Object[][] getData(){
		
	return new Object[][] {
		{"Raj", "male", "Argentina"},
		{"Rahul", "female", "Argentina"}
	};
		
	}
}

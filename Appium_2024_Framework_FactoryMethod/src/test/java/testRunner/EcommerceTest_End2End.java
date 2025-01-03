package testRunner;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.android.CartPage;
import pageObjects.android.FormPage;
import pageObjects.android.ProductCatalogue;

public class EcommerceTest_End2End extends BaseTest {

	
	@Test(groups= {"Smoke"})
	public void formFilltest() {
		FormPage formPage = new FormPage(driver);
		formPage.setCountrySelection("Argentina");
		formPage.setNameField("Raj");
		formPage.setGender("female");
		formPage.submitButton();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		
//		productCatalouge.addToCart1("Jordan 6 Rings");
//		productCatalouge.addToCart1("Jordan Lift Off");
		
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		
		productCatalogue.goToCartPage();
		
		
//		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text" , "Cart"));
				
				CartPage cartPage = new CartPage(driver);
				double totalSum =  cartPage.getProductsSum();
				double displayFormattedSum = cartPage.getTotalAmountDisplayed();
				Assert.assertEquals(totalSum, displayFormattedSum);
				cartPage.acceptTermsConditions();
				cartPage.submitOrder();
		
		
		
	}
}

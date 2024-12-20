package pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.android.AndroidActions;

public class ProductCatalogue extends AndroidActions {

	
	
	AndroidDriver driver;
	
	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="\"com.androidsample.generalstore:id/productName\"")
	private List<WebElement> products;
	
	@AndroidFindBy(id="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")
	private List<WebElement> productCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> productsize;
	

	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	public void addItemToCartByIndex(int index)
	{
		addToCart.get(index).click();
	
	}
	
	public void addToCart1(String productName) {
		
//		int productCount = productsize.size();
		scrollToText(productName);
		int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0; i<productCount; i++) {
			String productNames = products.get(i).getText();
			System.out.println(productNames);
			if(productNames.equalsIgnoreCase(productName)) {
				System.out.println("Product Name on Page : "+productNames);
				
				productCart.get(i).click();
			}
		}
	}
	
	
	public void goToCartPage() {
		cart.click();
	}
	
}

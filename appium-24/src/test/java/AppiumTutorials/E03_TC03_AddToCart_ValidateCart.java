package AppiumTutorials;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E03_TC03_AddToCart_ValidateCart extends BaseTest_StoreAPK {

	
	@Test
	public void addToCartTest() throws InterruptedException {
		
	driver.findElement(By.id("android:id/text1")).click();
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
	driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
	
	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Raj");
	driver.hideKeyboard();
	driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
	
	int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
	
	for(int i=0; i<productCount; i++) {
		String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
		
		if(productName.equalsIgnoreCase("Jordan 6 Rings")) {
			System.out.println("Product Name on Product Page : "+productName);
			
		driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")).get(i).click();
		}
	}
	
	
	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
	Thread.sleep(2000);
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	WebElement pageTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
	wait.until(ExpectedConditions.attributeContains(pageTitle, "text", "Cart"));
	
	String productName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
	System.out.println("Product Name on Cart Page : "+productName);
	Assert.assertEquals(productName, "Jordan 6 Rings");
	
	}
}

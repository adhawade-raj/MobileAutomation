package AppiumTutorials;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class F01_WebView_HybridApp extends BaseTest_StoreAPK {

	
	@Test
	public void webViewtest() throws InterruptedException {
		
	driver.findElement(By.id("android:id/text1")).click();
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
	driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
	
	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Raj");
	driver.hideKeyboard();
	driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"));"));
	
	int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
	
	addToCart(productCount, "Jordan 6 Rings");
	addToCart(productCount, "Jordan Lift Off");
	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
	Thread.sleep(2000);
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	WebElement pageTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
	wait.until(ExpectedConditions.attributeContains(pageTitle, "text", "Cart"));
	
	 List<WebElement> productPrices =driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrices.size();
		double totalSum =0;
		for(int i =0; i< count; i++)
		{
			String amountString =productPrices.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;  //160.97 + 120 =280.97
				
		}
		String displaySum =driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double displayFormattedSum = getFormattedAmount(displaySum);
		Assert.assertEquals(totalSum, displayFormattedSum);

		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(ele);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(6000);
		
		//Hybrid - Google page->
		
		Set<String> contextList =  driver.getContextHandles();
		for(String e: contextList) {
			System.out.println(e);
		}
		Thread.sleep(1000);
		driver.context("WEBVIEW_com.androidsample.generalstore");
		Thread.sleep(1000);
		driver.findElement(By.name("q")).sendKeys("Raj");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.context("NATIVE_APP");
		
		

		
	}

	
}

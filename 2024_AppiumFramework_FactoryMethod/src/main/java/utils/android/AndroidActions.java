package utils.android;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {

	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver=driver;
		}
	
	
	/**
	 * 
	 * @param Productcount
	 * @param productName
	 */
	public void addToCart(int Productcount, String productName) {
		
		for(int i=0; i<Productcount; i++) {
			String productNames = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			if(productNames.equalsIgnoreCase(productName)) {
				System.out.println("Product Name on Page : "+productNames);
				
			driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")).get(i).click();
			}
		}
	}
	
	
	/**
	 * 
	 * @param ele
	 */
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	/**
	 * 
	 */
	public void scrollToEndAction()
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    
			));
		}while(canScrollMore);
	}
	
	
	
	public void scrollToText(String text)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	/**
	 * 
	 * @param ele
	 * @param direction
	 */
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			 
			    "direction", direction,
			    "percent", 0.75
			));
		
		
	}
	
	/**
	 * 
	 * @param amount
	 * @return
	 */
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
		
	}
	
	
}

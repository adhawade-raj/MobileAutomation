package AppiumTutorials;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class C03_GestureSwipe extends BaseTest {

	
	
	@Test
	public void swipeGesture() {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		
		WebElement firstImage = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[1]"));
		firstImage.click();
		String focusAttributeValue = firstImage.getAttribute("focusable");
		
		Assert.assertEquals(focusAttributeValue, "true");
		
		/** Wrong with Swipe Function, as swipe is not happening validation is getting failed*/
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)firstImage).getId(),
			 
			    "direction", "left",
			    "percent", 0.75
			));

		Assert.assertEquals(focusAttributeValue, "false");		
	}
}

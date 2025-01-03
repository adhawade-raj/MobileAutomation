package AppiumTutorials;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class C01_GestureLongPress extends BaseTest {

	
	@Test
	public void longPressGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		
		WebElement ele = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("mobile: longClickGesture", 
						ImmutableMap.of(("elementId"), ((RemoteWebElement) ele).getId(),
						"duration", 2000));
		
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
String captureText =driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample menu\"]")).getText();
      	Assert.assertTrue(captureText.contains("Sample menu"), captureText+" -> Text Not Found");
	
		
	}
}

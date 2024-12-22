package AppiumTutorials;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class B01_WifiSettingTest extends BaseTest{

	
	@Test
	public void wifiSetting() {
	
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]")).click();
		
		String wifiPopuptext = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertTrue(wifiPopuptext.equals("WiFi settings"), wifiPopuptext+" -> Text is not present");
		
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("RajWifi");
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		
		
		
	}
}

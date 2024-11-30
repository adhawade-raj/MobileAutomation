package AppiumTutorials;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E01_TC01_StoreFormFilling extends BaseTest_StoreAPK {

	
	
	@Test
	public void formFilling() {
		
	driver.findElement(By.id("android:id/text1")).click();
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));"));
	driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
	
	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Raj");
	driver.hideKeyboard();
	driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	
	}
}

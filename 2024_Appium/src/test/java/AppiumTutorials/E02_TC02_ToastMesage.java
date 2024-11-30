package AppiumTutorials;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E02_TC02_ToastMesage extends BaseTest_StoreAPK {

	
	@Test
	public void toastMessgaeTest() {
		
	driver.findElement(By.id("android:id/text1")).click();
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));"));
	driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
	
//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Raj");
//	driver.hideKeyboard();
	driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
	String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	Assert.assertEquals(toastMessage, "Please enter your name");
	
	
	}
	
}

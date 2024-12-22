package AppiumTutorials;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class A01_BasicAppiumTest {

	
	@Test
	public void appiumTest() throws MalformedURLException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Raj_AppiumTest");
		options.setApp("C:\\Raj Setup\\Mobile_Automation\\2024_Appium\\src\\test\\resource\\ApiDemos-debug.apk");
		
		AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		driver.quit();
		
		
		
	}
	
}

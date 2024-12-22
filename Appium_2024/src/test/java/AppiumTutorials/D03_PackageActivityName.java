package AppiumTutorials;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;


public class D03_PackageActivityName extends BaseTest {

	@Test
	public void packageActivityName() throws MalformedURLException
	{
	//adb shell dumpsys window | grep -E 'mCurrentFocus'  - MAC
			// adb shell dumpsys window | find "mCurrentFocus"  - Windows

			//App Package & App Activity

	
((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
						
			driver.findElement(By.id("android:id/checkbox")).click();
			DeviceRotation landScape = new DeviceRotation(0, 0, 90);
			driver.rotate(landScape);		
	
}
}

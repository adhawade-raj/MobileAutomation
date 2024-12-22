package AppiumTutorials;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class A02_StartAppiumServer_WithCode {

	
	
	@Test
	public void startSeverTest() throws MalformedURLException {
		AppiumDriverLocalService service = new AppiumServiceBuilder()
		.withAppiumJS(new File("C:/Users/SHRUTI/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
		.usingDriverExecutable(new File ("C:\\Program Files\\nodejs\\node.exe"))
		.withIPAddress("0.0.0.0").usingPort(4723).build();
		
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Raj_AppiumTest");
		options.setApp("C:\\Raj Setup\\Mobile_Automation\\2024_Appium\\src\\test\\resource\\ApiDemos-debug.apk");
		
		AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		driver.quit();
		
		service.stop();
	
		
	}
}

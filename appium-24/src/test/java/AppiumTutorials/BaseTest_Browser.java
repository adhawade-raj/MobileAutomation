package AppiumTutorials;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest_Browser {

	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	public void appiumServerConfiguration() {
		
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\Raj\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingDriverExecutable(new File ("C:\\Program Files\\nodejs\\node.exe"))
				.withIPAddress("0.0.0.0").usingPort(4723).build();
				
				service.start();
	}
	
	@BeforeClass()
	public void configureAppium() {
		
//		appiumServerConfiguration();
				
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName("Raj_AppiumTest");
				
				System.setProperty("webdriver.chrome.driver", "D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024\\src\\test\\resource\\chromedriver.exe\\chromedriver.exe");
				options.setCapability("browserName", "Chrome");
//				options.setChromedriverExecutable("C:\\Raj Setup\\ChromeDriver\\V 131\\chromedriver-win64\\chromedriver.exe");
				
				 try {
					driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void tearDown() {
//		driver.quit();
//		service.stop();
	}
	
}

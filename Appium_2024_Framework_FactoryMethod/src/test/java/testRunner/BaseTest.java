package testRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	FileInputStream fis;
	
	
	
	public void appiumServerConfiguration() {
		
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\Raj\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingDriverExecutable(new File ("C:\\Program Files\\nodejs\\node.exe"))
				.withIPAddress("0.0.0.0").usingPort(4723).build();
				
				service.start();
	}
	
	@BeforeClass(alwaysRun = true)
	public void configureAppium() {
		
//		appiumServerConfiguration();
		
		Properties prop =new Properties();
		
		try {
			fis = new FileInputStream("D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024_Framework_FactoryMethod\\src\\test\\resource\\TestData\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName(prop.getProperty("deviceName"));
				
				System.setProperty("webdriver.chrome.driver", "D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024_Framework_FactoryMethod\\src\\test\\resource\\TestData\\chromedriver.exe");
//				options.setChromedriverExecutable("C:\\Raj Setup\\ChromeDriver\\V 131\\chromedriver-win64\\chromedriver.exe");
				options.setApp("D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024_Framework_FactoryMethod\\src\\test\\resource\\TestData\\General-Store.apk");
				
				 try {
					driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
//		driver.quit();
//		service.stop();
	}
	
	
	
	
}

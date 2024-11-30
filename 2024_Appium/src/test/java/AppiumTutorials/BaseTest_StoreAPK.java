package AppiumTutorials;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest_StoreAPK {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	public void appiumServerConfiguration() {
		
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:/Users/SHRUTI/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File ("C:\\Program Files\\nodejs\\node.exe"))
				.withIPAddress("0.0.0.0").usingPort(4723).build();
				
				service.start();
	}
	
	@BeforeClass()
	public void configureAppium() {
		
//		appiumServerConfiguration();
				
				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName("Raj_AppiumTest");
				
				System.setProperty("webdriver.chrome.driver", "C:\\Raj Setup\\ChromeDriver\\V 131\\chromedriver-win64\\chromedriver.exe");
//				options.setChromedriverExecutable("C:\\Raj Setup\\ChromeDriver\\V 131\\chromedriver-win64\\chromedriver.exe");
				options.setApp("C:\\Raj Setup\\Mobile_Automation\\2024_Appium\\src\\test\\resource\\General-Store.apk");
				
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
	
	/**
	 * 
	 * @param Productcount
	 * @param productName
	 */
	public void addToCart(int Productcount, String productName) {
		
		for(int i=0; i<Productcount; i++) {
			String productNames = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			if(productNames.equalsIgnoreCase(productName)) {
				System.out.println("Product Name on Page : "+productNames);
				
			driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")).get(i).click();
			}
		}
	}
	
	
	/**
	 * 
	 * @param ele
	 */
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	/**
	 * 
	 */
	public void scrollToEndAction()
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    
			));
		}while(canScrollMore);
	}
	
	/**
	 * 
	 * @param ele
	 * @param direction
	 */
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			 
			    "direction", direction,
			    "percent", 0.75
			));
		
		
	}
	
	/**
	 * 
	 * @param amount
	 * @return
	 */
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
		
	}
	
	
}

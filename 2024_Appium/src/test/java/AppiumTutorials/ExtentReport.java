package AppiumTutorials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		
		String path = "C:\\Raj Setup\\Mobile_Automation\\2024_Appium\\src\\test\\resource\\TestReport\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Result");
		reporter.config().setDocumentTitle("Test Results");	
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Raj");
		
	}
	
	
	public String browserSetup() {
//		System.setProperty("webdriver.chrome.driver", "C:\\Raj Setup\\ChromeDriver\\V 131\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		System.out.println(title);
		
		return title;
	}
	
	@Test
	public void initialDemoOfExtentReport() {
		
		String title = browserSetup();
		
		ExtentTest test = extent.createTest("Initial Demo");
		Assert.assertEquals(title, "google");
//		test.addScreenCaptureFromPath("C:\\Raj Setup\\Mobile_Automation\\2024_Appium\\src\\test\\resource\\TestReport");
//		extent.flush();
	}
	
	@Test
	public void initialDemoOfExtentReport2() {
		
		String title = browserSetup();
		
		ExtentTest test = extent.createTest("Initial Demo");
		Assert.assertEquals(title, "googl");
		extent.flush();
	}
	
	@Test(enabled=false)
	public void initialDemoOfExtentReportWithScreenShot() {
		
		String title = browserSetup();
		
		ExtentTest test = extent.createTest("Initial Demo");
		Assert.assertEquals(title, "googl");
		extent.flush();
	}
	
	
}

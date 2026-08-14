package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lombok.SneakyThrows;

public class AndroidUtils {

	AndroidDriver driver;
	AppiumDriverLocalService service;
	FileInputStream fis;
	Properties prop;
	FileUtils fileUtils;
	String fileSeparator = File.separator;
	String configFilePath = "D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024_Framework\\src\\test\\resource\\TestData\\config.properties"
			.replace("\\", fileSeparator);
	String chromeDriverPath = "D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024_Framework\\src\\test\\resource\\TestData\\chromedriver.exe".replace("\\",
			fileSeparator);
	String mainJSPath = "C:\\Users\\Raj\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"
			.replace("\\", fileSeparator);
	String nodeJSPath = "C:\\Program Files\\nodejs\\node.exe".replace("\\", fileSeparator);;
	String apkFile = "D:\\Eclipse_VSCode\\MobileTesting\\Appium_2024_Framework\\src\\test\\resource\\TestData\\General-Store.apk"
			.replace("\\", fileSeparator);
	String ipAddress;
	String port;

	public AndroidUtils(AndroidDriver driver) {
		this.driver = driver;
		prop = new Properties();
	}

	@SneakyThrows
	public void initConfigProperties2() {
		fis = new FileInputStream(configFilePath);
		prop.load(fis);
		ipAddress = prop.getProperty("ipAddress");
		System.out.println("Ip Address is : " + ipAddress);
		port = prop.getProperty("port");
		System.out.println("Running Port is : " + port);
	}

	@SneakyThrows
	public void initConfigProperties() {
		// Read the file content as a string using FileUtils
		String configContent = FileUtils.readFileToString(new File(configFilePath), "UTF-8");

		// Load the properties using StringReader
		prop = new Properties();
		prop.load(new StringReader(configContent));

		// Retrieve property values
		ipAddress = prop.getProperty("ipAddress");
		System.out.println("IP Address is: " + ipAddress);

		port = prop.getProperty("port");
		System.out.println("Running Port is: " + port);
	}

	/**
	 * To start Appium server Automatically
	 */
	public void appiumServerConfiguration() {

//		initConfigProperties2();
		initConfigProperties();
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File(mainJSPath))
				.usingDriverExecutable(new File(nodeJSPath)).withIPAddress(ipAddress).usingPort(Integer.parseInt(port))
				.build();
		service.start();
	}

	/**
	 * To Initialize driver and apk file
	 * 
	 * @return
	 */
	@SneakyThrows
	public AndroidDriver initDriver() {

//		appiumServerConfiguration();

//		initConfigProperties2();
		initConfigProperties();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("deviceName"));
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		options.setApp(apkFile);

		driver = new AndroidDriver(new URL("http://" + ipAddress + ":" + port), options);
		implicitTimeout(10);
		return driver;
	}

	/**
	 * To Appium server automatically
	 */
	public void tearDownAppiumServer() {
		service.stop();
	}

	public void browserTearDown() {
//			tearDownAppiumServer();
		driver.quit();
	}

	/**
	 * Static wait - Thread.sleep
	 * 
	 * @param value
	 */
	@SneakyThrows
	public void threadSleep(int value) {
		Thread.sleep(value);
	}

	/**
	 * To wait implicitly
	 * 
	 * @param timeout
	 */
	public void implicitTimeout(int implicitTimeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitTimeout));
	}

	/**
	 * PageLoadTimeout
	 * 
	 * @param pageLoadTimeout
	 */
	public void pageLoadTimeout(int pageLoadTimeout) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
	}

	/**
	 * To Capture ScreenShot
	 * 
	 * @param testCaseName
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		// 1. capture and place in folder //2. extent report pick file and attach to
		// report
	}

}

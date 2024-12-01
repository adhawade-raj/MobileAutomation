package com.qa.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.Utils.AndroidActions;
import com.qa.Utils.AndroidUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class FormPage {

	
	private AndroidDriver driver;
	private AndroidActions androidActions;
	private AndroidUtils androidUtils;
	
	public FormPage(AndroidDriver driver) {
		this.driver=driver;
		androidActions = new AndroidActions(driver);
		androidUtils = new AndroidUtils(driver);
	}
	
	private By countryDropdown = AppiumBy.id("android:id/text1");
	private By nameField = AppiumBy.id("com.androidsample.generalstore:id/nameField");
	private By genderRadioButtonMale = AppiumBy.id("com.androidsample.generalstore:id/radioMale");
	private By genderRadioButtonFemale = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
	private By letsShopButton = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");
	private By nextPageTitle = AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");
	private By errorMessage = AppiumBy.xpath("//android.widget.Toast[@text=\"Please enter your name\"]");
	//android.widget.FrameLayout[@resource-id=\"android:id/content\"]
	//android.widget.Toast[@text="Please enter your name"]
	
	public void countrySelection(String value) {
	driver.findElement(countryDropdown).click();
	androidActions.scrollToText(value);
	WebElement countryNames = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\""+value+"\"]"));
	countryNames.click();
	}
	
	public void setName(String name) {
		driver.findElement(nameField).sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void genderSelection(String gender) {
		if(gender.equalsIgnoreCase("male"))
			driver.findElement(genderRadioButtonMale).click();
		else
			driver.findElement(genderRadioButtonFemale).click();
	}
	
	public void letsShopButton() {
		driver.findElement(letsShopButton).click();
	}
	
	public String nextPageTitle() {
		androidUtils.threadSleep(1000);
		return driver.findElement(nextPageTitle).getText();
	}
	
	public String captureErrrorMessage() {
		androidUtils.threadSleep(100);
		return driver.findElement(errorMessage).getDomAttribute("text");
	}
	
	
	
	
	
	
}

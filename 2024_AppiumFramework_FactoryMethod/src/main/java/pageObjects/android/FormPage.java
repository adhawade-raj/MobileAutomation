package pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.android.AndroidActions;

public class FormPage extends AndroidActions {
	
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	
	/**
	 * 
	 * @param name
	 */
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	/**
	 * 
	 * @param gender
	 */
	public void setGender(String gender) {
		if(gender.equalsIgnoreCase("female"))
			femaleOption.click();
		else
			maleOption.click();
	}
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	
	public void setCountrySelection(String countryName) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
    public void submitButton() {
    	shopButton.click();
    }
    
    
    public void setActivity()
	{
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
	
	
}

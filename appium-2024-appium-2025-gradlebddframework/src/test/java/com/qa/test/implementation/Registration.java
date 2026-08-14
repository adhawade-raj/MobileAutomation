package com.qa.test.implementation;

import com.qa.test.hooks.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Registration extends BasePage{

    public Registration() {
        super();  // Calls BasePage constructor which initializes the driver
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
     static WebElement nameField;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
     static WebElement maleOption;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
     static WebElement femaleOption;
    @AndroidFindBy(id="android:id/text1")
     static WebElement countrySelection;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
     static WebElement shopButton;
    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
     static WebElement productPageTitle;

    @AndroidFindBy(xpath="(//android.widget.Toast)[1]")
    static WebElement toastMessage;

    public static void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public static void setGender(String gender) {
        if(gender.equalsIgnoreCase("female"))
            femaleOption.click();
        else
            maleOption.click();
    }

    public static void setCountrySelection(String countryName) {
        androidUtils.threadSleep(2000);
        countrySelection.click();
        androidUtils.scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public static void submitButton() {
        shopButton.click();
    }

    public static String getTitle(){
        androidUtils.threadSleep(1000);
        return productPageTitle.getText();
    }
    public static String getToastMessage(){
        androidUtils.threadSleep(1000);
         return toastMessage.getAttribute("name");
    }
}

package com.qa.test.implementation;

import com.google.inject.Inject;
import com.qa.test.hooks.BasePage;
import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Registration extends BasePage {

    @Inject
    public Registration(AndroidDriver driver, AndroidUtils androidUtils) {
        super(driver, androidUtils);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;
    @AndroidFindBy(id="android:id/text1")
    private WebElement countrySelection;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;
    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    private WebElement productPageTitle;
    @AndroidFindBy(xpath="(//android.widget.Toast)[1]")
    private WebElement toastMessage;



    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if(gender.equalsIgnoreCase("female"))
            femaleOption.click();
        else
            maleOption.click();
    }

    public void setCountrySelection(String countryName) {
        androidUtils.threadSleep(2000);
        countrySelection.click();
        androidUtils.scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public void submitButton() {
        shopButton.click();
    }

    public String getTitle(){
        androidUtils.threadSleep(1000);
        return productPageTitle.getText();
    }

    public String getToastMessage(){
        androidUtils.threadSleep(1000);
        return toastMessage.getAttribute("name");
    }
}
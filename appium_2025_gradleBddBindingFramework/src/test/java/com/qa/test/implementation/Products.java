package com.qa.test.implementation;

import com.google.inject.Inject;
import com.qa.test.hooks.BasePage;
import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import java.util.List;

public class Products  {

    private static AndroidDriver driver;
    private static AndroidUtils androidUtils;

    @Inject
    public Products(AndroidDriver driver, AndroidUtils androidUtils) {
        this.driver = driver;
        this.androidUtils=androidUtils;
    }

    @AndroidFindBy(id="\"com.androidsample.generalstore:id/productName\"")
    private List<WebElement> products;

    @AndroidFindBy(id="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")
    private List<WebElement> productCart;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private List<WebElement> productsize;
    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;
    @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    static WebElement toastMessage;

    public void addItemToCartByIndex(int index)
    {
        addToCart.get(index).click();
    }

    public void addToCart(String productName) {

        androidUtils.scrollToText(productName);
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0; i<productCount; i++) {
            String productNames = products.get(i).getText();
            System.out.println(productNames);
            if(productNames.equalsIgnoreCase(productName)) {
                System.out.println("Product Name on Page : "+productNames);
                productCart.get(i).click();
            }
        }
    }

    public void goToCartPage() {
        cart.click();
    }
    public static String getToastMessage(){
        androidUtils.threadSleep(1000);
        return toastMessage.getAttribute("name");
    }

}

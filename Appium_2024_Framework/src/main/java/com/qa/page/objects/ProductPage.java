package com.qa.page.objects;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.utils.AndroidActions;
import com.qa.utils.AndroidUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductPage {

	
	AndroidDriver driver;
	AndroidUtils androidUtils;
	AndroidActions androidActions;
	
	public ProductPage(AndroidDriver driver) {
		this.driver=driver;
		androidUtils = new AndroidUtils(driver);
		androidActions = new AndroidActions(driver);
	}
	
	private By prodNames = AppiumBy.id("com.androidsample.generalstore:id/productName");
	private By addToCart = AppiumBy.id("com.androidsample.generalstore:id/productAddCart");
	private By addTocartCount = AppiumBy.id("com.androidsample.generalstore:id/counterText");
	private By addToCartState = AppiumBy.id("com.androidsample.generalstore:id/productAddCart");
	private By shopButton = AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");
	
	
	
	
	/**
	 * To select the product using Java Stream(IntStream)
	 * @param productName
	 */
	public void productSelection_AddToCart_WithStream(String productName) {
		androidActions.scrollToText(productName);
		List<WebElement> productNames = driver.findElements(prodNames);
		
		IntStream.range(0, productNames.size())
		.filter(i -> productNames.get(i).getText().equals(productName))
		.findFirst()
		.ifPresent(i ->{
			System.out.println("Selected Product is : "+productName);
			List<WebElement> addToCartButton = driver.findElements(addToCart);
			addToCartButton.get(i).click();
		});
		
		}
	
	/**
	 * To Select multiple products
	 * @param productNames
	 */
	public void addMultipleProductsToCartUsingStream(String[] productNames) {
        for (String productName : productNames) {
        	productSelection_AddToCart_WithStream(productName);
        }
	}
	
		
	/**
     * Add a single product to the cart by its name.
     * @param productName Name of the product to add to the cart.
     */
    public void productSelection_AddToCart(String productName) {
        androidActions.scrollToText(productName);
        List<WebElement> productNames = driver.findElements(prodNames);

        for (int i = 0; i < productNames.size(); i++) {
            String prodName = productNames.get(i).getText();

            if (prodName.equals(productName)) {
                System.out.println("Adding product to cart: " + productName);

                // Click corresponding "Add to Cart" button
                List<WebElement> addToCartButtons = driver.findElements(addToCart);
                addToCartButtons.get(i).click();
                return;
            }
        }

        throw new RuntimeException("Product not found: " + productName);
    }
    
//    	In productSelection_AddToCart:
//    	Use return because the method has no reason to continue executing after adding the product.
//    	Use break only if you plan to execute additional logic after the loop, which isn't the case here.
	

	/**
	 * To Select multiple products
	 * @param productNames
	 */
	public void addMultipleProductsToCart(String[] productNames) {
        for (String productName : productNames) {
        	productSelection_AddToCart(productName);
        }
	}
	

	
	/**
	 * To Verify the state the of cart button after adding the products in cart
	 * @return
	 */
	public String verifyCartButtonState() {
		return driver.findElement(addToCartState).getDomAttribute("text");
	}
	
	/*
	 * To Verify selected product count
	 */
	public int verifyCountProduct() {
		String prodCount = driver.findElement(addTocartCount).getDomAttribute("text");
		return Integer.parseInt(prodCount);
	}
	
	public void shopButton() {
		driver.findElement(shopButton).click();
	}
	
}
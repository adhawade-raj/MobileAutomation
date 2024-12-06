package com.qa.PageObjects;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.Utils.AndroidActions;
import com.qa.Utils.AndroidUtils;

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
	
	
	public void productSelection_AddToCart_WithStream(String[] productName) {
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
	 * To Select Product and add to cart without stream(Using for loop)
	 * @param productName
	 */
	public void productSelection_AddToCart(String productName) {
		
		androidActions.scrollToText(productName);
		List<WebElement> productNames = driver.findElements(prodNames);
		for(int i=0; i<productNames.size(); i++) {
			String prodName = productNames.get(i).getText();
			if(prodName.equals(productName)) {
				System.out.println("Selected Product is : "+productName);
				List<WebElement> addToCartButton = driver.findElements(addToCart);
				addToCartButton.get(i).click();
				break;
			}
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

package com.qa.page.objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.utils.AndroidActions;
import com.qa.utils.AndroidUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartPage {

	
	AndroidDriver driver;
	AndroidUtils androidUtils;
	AndroidActions androidActions;
	
	public CartPage(AndroidDriver driver) {
		this.driver=driver;
		androidUtils = new AndroidUtils(driver);
		androidActions = new AndroidActions(driver);
	}
	
	
	private By productList = AppiumBy.id("com.androidsample.generalstore:id/rvCartProductList");
	private By productName = AppiumBy.id("com.androidsample.generalstore:id/productName");
	private By productPrice = AppiumBy.id("com.androidsample.generalstore:id/productPrice");
	private By totalBill = AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl");
	private By checkBox = AppiumBy.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]");
	private By proceedButton = AppiumBy.id("com.androidsample.generalstore:id/btnProceed");
	
	
	public void verifyNameOfProducts() {
		
	}
	
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
		
	}
	
	public double totalOfProducts() {
		
		double sum=0;
		List<WebElement> prodPrice = driver.findElements(productPrice);
		for(WebElement e: prodPrice) {
			String productPrice = e.getDomAttribute("text");
			double prodPriceFormatted = getFormattedAmount(productPrice);
			sum = sum + prodPriceFormatted;
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Expected total of selected products is : "+sum);
		return sum;
	}

	
	public double captureTotalBillFromCart() {
	
		String totalBillOfProducts = driver.findElement(totalBill).getDomAttribute("text");
		double actualTotalBill = getFormattedAmount(totalBillOfProducts);
		System.out.println("Actual total of selected product is : "+actualTotalBill);
		System.out.println("--------------------------------------------------");
		return actualTotalBill;
	}
	
	public void selectCheckBox() {
		driver.findElement(checkBox).click();
	}
	
	public void proceedButton() {
		driver.findElement(proceedButton).click();
		androidUtils.threadSleep(100);
	}
	
	
}

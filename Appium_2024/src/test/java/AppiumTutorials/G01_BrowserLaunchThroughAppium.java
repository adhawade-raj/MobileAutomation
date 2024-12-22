package AppiumTutorials;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class G01_BrowserLaunchThroughAppium extends BaseTest_Browser{

	
	
	@Test
	public void browserTest() {
		
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("Raj Thakrey");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}
}

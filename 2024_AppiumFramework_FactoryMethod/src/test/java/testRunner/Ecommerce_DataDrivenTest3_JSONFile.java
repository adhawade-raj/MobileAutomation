package testRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.android.FormPage;
import utils.android.AppiumUtils;

public class Ecommerce_DataDrivenTest3_JSONFile extends BaseTest {

	AppiumUtils appiumUtils = new AppiumUtils();
	
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup()
	{
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
	}
	
	
	@Test(dataProvider="getData")
	public void dataDrivenTest_WithJSONFile(HashMap<String,String> input) throws InterruptedException{
		FormPage formPage = new FormPage(driver);
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));

		formPage.submitButton();
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>>	data = appiumUtils.getJsonData(System.getProperty("user.dir")+"C:/Raj Setup/Mobile_Automation/2024_AppiumFramework/src/test/resource/TestData/eCommerce.json");
		return new Object[][] { {data.get(0)},{data.get(1)}  };
	}

	//key-name ,value - Raj
	// {   {hash},   {hash}    }  
		
	}


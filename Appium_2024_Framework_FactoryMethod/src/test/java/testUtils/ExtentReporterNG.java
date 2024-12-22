package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG {
	
	
	static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		
		String path = "C:\\Raj Setup\\Mobile_Automation\\2024_AppiumFramework\\src\\test\\resource\\TestReports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		 extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Raj Adhawade");
		return extent;
	}
	
	

}

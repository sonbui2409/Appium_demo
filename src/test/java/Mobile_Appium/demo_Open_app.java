package Mobile_Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import AppiumBaseCommon.BaseTest;
import AppiumObject.VnExpressObject;


public class demo_Open_app extends BaseTest {
	
	//AppiumDriver<WebElement> driver;
	//DesiredCapabilities caps; //env configuration
	VnExpressObject vn = new VnExpressObject(driver, log);

	@Test (priority = 1)
	public void Demo_scroll() {
		log = report.createTest("Scroll to Element");	
		vn.ClickByPass();
		vn.ScrollToPost("DOWN", "Khám");
		vn.ClickToTitle("Khám");
		
	}
	

}

package Mobile_Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import AppiumBaseCommon.BaseTest;
import AppiumObject.VnExpressObject;


public class Demo_appium extends BaseTest {
	
	//AppiumDriver<WebElement> driver;
	//DesiredCapabilities caps; //env configuration
	VnExpressObject vn;

	@Test (priority = 1)
	public void Demo_scroll() {
		log = report.createTest("Scroll to Element");	
		vn = new VnExpressObject(driver, log);
		vn.ClickByPass();
		vn.ScrollToPost("DOWN", "Khám");
		vn.ClickToTitle("Khám");
		
	}
	

}

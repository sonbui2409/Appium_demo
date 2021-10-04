package AppiumObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import AppiumBaseCommon.BaseActions;
import io.appium.java_client.AppiumDriver;

public class VnExpressObject extends BaseActions {

	String Bypass = "fr.playsoft.vnexpress:id/tab_podcast";
	String Title = "//android.widget.TextView[contains(@text,'%s')]";
	AppiumDriver<WebElement> driver;
	public VnExpressObject(AppiumDriver<WebElement> remoteDriver, ExtentTest remoteLog) {
		super(remoteDriver, remoteLog);
		this.driver = remoteDriver;
		// TODO Auto-generated constructor stub
	}
	public void ScrollToPost (String dir, String key) {
		scrollToEle(String.format(Title, key), dir);
	}
	public void ClickByPass() {
		ClickId(Bypass);
	}
	public void ClickToTitle(String key) {
		ClickXpath(String.format(Title, key));
	}
}

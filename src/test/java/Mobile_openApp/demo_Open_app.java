package Mobile_openApp;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class demo_Open_app {
	
	AppiumDriver<WebElement> driver;
	DesiredCapabilities caps; //env configuration
	
	
	@BeforeTest
	public void setUp() {
		
		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		//caps.setCapability(MobileCapabilityType.APP, "");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		
		caps.setCapability(MobileCapabilityType.NO_RESET, true);
		caps.setCapability(MobileCapabilityType.FULL_RESET, false);
		
		
		caps.setCapability("appPackage", "com.google.android.calculator");
		caps.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		try {
			driver = new AppiumDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Can not Start Driver");
		}
	
	}
	
	
	@Test
	public void OpenApp() {
		System.out.println("Open Calculator App");
	
	}
	
	

}

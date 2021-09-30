package Mobile_Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class demo_Browser_App {
	
	AppiumDriver<WebElement> driver;
	DesiredCapabilities caps; //env configuration
	
	
	@BeforeTest
	public void setUp() {
		
		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + File.separator 
				+ "\\src\\APK\\Google Calendar_v2021.35.3.apk");  //path for app file
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		
		caps.setCapability(MobileCapabilityType.NO_RESET, true); //allow to Reset
		caps.setCapability(MobileCapabilityType.FULL_RESET, false); //Clear Data
		
		
		caps.setCapability("appPackage", "com.socialnmobile.dictapps.notepad.color.note");
		//caps.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		try {
			driver = new AppiumDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Can not Start Driver");
		}
	
	}
	
	
	//@Test (priority = 1)
	public void makeSomeCalc() {
		driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_0")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
		driver.findElement(By.id("com.google.android.calculator:id/eq")).click();
		WebElement result = driver.findElement(By.id("com.google.android.calculator:id/result_final"));
		Assert.assertEquals("20", result.getText());
		
		System.out.println("Test Case is Passed");
	
	}
	
	@Test (priority = 1)
	public void opencalendar() {
		System.out.println("Done");
		
		
	}
	

}

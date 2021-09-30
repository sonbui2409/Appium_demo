package Mobile_Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.collections.functors.WhileClosure;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

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
		caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\src\\APK\\VnExpress.apk");  //path for app file
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "500");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		
		caps.setCapability(MobileCapabilityType.NO_RESET, false); //allow to Reset
		caps.setCapability(MobileCapabilityType.FULL_RESET, true); //Clear Data
		
		//caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome"); //open browser
		//caps.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "\\src\\driver\\chromedriver.exe"); //Browser driver path
		
		caps.setCapability("appPackage", "fr.playsoft.vnexpress");
		caps.setCapability("appActivity", "fr.playsoft.vnexpress.ActivityMain");
		
		try {
			driver = new AppiumDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Can not Start Driver");
		}
	
	}
	
	public void swipeHalfScreen(AppiumDriver<WebElement> driver, String direction) {								
		Dimension size = driver.manage().window().getSize();				 //get screen size		
		
		int startX = 0;						
		int endX = 0;						
		int startY = 0;						
		int endY = 0;						
								
		TouchAction touch = new TouchAction(driver);	
		
		direction =direction.toUpperCase();
		
		switch (direction) {						
	
		case "RIGHT":						
			startY = (int) (size.height / 2);					
			startX = (int) (size.width * 0.9);					
			endX = (int) (size.width * 0.05);					
			touch.press(PointOption.point(startX, startY)) // tap vao diem anchor, startPoint					
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))) // wait 3 s : scroll roi dung khoang 3s			
					.moveTo(PointOption.point(endX, startY)).release().perform();			
								
			break;		
			
		case "LEFT":						
			startY = (int) (size.height / 2);					
			startX = (int) (size.width * 0.05);					
			endX = (int) (size.width * 0.9);					
			touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))					
					.moveTo(PointOption.point(endX, startY)).release().perform();			
								
			break;		
			
		case "UP":	
	
			endY = (int) (size.height * 0.7);					
			startY = (int) (size.height * 0.3);					
			startX = (size.width / 2);					
			touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))					
					.moveTo(PointOption.point(startX, endY)).release().perform();			
								
			break;					
		case "DOWN":						
			startY = (int) (size.height * 0.7);					
			endY = (int) (size.height * 0.3);					
			startX = (size.width / 2);					
			touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))					
					.moveTo(PointOption.point(startX, endY)).release().perform();			
								
			break;					
		}						
	}
	
	
	@Test (priority = 1)
	public void Demo_scroll() {
		driver.findElement(By.id("fr.playsoft.vnexpress:id/tab_podcast")).click();
		
		while (driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'Đề')]")).size()<1) {
			swipeHalfScreen(driver, "DOWN");
		}
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Đề')]")).click();
		
	}
	

}

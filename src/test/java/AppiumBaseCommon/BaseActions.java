package AppiumBaseCommon;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class BaseActions {
	
	ExcelInit reader;
	AppiumDriver<WebElement> driver;
	ExtentTest log;
	
	public BaseActions(AppiumDriver<WebElement> remoteDriver,ExtentTest remoteLog ) {
		driver = remoteDriver; //create driver variable in the class so do not need to define it again
		log = remoteLog;
		reader = new ExcelInit();
	}
	
	public void updateLog(ExtentTest LocalLog) {
		log = LocalLog;
	}

	private String get_EleDescription(WebElement ele) {
		String des ="";
		des = ele.getAttribute("id");
		if(isNullOrEmpty(des)) {
			des= ele.getAttribute("name");
			if(isNullOrEmpty(des)) {
				des = ele.getText();
				if(isNullOrEmpty(des)) {
					des = ele.getAttribute("class");
				}
			}
		}
		return des;
	}
	
	private boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

	protected void GotoURL(String url) {
		driver.get(url);
		//System.out.println("Go to URL " + url);
		log.info("Go to URL " + url );		
	}
	//Input Data
	protected void InputdataXpath (String xpath, String data) {
		WebElement field = driver.findElement(By.xpath(xpath));
		field.sendKeys(data);
		get_EleDescription(field);
		log.info("Input " + data + " to field " + get_EleDescription(field));
	}
	
	protected void InputdataId (String id, String data) {
		WebElement field = driver.findElement(By.id(id));
		field.sendKeys(data);
		log.info("Input " + data + " to " + get_EleDescription(field));
	}
	protected void InputdataClass (String classname, String data) {
		WebElement field = driver.findElement(By.className(classname));
		field.sendKeys(data);
		log.info("Input " + data + " to " + get_EleDescription(field));
	}
	protected void ClearDataxpath(String xpath) {
		WebElement field = driver.findElement(By.xpath(xpath));
		field.clear();
		log.info("Clear Data of " + get_EleDescription(field) + " Field");
	}
	//Click
	protected void ClickXpath(String xpath) {
		WebElement field = driver.findElement(By.xpath(xpath));
		log.info("Click to " + get_EleDescription(field));
		field.click();
	}
	protected void ClickId(String id) {
		WebElement field = driver.findElement(By.id(id));
		log.info("Click to " + get_EleDescription(field));
		field.click();
		
	}
	protected void ClickFormat (String xpath, String item) {
		String text = String.format(xpath, item);
		WebElement field = driver.findElement(By.xpath(text));
		log.info("Click to " + get_EleDescription(field));
		field.click();	
	}
	//Get Text
	protected void GetTextFormatEqual (String xpath, String item, String compare) {
		String text = String.format(xpath, item);
		WebElement field = driver.findElement(By.xpath(text));
		Assert.assertTrue(field.getText().equalsIgnoreCase(compare));
		log.info("Get text of " + get_EleDescription(field) + " and Compare to " + compare);
	}
	protected void GetTextEqual (String xpath, String compare) {
		WebElement field = driver.findElement(By.xpath(xpath));
		Assert.assertTrue(field.getText().equalsIgnoreCase(compare));
		log.info("Get text of " + get_EleDescription(field) + " and Compare to " + compare);
	}
	protected void GetText (String xpath) {
		WebElement field = driver.findElement(By.xpath(xpath));
		field.getText();
		log.info("Get text of " + get_EleDescription(field));
	}
	protected void GetTextFormatEqual_replace (String xpath, String item, String compare) {
		String text = String.format(xpath, item);
		WebElement field = driver.findElement(By.xpath(text));
		Assert.assertTrue(field.getText().replace("\r" + "\n", "").equalsIgnoreCase(compare));
		log.info("Get text of " + get_EleDescription(field) + " and Compare to " + compare);
	}
	protected void CompareCurrentURL (String compareUrl) {
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(compareUrl));
		log.info("The current URL is: " + compareUrl);
	}
	
	
	//Assert and softAssert
	protected void sAssertDisplay_true (SoftAssert softAssert,String xpath) {
		WebElement field = driver.findElement(By.xpath(xpath));
		softAssert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
		log.info("Verify " + get_EleDescription(field) + " is displayed");
	
	}
	protected void AssertDisplay_true (String xpath) {
		WebElement field = driver.findElement(By.xpath(xpath));
		Assert.assertTrue(field.isDisplayed());
		log.info("Verify " + get_EleDescription(field) + " is displayed");
	
	}
	protected void sAssertDisplay_false (SoftAssert softAssert,String xpath) {
		WebElement field = driver.findElement(By.xpath(xpath));
		softAssert.assertFalse(field.isDisplayed());
		log.info("Verify " + get_EleDescription(field) + " is not displayed");
		
	
	}
	protected void sAssertEqual (SoftAssert softAssert,String xpath, String expect) {
		WebElement field = driver.findElement(By.xpath(xpath));
		softAssert.assertEquals(field.getText(), expect);
		log.info("Verify " + get_EleDescription(field) + " equal to " + expect);
	}
	protected void sAssertEqual_replace(SoftAssert softAssert,String xpath, String expect) {
		WebElement field = driver.findElement(By.xpath(xpath));
		softAssert.assertEquals(field.getText().replace("\r" + "\n", ""), expect);
		//"\r" a space, "\n" a new line
		log.info("Verify " + get_EleDescription(field) + " equal to " + expect);
	}
	protected void AssertEqual (String xpath, String expect) {
		WebElement field = driver.findElement(By.xpath(xpath));
		Assert.assertEquals(field.getText(), expect);
		log.info("Verify " + get_EleDescription(field) + " equal to " + expect);
	}
	protected void AssertEqual_replace(String xpath, String expect) {
		WebElement field = driver.findElement(By.xpath(xpath));
		Assert.assertEquals(field.getText().replace("\r" + "\n", ""), expect);
		//"\r" a space, "\n" a new line
		log.info("Verify " + get_EleDescription(field) + " equal to " + expect);
	}
	//Wait
	protected void waitclickable(WebDriverWait wait, String xpath) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	protected void waitformatclickable(WebDriverWait wait, String xpath, String item) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(xpath, item))));
	}
	protected void waitvisible(WebDriverWait wait, String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	protected void waitformatvisible(WebDriverWait wait, String xpath, String item) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, item))));
	}
	protected void softAssertAll (SoftAssert softAssert) {
		softAssert.assertAll();
	}
	//Read Excel
	
	public String readExl(String sheetname,int icol, int irow) {
		String data = reader.RDataAtCell(BaseTest.fileName, sheetname, icol, irow);
		return data;
	}
	public ArrayList<String> lstData(String sheetname,int icol){
		ArrayList<String> data = reader.readExcelFileAtColumn(BaseTest.fileName, sheetname, icol);
		return data;
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
	
	public List<WebElement> ListElement(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	public void scrollToEle(String xpath, String dir) {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS); //set timeout for driver when find element, should be set to default after used
		while (ListElement(xpath).size()<1) {
			swipeHalfScreen(driver,dir);
		}
	}
}

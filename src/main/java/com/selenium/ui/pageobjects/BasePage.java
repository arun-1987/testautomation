package com.selenium.ui.pageobjects;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Reporter;

import com.selenium.connectors.JsonConnector;
import com.selenium.ui.helper.ExceptionHelper;
import com.selenium.ui.helper.LoggerHelper;
import com.selenium.ui.helper.WaitHelper;
import com.selenium.ui.testbase.TestBase;

public class BasePage {
	
	private  Logger logger = LoggerHelper.getLogger(this.getClass());
	protected WebDriver driver;
	WaitHelper waithelper;
	
	
	private static int explicitwait = Integer.parseInt(JsonConnector.getConfig("explicitwait")==null?"10":JsonConnector.getConfig("explicitwait"));
	private static int implicitwait = Integer.parseInt(JsonConnector.getConfig("implicitwait")==null?"0":JsonConnector.getConfig("implicitwait"));
	private static int pageloadwait = Integer.parseInt(JsonConnector.getConfig("pageloadwait")==null?"10":JsonConnector.getConfig("pageloadwait"));
	private static int pollingtime = Integer.parseInt(JsonConnector.getConfig("pollingtime")==null?"10":JsonConnector.getConfig("pollingtime"));
	
	public BasePage() throws Exception{
		driver = TestBase.getDriver();
		waithelper = new WaitHelper();
	}
	
	public void launchApplication(final String url) {
		Objects.requireNonNull(driver,"Driver object is null...");
    	driver.get(url);
    	waithelper.pageLoadTimeOut(driver, 20, TimeUnit.SECONDS);
	}
	
	
	public void performClick(By locator)throws ExceptionHelper {
		try {
			waithelper.waitForElementClickable(driver,locator,explicitwait, pollingtime).click();
	}catch(Exception e) {
		logger.error("Exception thrown in performClick function" +e);
		throw new ExceptionHelper(e.getLocalizedMessage());
		}
	}
	
	public void performSubmit(By locator)throws ExceptionHelper {
		try {
			waithelper.waitForElementClickable(driver,locator,explicitwait, pollingtime).submit();
	}catch(Exception e) {
		logger.error("Exception thrown in performSubmit function" +e);
		throw new ExceptionHelper(e.getLocalizedMessage());
		}
	}
	
	public void performEnterText(By locator,String value) throws ExceptionHelper {
		try {
		waithelper.waitForElementVisible(driver,locator, explicitwait, pollingtime).sendKeys(value);
		}catch(Exception e) {
		logger.error("Exception thrown in performEnterText function" +e);
		throw new ExceptionHelper(e.getLocalizedMessage());
		}
	}
	
	public void checkTitleMatch(final String searchString) throws ExceptionHelper{
		try {
			waithelper.waitUntilTitleMatches(driver, searchString);
		}catch(Exception e) {
			logger.error("Title does not match Expected is : "+searchString+" Actual is : "+ e);
			Reporter.log("Title does not match Expected is : "+searchString+" Actual is : "+ e);
			throw new ExceptionHelper(e.getLocalizedMessage());
		}
	}
	
	public void checkElementIsDisplayed(By locator) throws ExceptionHelper{
		try {
			waithelper.isDisplayedUsingAwait(driver, locator);
		}catch(Exception e) {
			logger.error("Element is not displayed : "+ e);
			Reporter.log("Element is not displayed : "+ e);
			throw new ExceptionHelper(e.getLocalizedMessage());
		}
	}
	

}

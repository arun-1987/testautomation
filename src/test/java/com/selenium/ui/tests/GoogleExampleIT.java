package com.selenium.ui.tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.global.dataprovider.JsonDataProvider;
import com.selenium.ui.pageobjects.GoogleHomePage;
import com.selenium.ui.testbase.TestBase;

public class GoogleExampleIT extends TestBase {



	@Test(dataProvider = "TestData", dataProviderClass = JsonDataProvider.class)
	public void googleCheeseExample(String rowID, String description, JSONObject data) throws Exception {
		GoogleHomePage googleHomePage = new GoogleHomePage();
		googleHomePage.LaunchApplication();
		googleHomePage.enterSearchTerm(data.get("SearchTerm").toString())
					.submitSearch()
					.verifyTitle("Cheese")
					.verifyElement();
	}
	/*
	 * @Test public void googleMilkExample() throws Exception { WebDriver driver =
	 * getDriver(); driver.get("http://www.google.com"); GoogleHomePage
	 * googleHomePage = new GoogleHomePage(); System.out.println("Page title is: " +
	 * driver.getTitle()); googleHomePage.enterSearchTerm("Milk").submitSearch();
	 * WebDriverWait wait = new WebDriverWait(driver, 10, 100);
	 * wait.until(pageTitleStartsWith("Milk")); System.out.println("Page title is: "
	 * + driver.getTitle()); }
	 * 
	 * @Test public void googleCurdExample() throws Exception { WebDriver driver =
	 * getDriver(); driver.get("http://www.google.com"); GoogleHomePage
	 * googleHomePage = new GoogleHomePage(); System.out.println("Page title is: " +
	 * driver.getTitle()); googleHomePage.enterSearchTerm("Butter").submitSearch();
	 * WebDriverWait wait = new WebDriverWait(driver, 10, 100);
	 * wait.until(pageTitleStartsWith("Butter"));
	 * System.out.println("Page title is: " + driver.getTitle()); }
	 * 
	 * @Test public void googleButterExample() throws Exception { WebDriver driver =
	 * getDriver(); driver.get("http://www.google.com"); GoogleHomePage
	 * googleHomePage = new GoogleHomePage(); System.out.println("Page title is: " +
	 * driver.getTitle()); googleHomePage.enterSearchTerm("Butter").submitSearch();
	 * WebDriverWait wait = new WebDriverWait(driver, 10, 100);
	 * wait.until(pageTitleStartsWith("Curd")); System.out.println("Page title is: "
	 * + driver.getTitle()); }
	 */
}
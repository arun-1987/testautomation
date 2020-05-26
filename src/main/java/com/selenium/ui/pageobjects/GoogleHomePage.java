package com.selenium.ui.pageobjects;



import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.connectors.JsonConnector;
import com.selenium.ui.helper.ExceptionHelper;
import com.selenium.ui.reporter.ExtentListener;
import com.selenium.ui.reporter.ThreadSafeExtent;
import com.selenium.ui.testbase.TestBase;


public class GoogleHomePage extends BasePage{


	
    public GoogleHomePage() throws Exception {
		super();
	}

	private By searchBar = By.name("q");
    private By googleSearch = By.name("btnK");


    public void LaunchApplication()throws ExceptionHelper {
    	String appurl = JsonConnector.getConfig("applicationurl");
    	appurl = System.getProperty("applicationurl")!=null?System.getProperty("applicationurl"):appurl;
    	launchApplication(appurl);
    }
    

    public GoogleHomePage enterSearchTerm(String searchTerm) throws ExceptionHelper {
    	Reporter.log("enterSearchTerm for the string "+searchTerm);
    	performEnterText(searchBar,searchTerm);
        return this;
    }

    public GoogleHomePage submitSearch() throws ExceptionHelper {
    	performSubmit(googleSearch);
        return this;
    }

    public GoogleHomePage verifyTitle(String title) throws ExceptionHelper {
    	checkTitleMatch(title);
        return this;
    }
   
    public GoogleHomePage verifyElement() throws ExceptionHelper {
    	checkElementIsDisplayed(By.xpath("//a/h3[.='Cheese - Wikipedia']"));
        return this;
    }
    
  
    
    
}
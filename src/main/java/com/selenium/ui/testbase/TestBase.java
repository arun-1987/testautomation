package com.selenium.ui.testbase;



import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.selenium.connectors.JsonConnector;
import com.selenium.ui.browser.ChromeService;
import com.selenium.ui.browser.DriverFactoryManager;
import com.selenium.ui.helper.LoggerHelper;
import com.selenium.ui.listener.ReportListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners({ReportListener.class})
public class TestBase {

    private static List<DriverFactoryManager> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactoryManager>());
    private static ThreadLocal<DriverFactoryManager> driverFactoryThread;
    private static Boolean executiontype;
    private static Logger logger = LoggerHelper.getLogger(TestBase.class);

    /**
     * 
     */
    @BeforeSuite(alwaysRun = true)
    public void instantiateDriverObject() {
    	executiontype = Boolean.valueOf(System.getProperty("executiontypeweb"));
    	executionSetUpWeb(executiontype);
    }

    /**
     * 
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
    	if(executiontype) {
        try {
            driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
        	logger.error("Unable to clear cookies, driver object is not viable...");
        }
    	}
    }

    /**
     * 
     */
    @AfterSuite(alwaysRun = true)
    public void closeDriverObjects() {
    	if(executiontype) {
    		webDriverThreadPool.forEach(driver->driver.quitDriver());
    	}
    }
    
    /**
     * 
     */
    public static void checkIfChromeServiceEnabled() {
    	if(Boolean.valueOf(JsonConnector.getConfig("chromeDriverService")))
    		ChromeService.startService();
    }
    
    /**
     * 
     */
    public static void executionSetUpWeb(Boolean flag) {
    	if(flag) {
	    	checkIfChromeServiceEnabled();
	    	driverFactoryThread = ThreadLocal.withInitial(() -> {
	        	DriverFactoryManager driverFactory = new DriverFactoryManager();
	            webDriverThreadPool.add(driverFactory);
	            return driverFactory;
	        });
    	}else {
    		logger.info("Execution type is set to API in Maven POM Properties."
    				+ "For UI Execution set the property flag executiontypeweb to true");
    	}
    }
        
    /** 
     * 
     * @return
     * @throws Exception
     */
    public static RemoteWebDriver getDriver() throws Exception {
    		return driverFactoryThread.get().getDriver();

    }
}
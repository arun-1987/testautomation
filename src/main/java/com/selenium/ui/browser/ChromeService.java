package com.selenium.ui.browser;

import java.io.File;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import com.selenium.ui.helper.LoggerHelper;



public class ChromeService  {
	protected static ChromeDriverService chService;
	private  static Logger logger = LoggerHelper.getLogger(ChromeService.class);
	    public static  ChromeDriverService startService() {
	        if (null == chService) {
	            try {
	                chService = new ChromeDriverService.Builder()
	                    .usingDriverExecutable(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe"))
	                    .usingAnyFreePort()
	                    .withSilent(true)
	                    .build();
	                chService.start();
	                logger.info("ChromeDriver Service is started...");
	               
	            } catch (Exception e) {
	               logger.error("Error in starting chrome driver service",e);
	            }
	        }
			return chService;
	    }


	    public void stopService() {
	        if (null != chService && chService.isRunning())
	            chService.stop();
	    }
	    
	    public static ChromeOptions getChromeOptions() {	    	
	    	ChromeOptions options = new ChromeOptions();
	    	options.addArguments("start-maximized");
			return options;
	    	
	    }

}

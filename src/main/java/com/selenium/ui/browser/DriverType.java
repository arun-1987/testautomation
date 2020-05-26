package com.selenium.ui.browser;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.selenium.ui.helper.ResourceHelper;

public enum DriverType implements DriverSetup {

	CHROME {
		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourceHelper("/src/main/resources/drivers/chromedriver.exe"));
			System.setProperty("webdriver.chrome.silentOutput","true");
			HashMap<String, Object> chromePreferences = new HashMap<>();
			chromePreferences.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.merge(capabilities);
			options.setHeadless(HEADLESS);
			options.addArguments("--no-default-browser-check");
			options.addArguments("--silent");
			options.setExperimentalOption("prefs", chromePreferences);
			return new ChromeDriver(options);
		}
	},

	FIREFOX {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capabilities);
			options.setHeadless(HEADLESS);
			return new FirefoxDriver(options);
		}

	},

	SAFARI {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			// TODO Auto-generated method stub
			return null;
		}

	},

	API {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			// TODO Auto-generated method stub
			return null;
		}

	},

	APPIUM {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public final static boolean HEADLESS = Boolean.getBoolean("headless");

}

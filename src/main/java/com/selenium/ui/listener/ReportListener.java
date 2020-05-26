package com.selenium.ui.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.selenium.ui.helper.ResourceHelper;
import com.selenium.ui.reporter.ExtentManager;
import com.selenium.ui.reporter.ThreadSafeExtent;
import com.selenium.ui.testbase.TestBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportListener extends TestListenerAdapter {

	public static String screenshotpath;

	private boolean createFile(File screenshot) {
		boolean fileCreated = false;

		if (screenshot.exists()) {
			fileCreated = true;
		} else {
			File parentDirectory = new File(screenshot.getParent());
			if (parentDirectory.exists() || parentDirectory.mkdirs()) {
				try {
					fileCreated = screenshot.createNewFile();
				} catch (IOException errorCreatingScreenshot) {
					errorCreatingScreenshot.printStackTrace();
				}
			}
		}

		return fileCreated;
	}

	private void writeScreenshotToFile(WebDriver driver, File screenshot) {
		try {
			FileOutputStream screenshotStream = new FileOutputStream(screenshot);
			screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			screenshotStream.close();
		} catch (IOException unableToWriteScreenshot) {
			System.err.println("Unable to write " + screenshot.getAbsolutePath());
			unableToWriteScreenshot.printStackTrace();
		}
	}

	private String getScreenShotInBase64(WebDriver driver) {
		try {
			screenshotpath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			System.err.println("Unable to take screenshot " + screenshotpath);
		}
		return screenshotpath;
	}

	
	public static ExtentReports extentreports;
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extentreports.createTest(result.getMethod().getMethodName());
		ThreadSafeExtent.setTest(test);
	}

	public void onTestSuccess(ITestResult result) {	
		Reporter.getOutput(result).forEach(item -> ThreadSafeExtent.getTest().info(item));
		ThreadSafeExtent.getTest().log(Status.PASS, result.getName() + " Passed...");
	}
	
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		extentreports = ExtentManager.getInstance();
	}

	public void onFinish(ITestContext context) {
		extentreports.flush();
	}

	
	
	@Override
	public void onTestFailure(ITestResult failingTest) {
		try {
			WebDriver driver = TestBase.getDriver();
			String screenshotDirectory = System.getProperty("screenshotDirectory",
					"src/main/resources/report/screenshots");
			String screenshotAbsolutePath = screenshotDirectory + "/" + System.currentTimeMillis() + "_"
					+ failingTest.getName() + ".png";
			String screenshotpath = getScreenShotInBase64(driver);
			File screenshot = new File(screenshotAbsolutePath);
			if (createFile(screenshot)) {
				try {
					writeScreenshotToFile(driver, screenshot);
					Reporter.getOutput(failingTest).forEach(item -> ThreadSafeExtent.getTest().info(item));
					ThreadSafeExtent.getTest().log(Status.FAIL, failingTest.getThrowable().getMessage(),
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotpath).build());
				} catch (ClassCastException weNeedToAugmentOurDriverObject) {
					writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
				}
				System.out.println("Written screenshot to " + screenshotAbsolutePath);
			} else {
				System.err.println("Unable to create " + screenshotAbsolutePath);
			}
		} catch(NullPointerException n) {
			System.err.println("Driver object is null may be due to API test execution");
		}
		catch (Exception ex) {
			System.err.println("Unable to capture screenshot...");
			ex.printStackTrace();
		}
	}
}
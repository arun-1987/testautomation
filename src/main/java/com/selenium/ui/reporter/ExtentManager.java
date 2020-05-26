package com.selenium.ui.reporter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {

	public static ExtentReports extentreports;

	public synchronized static ExtentReports getInstance() {
		if (extentreports == null) {
			return createInstance("src/main/resources/report/extent.html");
		} else {
			return extentreports;
		}
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(fileName);
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setDocumentTitle(fileName);
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setReportName("AutomationReport");
		extentreports = new ExtentReports();
		extentreports.attachReporter(htmlreporter);
		return extentreports;
	}
}

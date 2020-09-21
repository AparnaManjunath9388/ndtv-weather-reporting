package com.qa.automation.framework.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

//import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.automation.framework.reporting.ExtentTestManager;

public class TestListener implements ITestListener {

	private Logger logger;
	private ExtentTestManager ExtentTestManager;
	
	public TestListener() {
		logger = LoggerFactory.getLogger(TestListener.class);
		ExtentTestManager = new ExtentTestManager();			//ExtentReports for individual threads are managed by ExtentTestManager
	}
	
	public void onStart(ITestContext context) {
		
		String suiteName = context.getName();
		logger.info("*** Test Suite " + suiteName + " started ***");
		ExtentTestManager.createReport(suiteName);			//on suite of a suite in thread1, create a Report and register along with thread Id
	}

	public void onFinish(ITestContext context) {
		logger.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();						//end the test of current thread when suite ends
		//ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		logger.info(("From Thread " + Thread.currentThread().getId() + ": *** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());			
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("From Thread " + Thread.currentThread().getId() + ": *** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");		//log pass status to current thread's report
	}

	/*public void onTestFailure(ITestResult result) {
		logger.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	} */

	public void onTestSkipped(ITestResult result) {
		logger.info("From Thread " + Thread.currentThread().getId() + ": *** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
	
	public void onTestFailure(ITestResult result) {
		logger.info("From Thread " + Thread.currentThread().getId() + ": *** Test execution " + result.getMethod().getMethodName() + " failed...");

		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

		String targetLocation = null;

		//create TestReport and screenshots folders to store report and screenshots
		String testClassName = result.getInstance().getClass().getName().trim();
		String timeStamp = LocalDateTime.now().toString().replace(":", "_").replace(".", "_");
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + timeStamp + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		logger.info("Screen shots reports path - " + reportsPath);
		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set
																				// screenshots
																				// folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					logger.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					logger.info("Failed to create directory: " + file.getAbsolutePath());
				}

			}

			//take screenshot
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
			
			//store screenshot in required location
			File targetFile = new File(targetLocation);
			logger.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
			logger.info("Target File location - " + targetFile.getAbsolutePath());
			FileHandler.copy(screenshotFile, targetFile);

		} catch (FileNotFoundException e) {
			logger.info("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			logger.info("An exception occurred while taking screenshot " + e.getCause());
		}

		// attach screenshots to report
		try {
			ExtentTestManager.getTest().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
		} catch (IOException e) {
			logger.info("An exception occured while taking screenshot " + e.getCause());
		}
		
		String message = result.getThrowable().getMessage();
		
		if (message.isEmpty())
			message = "Test Failed";
		ExtentTestManager.getTest().log(Status.FAIL, message);
	}
}

package com.qa.automation.framework.reporting;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
	private ExtentManager ExtentManager;
	
	//Maps of all the extentTests
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	
	//Maps of all the extentReports (each suite to run in one thread. One report for each thread)
	static Map<Integer, ExtentReports> extentReportsMap = new HashMap<Integer, ExtentReports>();
	
	public ExtentTestManager() {
		ExtentManager = new ExtentManager();
	}
	
	//add a report to extentReport map
	public synchronized void createReport(String suiteName) {
		ExtentReports extent = ExtentManager.getInstance(suiteName);
		extentReportsMap.put((int) (long) (Thread.currentThread().getId()), extent);
	}

	//required in TestListener to report steps of current thread's test
	public synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	//end test in current thread
	public synchronized void endTest() {
		extentReportsMap.get((int) (long) (Thread.currentThread().getId())).flush();
	}

	//add a test to extentTest map
	public synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extentReportsMap.get((int) (long) (Thread.currentThread().getId())).createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}

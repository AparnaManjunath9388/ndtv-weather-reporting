package com.qa.automation.ndtv.weatherreporting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automation.framework.base.TestBase;

public class SimpleTests extends TestBase {
	
	@Test(groups="Regression", testName="Verify if the page title is as expected")
	public void verifyPageTitle() throws Exception {
		
		try {
			Assert.assertTrue(openSite().getTitle().contains("NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos"));
		} catch(Exception e) {
			throw new Exception(e.getCause() + ": " + e.getMessage() + ". " + e.getStackTrace());
		}
	}
}

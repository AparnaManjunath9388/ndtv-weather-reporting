package com.qa.automation.ndtv.weatherreporting.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automation.framework.base.TestBase;
import com.qa.automation.ndtv.weatherreporting.dataproviders.JSONDataProvider;

public class SimpleTests extends TestBase {
	
	//@Test(groups="Regression", testName="Verify if the page title is as expected")
	@Test(groups="Regression", testName="Verify if the page title is as expected", dataProvider="TestDataProvider", dataProviderClass=JSONDataProvider.class)
	public void verifyPageTitle(JSONObject titleData) throws Exception {
		
		try {
			Assert.assertTrue(openSite().getTitle().contains(titleData.get("title").toString()));
		} catch(Exception e) {
			throw new Exception(e.getCause() + ": " + e.getMessage() + ". " + e.getStackTrace());
		}
	}
}

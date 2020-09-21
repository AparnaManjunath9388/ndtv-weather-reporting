package com.qa.automation.ndtv.weatherreporting.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automation.framework.base.TestBase;
import com.qa.automation.ndtv.weatherreporting.dataproviders.JSONDataProvider;

public class SimpleTests extends TestBase {
	
	@Test(groups="Regression", dataProvider="TestDataProvider", dataProviderClass=JSONDataProvider.class)
	public void verifyPageTitle(JSONObject titleData) throws Throwable {
		
		try {
			Assert.assertTrue(openSite().getTitle().contains(titleData.get("title").toString()));
		} catch(Exception e) {
			Assert.fail("Exception: Cause- " + e.getClass().getSimpleName() + ", " + e.getCause() + ", Message- " + e.getMessage());
			throw e;
		}
	}
}

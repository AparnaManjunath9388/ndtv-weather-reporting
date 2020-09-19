package com.qa.automation.ndtv.weatherreporting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automation.framework.base.TestBase;
import com.qa.automation.ndtv.weatherreporting.pages.WeatherPage;
import com.qa.automation.ndtv.weatherreporting.userdefinedclasses.Weather;

public class WeatherComparator extends TestBase {
	
	private WeatherPage weatherPage;
	private Weather weather;
	
	@Test(groups="Regression", testName="This test is intended to Navigate to Weather Page starting from Home Page")
	public void navigateToWeatherPage() throws Exception {
		try {
			weatherPage = openSite().navigateToWorldPage().navigateToWeatherPage();
			Assert.assertTrue(weatherPage.weatherlogoPresent(), "Navigation to WeatherPage has failed");
		} catch(Exception e) {
			throw new Exception(e.getCause() +": " + e.getMessage() + ". " + e.getStackTrace());
		}
	}
	
	@Test(groups="Regression", testName="This test is intended to Pin the required city and capture Weather details of the city from the Map", dependsOnMethods= {"navigateToWeatherPage"})
	public void captureCityWeatherDetails() throws Exception {
		try {
			weatherPage.pinRequiredCity("Bengaluru");
			weatherPage.highlightCityWeatherDetails("Bengaluru");
			weather = weatherPage.collectWeatherDetails();
			System.out.println(weather.toString());
			
		} catch(Exception e) {
			throw new Exception(e.getCause() +": " + e.getMessage() + ". " + e.getStackTrace());
		}
		
	}

}

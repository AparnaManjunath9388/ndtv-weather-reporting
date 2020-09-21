package com.qa.automation.ndtv.weatherreporting.tests;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.automation.framework.base.TestBase;
import com.qa.automation.framework.utils.APIRequestsProcessor;
import com.qa.automation.ndtv.weatherreporting.dataproviders.JSONDataProvider;
import com.qa.automation.ndtv.weatherreporting.pages.WeatherPage;
import com.qa.automation.ndtv.weatherreporting.weatherclasses.TemperatureComparator;
import com.qa.automation.ndtv.weatherreporting.weatherclasses.Weather;

public class WeatherReportingTests extends TestBase {
	
	private WeatherPage weatherPage;
	
	@Test(groups="Regression", dataProvider="TestDataProvider", dataProviderClass=JSONDataProvider.class)	
	public void compareTemperatureFromUIandAPI(JSONObject testData) throws Throwable {
		
		try {
			SoftAssert assertWeatherComparator = new SoftAssert();
			weatherPage = openSite().navigateToWorldPage().navigateToWeatherPage();
			assertWeatherComparator.assertTrue(weatherPage.weatherlogoPresent(), "Failed to navigate to Weather Page");
			
			Weather weatherFromUI = weatherPage.captureCityWeatherDetails(testData.get("city").toString());

			APIRequestsProcessor apiRequestsProcessor = new APIRequestsProcessor();
			apiRequestsProcessor.processRequest((JSONObject) testData.get("RequestDetails"));
			
			if (apiRequestsProcessor.validateResponse((JSONObject) testData.get("validations"))) {
				HashMap<String, String> weatherData = apiRequestsProcessor.getDataFromBody((JSONArray) testData.get("dataToCaptureFromBody"));
				Weather weatherFromAPI = new Weather(weatherData);
				
				int acceptableVariance = Integer.parseInt(testData.get("acceptableVariance").toString());
				TemperatureComparator tempComparator = new TemperatureComparator(acceptableVariance);
				assertWeatherComparator.assertTrue(tempComparator.compareTemperate(weatherFromUI, weatherFromAPI), "Temperature variance is more than acceptable number!");				
			} else {
				assertWeatherComparator.fail("Failed to get valid response from API");
			}
			assertWeatherComparator.assertAll();
			
		} catch(Throwable e) {
			Assert.fail("Exception: Cause- " + e.getClass().getSimpleName() + ", " + e.getCause() + ", Message- " + e.getMessage());
			throw e;
		}
		
	}

}

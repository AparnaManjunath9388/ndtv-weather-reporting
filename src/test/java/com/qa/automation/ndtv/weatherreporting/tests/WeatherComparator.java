package com.qa.automation.ndtv.weatherreporting.tests;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.automation.framework.base.TestBase;
import com.qa.automation.framework.utils.APIRequestsProcessor;
import com.qa.automation.ndtv.weatherreporting.dataproviders.JSONDataProvider;
import com.qa.automation.ndtv.weatherreporting.pages.WeatherPage;
import com.qa.automation.ndtv.weatherreporting.userdefinedclasses.Weather;

public class WeatherComparator extends TestBase {
	
	private WeatherPage weatherPage;
	
	@Test(groups="Regression", dataProvider="TestDataProvider", dataProviderClass=JSONDataProvider.class)	
	public void captureWeatherDetailsFromUIAndApi(JSONObject comparatorData) throws Exception {
		
		try {
			SoftAssert assertWeatherComparator = new SoftAssert();
			weatherPage = openSite().navigateToWorldPage().navigateToWeatherPage();
			assertWeatherComparator.assertTrue(weatherPage.weatherlogoPresent(), "Navigation to Weather Page has failed");
			
			Weather weatherFromUI = weatherPage.captureCityWeatherDetails(comparatorData.get("city").toString());
			
			APIRequestsProcessor apiRequestProcessor = new APIRequestsProcessor();
			/*String[] weatherData = apiRequestProcessor.processRequestAndGet((JSONObject) comparatorData.get("RequestDetails"), 
														(JSONObject) comparatorData.get("dataToCaptureFromBody"), 
														(JSONObject) comparatorData.get("validations"));
			Weather weatherFromAPI = new Weather(new HashMap<String, String>().put("temp in degrees", weatherData[0]));*/
			
		} catch(Exception e) {
			
		}
		
	}

}

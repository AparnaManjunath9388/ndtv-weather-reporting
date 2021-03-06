package com.qa.automation.ndtv.weatherreporting.uimaps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherPageElements {
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: driver
	 * @return: class object with instantiated WeElements
	 * @description: Contains UIMaps of all WeatherPageVerification functionality of WeatherPage
	 */
	public static class WeatherPageVerification {
		
		public static WeatherPageVerification getWeatherPageVerification(WebDriver driver) {
			return PageFactory.initElements(driver, WeatherPageVerification.class);
		}
		
		@FindBy(xpath="//img[contains(@src, \"ndtv_logo\")]")
		public WebElement img_WeatherLogo;
		
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: driver
	 * @return: class object with instantiated WeElements
	 * @description: Contains UIMaps of all PinRequiredCity functionality of WeatherPage
	 */
	public static class PinRequiredCity {
		
		public static PinRequiredCity getPinRequiredCityElements(WebDriver driver) {
			return PageFactory.initElements(driver, PinRequiredCity.class);
		}
		
		@FindBy(id="searchBox")
		public WebElement txt_SearchBox;
		
		@FindBy(xpath="//*[@id=\"messages\"]/div")
		public WebElement chkBx_AllCities;
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: driver
	 * @return: class object with instantiated WeElements
	 * @description: Contains UIMaps of all CollectWeatherDetails functionality of WeatherPage
	 */
	public static class CollectWeatherDetails {
		
		public static CollectWeatherDetails getCollectWeatherDetailsElements(WebDriver driver) {
			return PageFactory.initElements(driver, CollectWeatherDetails.class);
		}
		
		@FindBy(xpath="//div[@class=\"leaflet-popup-content\"]/div/span")
		public List<WebElement> allWeatherDetails;
		
		@FindBy(xpath="//*[@class=\"leaflet-popup-content-wrapper\"]")
		public WebElement popup_WeatherDetails;
	}

}

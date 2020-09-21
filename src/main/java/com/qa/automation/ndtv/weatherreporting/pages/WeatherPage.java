package com.qa.automation.ndtv.weatherreporting.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.automation.framework.base.PageBase;
import com.qa.automation.framework.utils.CommonMethods;
import com.qa.automation.framework.utils.CustomElementsManager;
import com.qa.automation.ndtv.weatherreporting.uimaps.WeatherPageElements;
import com.qa.automation.ndtv.weatherreporting.uimaps.WeatherPageElements.CollectWeatherDetails;
import com.qa.automation.ndtv.weatherreporting.uimaps.WeatherPageElements.WeatherPageVerification;
import com.qa.automation.ndtv.weatherreporting.weatherclasses.Weather;

public class WeatherPage extends PageBase {

	private CommonMethods commonMethods;
	private CustomElementsManager elementsManager;
	
	public WeatherPage(WebDriver driver) throws Throwable {
		super(driver);
		commonMethods = new CommonMethods(driver);
		elementsManager = new CustomElementsManager();
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: boolean indicating of Weather page logo is present or not
	 * @description: verifies if the weather page logo is present or not
	 */
	public boolean weatherlogoPresent() throws Throwable {
		WebElement img_logo = WeatherPageVerification.getWeatherPageVerification(driver).img_WeatherLogo;
		return explicitWait.until(ExpectedConditions.visibilityOf(img_logo)).isDisplayed();
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: cityName
	 * @return: none
	 * @description: pins the specified city on the screen
	 */
	public void pinRequiredCity(String cityName) throws Throwable {
		
		//WeatherPageElements.PinRequiredCity in inner class in WeatherPageElements. getPinRequiredCityElements method inits PageFactory objects for this scenario
		WebElement txt_SearchBox = WeatherPageElements.PinRequiredCity.getPinRequiredCityElements(driver).txt_SearchBox;
		explicitWait.until(ExpectedConditions.elementToBeClickable(txt_SearchBox)).sendKeys(cityName);
		
		//CustomElementsManager object since we need to replace city name in element's xpath
		String[] cityCheckBox = elementsManager.getProperty("chkBox_City");
		cityCheckBox[1] = cityCheckBox[1].replace("cityname", cityName);
		
		//use the edited xpath to find city name checkbox and click the same if not clicked
		WebElement chkBox_City = driver.findElement(commonMethods.getBy(cityCheckBox));
		if (!chkBox_City.isSelected())
			commonMethods.clickElement(chkBox_City);
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: none
	 * @description: click on city temperature in the map so that weather details will popup
	 */
	public void highlightCityWeatherDetails(String cityName) throws Throwable {
		
		//CustomElementsManager is used since xpath value needs to be edited for city name
		String[] cityTemperature = elementsManager.getProperty("temp_City");
		cityTemperature[1] = cityTemperature[1].replace("cityname", cityName);
		commonMethods.clickElement(driver.findElement(commonMethods.getBy(cityTemperature)));		//click on city temperature so that details will popup
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: new Weather object containing weather details of highlighted city
	 * @description: navigates from Home Page to World page
	 */
	public Weather collectWeatherDetails() throws Throwable {
		
		WebElement popup_WeatherDetails = CollectWeatherDetails.getCollectWeatherDetailsElements(driver).popup_WeatherDetails;
		explicitWait.until(ExpectedConditions.visibilityOf(popup_WeatherDetails));
		
		//read all the weather details and put them in HashMap. This HashMap is passed as a parameter to Weather object constructor to initialize it's values
		HashMap<String, String> WeatherDetails = new HashMap<String, String>();
		List<WebElement> weatherDetails = CollectWeatherDetails.getCollectWeatherDetailsElements(driver).allWeatherDetails;		
		for (WebElement detail : weatherDetails) {
			String content = detail.getText();
			WeatherDetails.put(content.split(":")[0].trim(), content.split(":")[1].trim());
		}
		return new Weather(WeatherDetails);
		
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: city name
	 * @return: new Weather object instantiated
	 * @description: calls the internal methods - pinRequiredCity, highlightCityWeatherDetails, collectWeatherDetails
	 */
	public Weather captureCityWeatherDetails(String city) throws Throwable {
		pinRequiredCity(city);
		highlightCityWeatherDetails(city);
		return collectWeatherDetails();
	}
}

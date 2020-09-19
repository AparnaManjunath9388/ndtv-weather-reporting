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
import com.qa.automation.ndtv.weatherreporting.userdefinedclasses.Weather;

public class WeatherPage extends PageBase {

	private CommonMethods commonMethods;
	private CustomElementsManager elementsManager;
	
	public WeatherPage(WebDriver driver) throws Exception {
		super(driver);
		commonMethods = new CommonMethods(driver);
		elementsManager = new CustomElementsManager();
	}
	
	public boolean weatherlogoPresent() throws Exception {
		WebElement img_logo = WeatherPageVerification.getWeatherPageVerification(driver).img_WeatherLogo;
		return explicitWait.until(ExpectedConditions.visibilityOf(img_logo)).isDisplayed();
	}
	
	public void pinRequiredCity(String cityName) throws Exception {
		
		WeatherPageElements.PinRequiredCity.getPinRequiredCityElements(driver).txt_SearchBox.sendKeys(cityName);
		
		String[] cityCheckBox = elementsManager.getProperty("chkBox_City");
		cityCheckBox[1] = cityCheckBox[1].replace("cityname", cityName);
		
		WebElement chkBox_City = driver.findElement(commonMethods.getBy(cityCheckBox));
		if (!chkBox_City.isSelected())
			commonMethods.clickElement(chkBox_City);
	}
	
	public void highlightCityWeatherDetails(String cityName) throws Exception {
		
		String[] cityTemperature = elementsManager.getProperty("temp_City");
		cityTemperature[1] = cityTemperature[1].replace("cityname", cityName);
		commonMethods.clickElement(driver.findElement(commonMethods.getBy(cityTemperature)));
	}
	
	public Weather collectWeatherDetails() throws Exception {
		
		WebElement popup_WeatherDetails = CollectWeatherDetails.getCollectWeatherDetailsElements(driver).popup_WeatherDetails;
		explicitWait.until(ExpectedConditions.visibilityOf(popup_WeatherDetails));
		
		HashMap<String, String> WeatherDetails = new HashMap<String, String>();
		List<WebElement> weatherDetails = CollectWeatherDetails.getCollectWeatherDetailsElements(driver).allWeatherDetails;		
		for (WebElement detail : weatherDetails) {
			String content = detail.getText();
			WeatherDetails.put(content.split(":")[0].trim(), content.split(":")[1].trim());
		}
		return new Weather(WeatherDetails);
		
	}
}

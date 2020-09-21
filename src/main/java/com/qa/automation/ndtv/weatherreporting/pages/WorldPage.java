package com.qa.automation.ndtv.weatherreporting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.automation.framework.base.PageBase;
import com.qa.automation.framework.utils.CommonMethods;
import com.qa.automation.ndtv.weatherreporting.uimaps.WorldPageElements;

public class WorldPage extends PageBase {
	
	CommonMethods commonMethods;
	public WorldPage(WebDriver driver) {
		super(driver);
		commonMethods = new CommonMethods(driver);
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: new WeatherPage instantiated (Page chaining)
	 * @description: navigateToWeatherPage2 is called in try-catch to ensure the sync between page content load and operations we perform
	 */
	public WeatherPage navigateToWeatherPage() throws Throwable {
		try {
			return navigateToWeatherPage2();
			
		} catch(Exception e) {			
			if (e.getMessage().contains("element not interactable"))
				return navigateToWeatherPage2();
			else
				throw e;
		}
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: new WeatherPage instantiated (Page chaining)
	 * @description: clicks on Top navigation section, clicks on Weather link to navigate to Weather page
	 */
	public WeatherPage navigateToWeatherPage2() throws Throwable {
		
		//wait for top navigation element to be clickable and then click
		WebElement topNavigationMenu = WorldPageElements.SelectMenuItem.getSelectMenutemsElements(driver).TopNavigationSection;
		explicitWait.until(ExpectedConditions.elementToBeClickable(topNavigationMenu)).click();
		
		//wait for weather link to be visible, scroll down to view the same and click
		WebElement lnk_Weather = WorldPageElements.SelectMenuItem.getSelectMenutemsElements(driver).lnk_Weather;
		explicitWait.until(ExpectedConditions.visibilityOf(lnk_Weather));		
		commonMethods.scrollToView(lnk_Weather);
		lnk_Weather.click();
		
		return new WeatherPage(driver);			

	}

}

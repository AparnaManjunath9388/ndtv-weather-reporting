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
	
	public WeatherPage navigateToWeatherPage() throws Exception {
		try {
			return navigateToWeatherPage2();
			
		} catch(Exception e) {			
			if (e.getMessage().contains("element not interactable"))
				return navigateToWeatherPage2();
			else
				throw e;
		}
	}
	 
	public WeatherPage navigateToWeatherPage2() throws Exception {
		
		WebElement topNavigationMenu = WorldPageElements.SelectMenuItem.getSelectMenutemsElements(driver).TopNavigationSection;
		explicitWait.until(ExpectedConditions.elementToBeClickable(topNavigationMenu)).click();
		
		WebElement lnk_Weather = WorldPageElements.SelectMenuItem.getSelectMenutemsElements(driver).lnk_Weather;
		explicitWait.until(ExpectedConditions.visibilityOf(lnk_Weather));		
		commonMethods.scrollToView(lnk_Weather);
		lnk_Weather.click();
		
		return new WeatherPage(driver);			

	}

}

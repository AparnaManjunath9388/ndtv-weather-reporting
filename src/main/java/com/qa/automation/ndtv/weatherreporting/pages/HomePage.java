package com.qa.automation.ndtv.weatherreporting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.automation.framework.base.PageBase;
import com.qa.automation.ndtv.weatherreporting.uimaps.HomePageElements;

public class HomePage extends PageBase {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: page title in String format
	 * @description: extracts Page title and returns the same
	 */
	public String getTitle() {
		try {
			return driver.getTitle();
		} catch(Exception e) {
			throw e;
		}
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: WorldPage object instantiated
	 * @description: navigates from Home Page to World page
	 */
	public WorldPage navigateToWorldPage() throws Throwable {
		
		//HomePageElements.NavigateTo is a static inner class with method getNavigateToElements that initiates all PageFactory objects
		WebElement lnk_World = HomePageElements.NavigateTo.getNavigateToElements(driver).lnk_World;
		explicitWait.until(ExpectedConditions.visibilityOf(lnk_World)).click();			//wait for visibility of WOLR link and click on it
		
		WebElement PageHeading = HomePageElements.NavigateTo.getNavigateToElements(driver).txt_WorldPageHeading;
		explicitWait.until(ExpectedConditions.visibilityOf(PageHeading));		//wait for the Page heading to appear
		return new WorldPage(driver);		//new WorldPage object (Page chaining)
	}

}

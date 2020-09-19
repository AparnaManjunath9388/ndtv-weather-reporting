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
	
	public String getTitle() {
		try {
			return driver.getTitle();
		} catch(Exception e) {
			throw e;
		}
	}
	
	public WorldPage navigateToWorldPage() throws Exception {
		
		WebElement lnk_World = HomePageElements.NavigateTo.getNavigateToElements(driver).lnk_World;
		explicitWait.until(ExpectedConditions.visibilityOf(lnk_World)).click();
		
		WebElement PageHeading = HomePageElements.NavigateTo.getNavigateToElements(driver).txt_WorldPageHeading;
		explicitWait.until(ExpectedConditions.visibilityOf(PageHeading));
		return new WorldPage(driver);
	}

}

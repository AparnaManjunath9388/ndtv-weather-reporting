package com.qa.automation.ndtv.weatherreporting.pages;

import org.openqa.selenium.WebDriver;

import com.qa.automation.framework.base.PageBase;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTitle() {
		return driver.getCurrentUrl();
	}

}

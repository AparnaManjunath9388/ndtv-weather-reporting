package com.qa.automation.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automation.framework.utils.Constants;

public class PageBase {
	
	protected WebDriver driver;
	protected WebDriverWait explicitWait;
	
	//constructor to set protected WebDriver instance and instantiate explicit wait using Constants from  com.qa.automation.framework.utils.Constants class
	public PageBase(WebDriver driver) { 
		this.driver = driver;
		explicitWait = new WebDriverWait(driver, Constants.EXPLICITWAIT_TIMEOUT);
		
	}

}

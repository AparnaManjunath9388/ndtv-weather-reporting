package com.qa.automation.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automation.framework.utils.Constants;

public class PageBase {
	
	protected WebDriver driver;
	protected WebDriverWait explicitWait;
	
	public PageBase(WebDriver driver) { 
		this.driver = driver;
		explicitWait = new WebDriverWait(driver, Constants.EXPLICITWAIT_TIMEOUT);
		
	}

}

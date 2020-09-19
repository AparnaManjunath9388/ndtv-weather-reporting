package com.qa.automation.ndtv.weatherreporting.uimaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class WorldPageElements {
	
	public static class SelectMenuItem {
		
		public static SelectMenuItem getSelectMenutemsElements(WebDriver driver) {
			return PageFactory.initElements(driver, SelectMenuItem.class);
		}
		
		@FindBy(id="topnav_section")
		public WebElement TopNavigationSection;
		
		@FindBy(xpath="//*[@class=\"ins_page_header\"]/h1")
		public WebElement txt_WorldPageHeading;
		
		@FindBy(xpath="//*[@class=\"n_weather\"]/a")
		public WebElement lnk_Weather;
	}
}

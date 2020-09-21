package com.qa.automation.ndtv.weatherreporting.uimaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElements {
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: driver
	 * @return: class object with instantiated WeElements
	 * @description: Contains UIMaps of all NavigateTo functionality of HomePage
	 */
	public static class NavigateTo {
		
		public static NavigateTo getNavigateToElements(WebDriver driver) {
			return PageFactory.initElements(driver, NavigateTo.class);
		}
		
		@FindBy(linkText="WORLD")
		public WebElement lnk_World;
		
		@FindBy(xpath="//*[@class=\"ins_page_header\"]/h1")
		public WebElement txt_WorldPageHeading;
		
	}

}

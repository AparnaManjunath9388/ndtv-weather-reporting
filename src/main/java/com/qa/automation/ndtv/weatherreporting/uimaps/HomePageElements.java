package com.qa.automation.ndtv.weatherreporting.uimaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElements {
	
	public static class Notifications {
		
		public static Notifications getAllNotifications(WebDriver driver) {
			return PageFactory.initElements(driver, Notifications.class);
		}
		
		@FindBy(xpath="//*[@id=\"__cricketsubscribe\"]/div[@class=\"noti_wrap\"]")
		public WebElement home_BreakingNewsAlert;
	}
	
	public static class NavigateTo {
		
		public static NavigateTo getNavigateToElements(WebDriver driver) {
			return PageFactory.initElements(driver, NavigateTo.class);
		}
		
	}

}

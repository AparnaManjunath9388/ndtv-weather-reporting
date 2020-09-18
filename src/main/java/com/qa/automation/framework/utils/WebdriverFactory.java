package com.qa.automation.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public void setDriver(BrowserType browserName, String version) throws Exception {
		
		try {
			switch (browserName) {
				
				case CHROME:
					
					if (version.isEmpty())
						WebDriverManager.chromedriver().setup();
					else
						WebDriverManager.chromedriver().browserVersion(version).setup();
					driver.set(new ChromeDriver());
					break;
					
				case FIREFOX:
					if (version.isEmpty())
						WebDriverManager.firefoxdriver().setup();
					else
						WebDriverManager.firefoxdriver().browserVersion(version).setup();
					driver.set(new FirefoxDriver());
					break;
					
				case EDGE:
					WebDriverManager.edgedriver().setup();
					driver.set(new EdgeDriver());
					break;
			}
    		
		} catch(Exception e) {
			throw new Exception(e.getCause() + ": " + e.getMessage() + ". " + e.getStackTrace().toString());
		}
		
	}
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	public void tearDownBrowser() {
		driver.get().quit();
		driver.remove();
	}
}

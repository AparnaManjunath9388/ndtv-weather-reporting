package com.qa.automation.framework.utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public void setDriver(BrowserType browserName, String version) throws Exception {
		switch (browserName) {
			
			case CHROME:
				
				if (version.isEmpty())
					WebDriverManager.chromedriver().setup();
				else
					WebDriverManager.chromedriver().browserVersion(version).setup();
				driver.set(new ChromeDriver(buildChromeOption()));
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
		
	}
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	public void tearDownBrowser() {
		driver.get().quit();
		driver.remove();
	}
	
	public ChromeOptions buildChromeOption() throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		options.addArguments("-disable-notifications");
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		return options;
	}
}

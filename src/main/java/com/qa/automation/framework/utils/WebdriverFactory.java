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

	//thread-safe WebDriver instance
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: browserName and version String parameters
	 * @return: none
	 * @description: create desired WebDriver instance which is Thread safe with browser settings
	 */
	
	public void setDriver(BrowserType browserName, String version) throws Throwable {
		switch (browserName) {
			
			case CHROME:
				
				if (version.isEmpty())
					WebDriverManager.chromedriver().setup();				//io.github.bonigarcia.wdm.WebDriverManager is a utility to automatically download required driver.exe
				else
					WebDriverManager.chromedriver().browserVersion(version).setup();		//version can also be specified
				driver.set(new ChromeDriver(buildChromeOption()));							//buildChromeOption chrome options' internal builder method
				break;
				
			case FIREFOX:
				//if (version.isEmpty())
					WebDriverManager.firefoxdriver().setup();
				//else
					//WebDriverManager.firefoxdriver().browserVersion(version).setup();
				driver.set(new FirefoxDriver());
				break;
				
			case EDGE:
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
				break;
		}
		
	}

	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: WebDriver instance
	 * @description: return WebDriver instance which is Thread-safe
	 */
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: none
	 * @description: Tears down the current WebDriver instance and removes it from the ThreadLocal list
	 */
	public void tearDownBrowser() {
		driver.get().quit();
		driver.remove();
	}
	
	public ChromeOptions buildChromeOption() throws Throwable {
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		options.addArguments("-disable-notifications");					//disable all notifications
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);			//wait for all the page content to get loaded
		return options;
	}
}

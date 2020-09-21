package com.qa.automation.framework.base;

/*
 * @auth: Aparna Manjunath
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.automation.framework.listeners.EventHandler;
import com.qa.automation.framework.utils.BrowserType;
import com.qa.automation.framework.utils.ConfigFileManager;
import com.qa.automation.framework.utils.Constants;
import com.qa.automation.framework.utils.WebdriverFactory;
import com.qa.automation.ndtv.weatherreporting.pages.HomePage;

public class TestBase {
	
	//private WebDriver instance and a WebDriverManager to manage WebDriver related actions
	private WebDriver driver;
	WebdriverFactory webDriverFactory;
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: Browser and version String parameters
	 * @return: none
	 * @description: create desired WebDriver instance with browser settings
	 */
	@BeforeMethod(alwaysRun=true)
	@Parameters({"Browser", "Version"})
	public void setupBrowser(@Optional("") ITestContext context, @Optional("") String Browser, @Optional("") String Version) throws Throwable {
		
		//WebDriverFactory util will manage creation and tearing down of WebDriver instance
		webDriverFactory = new WebdriverFactory();
		webDriverFactory.setDriver(BrowserType.valueOf(Browser.toUpperCase()), Version);
		driver = webDriverFactory.getDriver();
		
		//registering EventListener
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(new EventHandler());
		driver = eventDriver;
		
		//some Browser settings
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		context.setAttribute("WebDriver", driver);
		
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: HomePage instance
	 * @description: intial method of Page chaining. Creates HomePage and passes the WebDriver instance to further pages in current test
	 */
	public HomePage openSite() throws Throwable {
		
		try {
			ConfigFileManager configManager = new ConfigFileManager();		//ConfigFileManager util will manage all actions related to config file in /NDTVWeatherReporting/src/main/java/com/qa/automation/framework/config
			driver.get(configManager.getAppURL());
			return new HomePage(driver);
		} catch(Exception e) {
			throw e;
		}
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: none
	 * @description: teardown of WebDriver instance
	 */
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		webDriverFactory.tearDownBrowser();
	}
}
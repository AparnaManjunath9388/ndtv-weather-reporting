package com.qa.automation.framework.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
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
	
	private WebDriver driver;
	WebdriverFactory webDriverFactory;
	
	@BeforeMethod(alwaysRun=true)
	@Parameters({"Browser", "Version"})
	public void setupBrowser(@Optional("") String Browser, @Optional("") String Version) throws Exception {
		
		webDriverFactory = new WebdriverFactory();
		webDriverFactory.setDriver(BrowserType.valueOf(Browser.toUpperCase()), Version);
		driver = webDriverFactory.getDriver();
		
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(new EventHandler());
		driver = eventDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		
	}
	
	public HomePage openSite() throws Exception {
		
		ConfigFileManager configManager = new ConfigFileManager();
		driver.get(configManager.getAppURL());
		
		//driver.switchTo().alert().dismiss();
		return new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		webDriverFactory.tearDownBrowser();
	}
}
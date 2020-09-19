package com.qa.automation.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.automation.framework.base.PageBase;

public class CommonMethods extends PageBase {
	
	public CommonMethods(WebDriver driver) {
		super(driver);
	}
	
	public void clickElement(WebElement elementToClick) throws Exception {		
		explicitWait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
	}
	
	public By getBy(String[] elementProperty) throws Exception {
		
			String Key = elementProperty[0];
			String Value = elementProperty[1];
			
			switch(Key.toLowerCase()) {
			 
			case "name":
				return By.name(Value);
				
			case "id":
				return By.id(Value);
				
			case "linktext":
				return By.linkText(Value);
				
			case "partiallinktext":
				return By.partialLinkText(Value);
		
			case "tagname":
				return By.tagName(Value);
				
			case "cssselector":
				return By.cssSelector(Value);
			
			case "classname":
				return By.cssSelector(Value);
				
			case "xpath":
				return By.xpath(Value);
			default:
				throw new Exception("Exception from LocationParser.getBy: Unknown locator type '" + Key + "'");

		}
	}
	
	public void scrollToView(WebElement element) throws Exception {
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
}

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
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: elementToClick WebElement
	 * @return: none
	 * @description: waits for the element to be clickable and then clicks on it
	 */
	public void clickElement(WebElement elementToClick) throws Throwable {		
		explicitWait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: elementProperty string array containing by string format in [0] and property value in [1]
	 * @return: By object
	 * @description: intake String array of element by and property values and return corresponding By object
	 */
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
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: element in WebElement format
	 * @return: none
	 * @description: scroll down till the element is visibe
	 */
	public void scrollToView(WebElement element) throws Throwable {
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
}

package com.qa.automation.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class CustomElementsManager {
	
	private Properties CustomElementsFile;
	
	public CustomElementsManager() throws Throwable {
		CustomElementsFile = new Properties();
		CustomElementsFile.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\automation\\ndtv\\weatherreporting\\uimaps\\CustomElementProperties.properties"));
	}
	
	public String[] getProperty(String elementName) throws Throwable {
		return CustomElementsFile.getProperty(elementName).split(";");
	}

}

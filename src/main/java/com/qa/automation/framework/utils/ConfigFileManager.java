package com.qa.automation.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFileManager {
	
	private Properties appProperties;
	private Properties frameworkProperties;

	
	public ConfigFileManager() throws Exception {
		try {
			appProperties = new Properties();
			appProperties.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\automation\\framework\\config\\AppConfig.properties"));
			frameworkProperties = new Properties();
			frameworkProperties.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\automation\\framework\\config\\FrameworkConfig.properties"));
			
		} catch(Exception e) {
			throw new Exception(e.getCause() + ": " + e.getMessage() + ". " + e.getStackTrace());
		}
	}
	
	public String getAppURL() throws Exception {
		try {
			return appProperties.getProperty("AppUrl");
		} catch(Exception e) {
			throw new Exception(e.getCause() + ": " + e.getMessage() + ". " + e.getStackTrace());
		}
	}

	public String getHtmlReportsPath() throws Exception {
		try {
			return frameworkProperties.getProperty("HtmlReportPath").replace("userdir", System.getProperty("user.dir"));
		} catch(Exception e) {
			throw new Exception(e.getCause() + ": " + e.getMessage() + ". " + e.getStackTrace());
		}		
	}
}

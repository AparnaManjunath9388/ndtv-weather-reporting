package com.qa.automation.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFileManager {
	
	private Properties appProperties;
	private Properties frameworkProperties;

	
	public ConfigFileManager() throws Exception {
		appProperties = new Properties();
		appProperties.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\automation\\framework\\config\\AppConfig.properties"));
		frameworkProperties = new Properties();
		frameworkProperties.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\automation\\framework\\config\\FrameworkConfig.properties"));
			
	}
	
	public String getAppURL() throws Exception {
		return appProperties.getProperty("AppUrl");
	}

	public String getHtmlReportsPath() throws Exception {
		return frameworkProperties.getProperty("HtmlReportPath").replace("userdir", System.getProperty("user.dir"));	
	}
}

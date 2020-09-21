# ndtv-weather-reporting
Overview:
Scenarios Automated:
1. com.qa.automation.ndtv.weatherreporting.tests.SimpleTests.verifyPageTitle: 
	Launch Browser -> Navigate to www.ndtv.com and verify if the page title is as expected
	
2. com.qa.automation.ndtv.weatherreporting.tests.WeatherReportingTests.compareTemperatureFromUIandAPI:
	a. Launch Browser -> Navigate to www.ndtv.com
	b. Navigate to World Page -> Weather page
	c. Pin the city "Bengaluru" -> Highlight Weather details -> Capture Weather details in Weather object
	d. Send GET request to https://api.openworldmap.org and get weather details
	e. Read Temperature and build Weather object with Temperature value (convert kelvin to celsius)
	f. Compare Weather objects from step c and e using TemperatureComparator and check whether difference is within acceptable variance. If not throw user-defined Exception MatcherException
	
Technologies used:
	Interface				: Selenium WebDriver
	Language				: Java
	Framework				: TestNG
	Log						: log4j
	Execution Report format	: ExtentReport (Thread-safe. Individual reports for each thread)
	Data Parameterization	: JSON file format. JSONDataProvider class that acts as @DataProvider for all the tests
	Listeners				: EventListener and TestListener
	UIMaps					: Separate PageElements class containing individual scenario elements (instantiated by PageFatory.init)
	Framework approach		: Page-Object-Model, Page-chaining model

Instructions to download and deploy:
Github Repository : https://github.com/AparnaManjunath9388/ndtv-weather-reporting

1. Goto above path and select master branch.
2. Download the zip by clicking on "code" button on right corner of the artifacts box
3. Unzip the same and go to NDTVWeatherReporting folder -> run NDTVWeatherReporting.bat file from the same location to run the build

NOTE:
1. Please make sure that Java 8 or above is installed in the machine
2. Make sure one of these browsers are installed : Chrome, Firefox, Microsoft Edge
3. Internet connection should be on
4. Browser and the version can be changed in testng.xml file if required. Currently CHROME browser is selected with "85.0.4183.102" version



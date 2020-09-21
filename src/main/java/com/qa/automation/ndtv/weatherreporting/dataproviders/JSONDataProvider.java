package com.qa.automation.ndtv.weatherreporting.dataproviders;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

public class JSONDataProvider {
	
    public static String dataFile = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\automation\\ndtv\\weatherreporting\\testdata\\TestData.json";
    
	/*
	 * @auth: Aparna Manjunath
	 * @params: none
	 * @return: none
	 * @description: extracts all the data from TestData.json file under com.qa.automation.ndtv.reporting.testdata.TestData.json
	 */
    public static JSONObject extractData_JSON() throws Throwable {
        FileReader reader = new FileReader(dataFile);
        JSONParser jsonParser = new JSONParser();

        return (JSONObject) jsonParser.parse(reader);
    }
    
	/*
	 * @auth: Aparna Manjunath
	 * @params: method of Type Method
	 * @return: 2D array of Objects
	 * @description: filters extracted data to pick on specified method name data (Scenario name), converts them to 2D array of JSONObjects
	 */
    @DataProvider(name="TestDataProvider")
    public static Object[][] fetchData(Method method) throws Throwable {
	   JSONArray testData = (JSONArray) extractData_JSON().get(method.getName());

	   List<JSONObject> testDataList = new ArrayList<JSONObject>();

		for ( int i = 0; i < testData.size(); i++ ) {
			testDataList.add((JSONObject) testData.get(i));
		}

		// create object for dataprovider to return
		Object[][] result = new Object[testDataList.size()][testDataList.get(0).size()];
		
		for ( int i = 0; i < testDataList.size(); i++ ) {
			result[i] = new Object[] { testDataList.get(i) };
		}

		return result;
    }
}

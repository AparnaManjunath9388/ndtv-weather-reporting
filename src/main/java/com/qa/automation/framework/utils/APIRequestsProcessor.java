package com.qa.automation.framework.utils;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.qa.automation.framework.apihandlers.GetHandler;

import io.restassured.response.Response;

public class APIRequestsProcessor {
	
	private Response response;
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: requestDetails in JSONObject format
	 * @return: none
	 * @description: Process RestAPI request as per the details given
	 */
	public void processRequest(JSONObject requestDetails) throws Throwable {
		
		//HTTPRequests is enum in utils package
		HTTPRequests requestType = HTTPRequests.valueOf(requestDetails.get("httpRequest").toString().toUpperCase());		//httpRequest inside requestDetails will specify what request to send
		switch (requestType) {
			case GET:
				response = GetHandler.SendGetRequest(requestDetails);			//GetHandler from com.qa.automation.framework.apihandlers
				break;
			default:
				throw new Exception("APIHandler yet to be developed");
		}
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: jsonPaths in JSONArray format
	 * @return: data in HashMap format
	 * @description: Parses response body and picks specified json path information. All the data is added in HashMap
	 */
	public HashMap<String, String> getDataFromBody(JSONArray jsonPaths) throws Throwable {
		
		HashMap<String, String> values = new HashMap<String, String>();
		if (!response.body().equals(null)) {			//if response body is npt null only then read the data from it
			JSONObject responseBody = (JSONObject) response.body();
			for (int i = 0; i<jsonPaths.size();i++)
				values.put(jsonPaths.get(i).toString(), responseBody.get(jsonPaths.get(i).toString()).toString());	//key: jsonpath, value: data from response body
		}
		return values;
	}

	/*
	 * @auth: Aparna Manjunath
	 * @params: validations in JSONObject format
	 * @return: boolean value indicating if response satisfies all the validations
	 * @description: Validates response against a set of validations
	 */
	public boolean validateResponse(JSONObject validations) throws Throwable {

		boolean validResponse = true;
    	for (Object keyStr : validations.keySet()) {
    		
    		switch(keyStr.toString().toLowerCase()) {
    		
    		//comparing status code
    		case "status code":
    			if ((long) response.statusCode() != (long) validations.get(keyStr))
    				validResponse = false;
    			break;
    			
    		default:
    			throw new Exception("BAD Validation: Validation " + keyStr.toString() + " yet to be implemented");
    		}
    	}
    	return validResponse;
	}

}

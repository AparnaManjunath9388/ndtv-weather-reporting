package com.qa.automation.framework.utils;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.qa.automation.framework.apihandlers.GetHandler;

import io.restassured.response.Response;

public class APIRequestsProcessor {
	
	private Response response;
	
	public void processRequest(JSONObject requestDetails) throws Throwable {
		
		HTTPRequests requestType = HTTPRequests.valueOf(requestDetails.get("httpRequest").toString().toUpperCase());
		switch (requestType) {
			case GET:
				response = GetHandler.SendGetRequest(requestDetails);
				break;
			default:
				throw new Exception("APIHandler yet to be developed");
		}
	}
	
	
	public HashMap<String, String> getDataFromBody(JSONArray jsonPaths) throws Throwable {
		
		//String[] jsonPathsToCapture = jsonPaths.toString().split(",");
		HashMap<String, String> values = new HashMap<String, String>();
		if (!response.body().equals(null)) {
			JSONObject responseBody = (JSONObject) response.body();
			for (int i = 0; i<jsonPaths.size();i++)
				values.put(jsonPaths.get(i).toString(), responseBody.get(jsonPaths.get(i).toString()).toString());
		}
		return values;
	}
	
	public boolean validateResponse(JSONObject validations) throws Throwable {

		boolean validResponse = true;
    	for (Object keyStr : validations.keySet()) {
    		
    		switch(keyStr.toString().toLowerCase()) {
    		
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

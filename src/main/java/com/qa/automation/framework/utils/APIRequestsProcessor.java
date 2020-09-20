package com.qa.automation.framework.utils;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.qa.automation.framework.apihandlers.GetHandler;

import io.restassured.response.Response;

public class APIRequestsProcessor {
	
	public Response processRequest(HTTPRequests requestType, JSONObject requestDetails) throws Exception {
		
		switch (requestType) {
			case GET:
				return GetHandler.SendGetRequest(requestDetails);
			default:
				throw new Exception("APIHandler yet to be developed");
		}
	}
	
	
	public HashMap<String, String> processRequestAndGet(JSONObject requestDetails, JSONObject jsonPaths, JSONObject validations) throws Exception {
		
		String[] jsonPathsToCapture = jsonPaths.toString().split(",");
		HashMap<String, String> values = new HashMap<String, String>();
		Response response = processRequest(HTTPRequests.valueOf(requestDetails.get("httpRequest").toString().toUpperCase()), requestDetails);
		
		if (validateResponse(validations)) {
			JSONObject responseBody = (JSONObject) response.body();
			if (!responseBody.isEmpty())
				for (int i = 0; i<jsonPathsToCapture.length;i++)
					values.put(jsonPathsToCapture[i], responseBody.get(jsonPathsToCapture[i]).toString());	
		}
		return values;
	}
	
	public boolean validateResponse(JSONObject validations) throws Exception {
		return true;
	}

}

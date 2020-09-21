package com.qa.automation.framework.apihandlers;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetHandler {
	
	/*
	 * @auth: Aparna Manjunath
	 * params: requestDetails in JSONObject format
	 * returns: HTTP Response
	 */
	public static Response SendGetRequest(JSONObject requestDetails) throws Throwable {

	    RequestSpecification httpGetRequest = RestAssured.given();
	    
	    //set baseuri
	    if (requestDetails.containsKey("baseuri")) { httpGetRequest.baseUri(requestDetails.get("baseuri").toString()); }
	    else { throw new Exception("BAD JSONObject: JSONObject must contain baseuri Key"); }
	    
	    //set basePath
	    if (requestDetails.containsKey("basePath")) { httpGetRequest.basePath(requestDetails.get("basePath").toString()); }
	    
	    //set query parameters 
	    if (requestDetails.containsKey("queryParams")) {
	    	JSONObject queryParams = (JSONObject) requestDetails.get("queryParams");
	    	for (Object keyStr : queryParams.keySet()) {
	    		httpGetRequest.queryParam(keyStr.toString(), queryParams.get(keyStr).toString());
	    	}
	    } 
	    
	    //set request Headers
	    if (requestDetails.containsKey("requestHeaders")) {
	    	Map<String, Object> requestHeadersMap = new Gson().fromJson(requestDetails.get("requestHeaders").toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
	    	httpGetRequest.headers(requestHeadersMap);
	    }
	    
	    //set request body if present
		if (requestDetails.containsKey("requestBody")) { httpGetRequest.body(requestDetails.get("requestBody")); }
	    
		//send request and return Response
		return httpGetRequest.when().get();
		
	} 

}

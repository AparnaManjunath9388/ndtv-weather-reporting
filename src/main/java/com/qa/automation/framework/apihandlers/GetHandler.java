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
	
	
	public static Response SendGetRequest(JSONObject requestDetails) throws Exception {

	    RequestSpecification httpGetRequest = RestAssured.given();
	    
	    httpGetRequest.baseUri(requestDetails.get("baseuri").toString());
	    
	    String basePath = requestDetails.get("basePath").toString();
	    if (!basePath.isEmpty()) { httpGetRequest.basePath(basePath); }
	    
	    Map<String, Object> requestParamsMap = new Gson().fromJson(requestDetails.get("requestParams").toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
	    if (!requestParamsMap.isEmpty()) { httpGetRequest.params(requestParamsMap); }
	    
	    Map<String, Object> requestHeadersMap = new Gson().fromJson(requestDetails.get("requestHeaders").toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
	    if (!requestHeadersMap.isEmpty()) { httpGetRequest.headers(requestHeadersMap); }
	    
		if (!requestDetails.get("requestBody").toString().isEmpty()) { httpGetRequest.body(requestDetails.get("requestBody")); }
	    
		return httpGetRequest.when().get();
		
	} 

}

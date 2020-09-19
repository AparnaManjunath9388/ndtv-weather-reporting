package com.qa.automation.ndtv.weatherreporting.userdefinedclasses;

import java.util.HashMap;
import java.util.Map;

public final class Weather {
	
	private String condition;
	private String wind;
	private String humidity;
	private double tempInDegrees;
	private double tempInFahrenheit;
	
	public Weather(String condition, String wind, String humidity, double tempInDegrees, double tempInFahrenheit) {
		
		this.condition = condition;
		this.wind = wind;
		this.humidity = humidity;
		this.tempInDegrees = tempInDegrees;
		this.tempInFahrenheit = tempInFahrenheit;
		
	}
	
	public Weather(HashMap<String, String> details) throws Exception {
		
		for(Map.Entry<String, String> mapElement : details.entrySet()) {
			findAndSet(mapElement.getKey().toString(), mapElement.getValue().toString());
		}
	}
	
	@Override
	public String toString() {
		return "Condition: " + this.condition +", wind: " + this.wind + ", humidity: " + this.humidity + 
				", Temperature in Degrees: " + this.tempInDegrees + ", Temperature in Fahrenheit: " + this.tempInFahrenheit;
	}
	
	public void findAndSet(String key, String value) throws Exception {
		
		switch (key.toLowerCase()) {
		
			case "condition":
				this.condition = value;
				break;
				
			case "wind":
				this.wind = value;
				break;
				
			case "humidity":
				this.humidity = value;
				break;
				
			case "temp in degrees":
				this.tempInDegrees = Double.parseDouble(value);
				break;
				
			case "temp in fahrenheit":
				this.tempInFahrenheit = Double.parseDouble(value);
				break;
				
			default:
				throw new Exception("Unknown Weather detail");
		}
			
	}

}

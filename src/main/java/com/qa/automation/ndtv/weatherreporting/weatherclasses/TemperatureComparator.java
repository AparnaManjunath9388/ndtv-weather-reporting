package com.qa.automation.ndtv.weatherreporting.weatherclasses;

import java.util.Comparator;

public class TemperatureComparator implements Comparator<Weather> {

	private int acceptableVariance;
	
	public TemperatureComparator(int variance) {
		acceptableVariance = variance;
	}
	
	@Override
	public int compare(Weather w1, Weather w2) {	
		return Math.abs((int) (w1.getTempInDegrees() - w2.getTempInDegrees()));
	}
	
	public boolean compareTemperate(Weather w1, Weather w2) throws MatcherException {
		
		boolean result = false;
		if (compare(w1, w2) <= acceptableVariance) {
			result = true;
		} else {
			throw new MatcherException("Temperature difference is beyond accpetable number!");
		}
		return result;
		
	}
}

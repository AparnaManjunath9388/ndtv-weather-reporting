package com.qa.automation.ndtv.weatherreporting.weatherclasses;

import java.util.Comparator;

public class TemperatureComparator implements Comparator<Weather> {

	private int acceptableVariance;
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: variance
	 * @return: none
	 * @description: constructor
	 */
	public TemperatureComparator(int variance) {
		acceptableVariance = variance;
	}
	
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: Weather objects w1 and w2
	 * @return: integer that indicates difference in temperature values of both the objects
	 * @description: calculates difference in temperature in both the objects
	 */
	@Override
	public int compare(Weather w1, Weather w2) {	
		return Math.abs((int) (w1.getTempInDegrees() - w2.getTempInDegrees()));
	}
	
	/*
	 * @auth: Aparna Manjunath
	 * @params: driver
	 * @return: Weather objects w1 and w2
	 * @description: calculates difference in temperature in both the objects. Throws MatcherException if the difference is more than acceptable variance
	 */
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

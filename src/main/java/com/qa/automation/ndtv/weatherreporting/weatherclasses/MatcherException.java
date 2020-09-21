package com.qa.automation.ndtv.weatherreporting.weatherclasses;

public class MatcherException extends Exception {

	/**
	 * User defined Exception to throw Matcher exception when Weather objects have mismatch in temperature
	 */
	private static final long serialVersionUID = 1L;
	
	public MatcherException(String s) {
		super(s);
	}

}

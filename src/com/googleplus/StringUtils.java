package com.googleplus;

public class StringUtils {

	public static final boolean isEmpty(String input) {
		return null == input || input.length() == 0;
	}

	public static final boolean isNotEmpty(String input) {
		return !isEmpty(input);
	}
}

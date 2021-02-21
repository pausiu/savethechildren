package com.savethechildren;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

	private static final String regex = "[(.+)(@+)0-9A-Za-z _-]+";
	private static final String regexNo = "[0-9]+";

	public static boolean validate(String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static boolean validateNo(String input) {
		Pattern pattern = Pattern.compile(regexNo);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static void main(String args[]) {
		List<String> emails = new ArrayList<String>();
		emails.add("98748.2371. 034@82 x49032-179");
		emails.add("mdafdhsafkjadshfkjlas");
		emails.add("fhdlksajhfdas932049324");

		for (String value : emails) {
			System.out.println("The validity of " + value + " is " + InputChecker.validate(value));
		}

		List<String> numbers = new ArrayList<String>();
		numbers.add("100000000");
		numbers.add("10000.000.");
		numbers.add("998897");

		for (String number : numbers) {
			System.out.println("The validity of " + number + " is " + InputChecker.validateNo(number));
		}
	}
}
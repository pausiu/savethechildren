package com.savethechildren;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private static final String regex = "^(.+)@(.+)$";

	public static boolean validate(String email) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static void main(String args[]) {
		List<String> emails = new ArrayList<String>();
		// valid email addresses
		emails.add("alice@example.com");
		emails.add("alice.bob@example.co.in");
		emails.add("alice#@example.me.org");
		// invalid email addresses
		emails.add("alice.example.com");
		emails.add("alice#example.com");
		emails.add("@example.me.org");

		for (String value : emails) {
			System.out.println("The Email address " + value + " is " + EmailValidator.validate(value));
		}
	}
}
package com.savethechildren;

public class CreditCardValidator {

	public static void main(String[] args) {
		String creditCardNumber = "4659433483663190";
		System.out.println(creditCardNumber + " is " + (validitychk(creditCardNumber) ? "valid" : "invalid"));
	}

	public static boolean validitychk(String creditCardNumber) {
		long creditCardNumberLong = Long.parseLong(creditCardNumber);
		return validitychk(creditCardNumberLong);
	}

	// Return true if the card number is valid
	public static boolean validitychk(long cnumber) {
		return (thesize(cnumber) >= 13 && thesize(cnumber) <= 16) && (prefixmatch(cnumber, 4) || prefixmatch(cnumber, 5)
				|| prefixmatch(cnumber, 37) || prefixmatch(cnumber, 6))
				&& ((sumdoubleeven(cnumber) + sumodd(cnumber)) % 10 == 0);
	}

	// Get the result from Step 2
	private static int sumdoubleeven(long cnumber) {
		int sum = 0;
		String num = cnumber + "";
		for (int i = thesize(cnumber) - 2; i >= 0; i -= 2)
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
		return sum;
	}

	// Return this cnumber if it is a single digit, otherwise,
	// return the sum of the two digits
	private static int getDigit(int cnumber) {
		if (cnumber < 9)
			return cnumber;
		return cnumber / 10 + cnumber % 10;
	}

	// Return sum of odd-place digits in cnumber
	private static int sumodd(long cnumber) {
		int sum = 0;
		String num = cnumber + "";
		for (int i = thesize(cnumber) - 1; i >= 0; i -= 2)
			sum += Integer.parseInt(num.charAt(i) + "");
		return sum;
	}

	// Return true if the digit d is a prefix for cnumber
	private static boolean prefixmatch(long cnumber, int d) {
		return getprefx(cnumber, thesize(d)) == d;
	}

	// Return the number of digits in d
	private static int thesize(long d) {
		String num = d + "";
		return num.length();
	}

	// Return the first k number of digits from
	// number. If the number of digits in number
	// is less than k, return number.
	private static long getprefx(long cnumber, int k) {
		if (thesize(cnumber) > k) {
			String num = cnumber + "";
			return Long.parseLong(num.substring(0, k));
		}
		return cnumber;
	}
}
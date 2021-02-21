package com.savethechildren.h2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity annotation specifies that the class is mapped to a database table.
@Entity
public class Donation {

	// @Id annotation specifies the primary key of an entity.
	// @GeneratedValue provides the generation strategy specification for the
	// primary key values.
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String creditCardNumber;
	private double amount;
	private String phoneNo;
	private String emailAddress;

	// Default constructor.
	public Donation() {
	}

	// Parameterized constructor.
	public Donation(int id, String name, String creditCardNumber, double amount, String phoneNo, String emailAddress) {
		this.id = id;
		this.name = name;
		this.creditCardNumber = creditCardNumber;
		this.amount = amount;
		this.phoneNo = phoneNo;
		this.emailAddress = emailAddress;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public double getAmount() {
		return amount;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String toString() {
		return "" + id + ";" + name + ";" + creditCardNumber + ";" + amount + ";" + phoneNo + ";" + emailAddress;
	}

}
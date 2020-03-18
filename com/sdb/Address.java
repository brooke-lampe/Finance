package com.sdb;
/**
 * Address.java
 * Brooke Lampe
 * 2017/01/29
 * This class provides support for the Address object
 */

public class Address {

	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	public Address(String street, String city, String state, String zipcode, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCountry() {
		return country;
	}
}

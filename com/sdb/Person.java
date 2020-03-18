package com.sdb;
/**
 * Person.java
 * Brooke Lampe
 * 2017/01/29
 * This class provides support for the Person object
 */

public class Person {

	private Integer personId;
	private String personCode;
	private Broker broker;
	private String firstName;
	private String lastName;
	private Address address;
	private String email;
	
	public Person(String personCode, Broker broker, String firstName, String lastName, Address address, String email) {
		this.personCode = personCode;
		this.broker = broker;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonCode() {
		return personCode;
	}

	public Broker getBroker() {
		return broker;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
	
	public static final int comparePerson(Person a, Person b) {
    	int result;
			
    	try {
			result = a.getLastName().compareTo(b.getLastName());
				
			if(result == 0) {
				result = a.getFirstName().compareTo(b.getFirstName());
				return result;
			} else {
				return result;
			}
		} catch (NullPointerException e) {
			if(a == null && b != null) {
				return 1;
			} else if(a != null && b == null) {
				return -1;
			}
			return 0;
		}
    }
	
	public static final int compareBroker(Person a, Person b) {
    	int result;
			
    	try {
			result = a.getBroker().getClassification().compareTo(b.getBroker().getClassification());
				
			if(result != 0) {
				return result;
			} else {
				result = a.getLastName().compareTo(b.getLastName());
				if(result != 0) {
					return result;
				} else {
					result = a.getFirstName().compareTo(b.getFirstName());
					return result;
				}
			}
		} catch (NullPointerException e) {
			if(a == null && b != null) {
				return 1;
			} else if(a != null && b == null) {
				return -1;
			}
			return 0;
		}
	}
	
	public String printName() {
		try {
			String printName = getLastName() + ", " + getFirstName();
			return printName;
		} catch (NullPointerException e) {
			return "none";
		}
	}
}

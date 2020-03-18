package com.sdb;
/**
 * Broker.java
 * Brooke Lampe
 * 2017/01/29
 * This class provides support for the Broker object
 */

public class Broker {

	private String classification;
	private String identifier;
	
	public Broker(String classification, String identifier) {
		this.classification = classification;
		this.identifier = identifier;
	}

	public String getClassification() {
		return classification;
	}

	public String getIdentifier() {
		return identifier;
	}
}

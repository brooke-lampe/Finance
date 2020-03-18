package com.sdb;
/**
 * PersonToXML.java
 * Brooke Lampe
 * 2017/02/04
 * This class will print a Person object to an .xml file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PersonToXML {

	public static void PersonToXML(ArrayList<Person> personList){

		//This method creates a .xml file for the Person object
		File y = new File("data/Persons.xml");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(y);
			
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		
		pw.println("<Person>");
		for(int i = 0; i < personList.size(); i++) {
			boolean exists = true;
			
			try {
				personList.get(i).getBroker().getClassification();
			} catch(NullPointerException n) {
				exists = false;
			}
			
			if(exists == true) {
				pw.println("\t" + "<person>");
					pw.println("\t" + "\t" + "<code>" + personList.get(i).getPersonCode() + "</code>");
					pw.println("\t" + "\t" + "<secIdentifier>" + personList.get(i).getBroker().getIdentifier() + "</secIdentifier>");
					pw.println("\t" + "\t" + "<type>" + personList.get(i).getBroker().getClassification() + "</type>");
					pw.println("\t" + "\t" + "<firstName>" + personList.get(i).getFirstName() + "</firstName>");
					pw.println("\t" + "\t" + "<lastName>" + personList.get(i).getLastName() + "</lastName>");
					pw.println("\t" + "\t" + "<address>");
						pw.println("\t" + "\t" + "\t" + "<street>" + personList.get(i).getAddress().getStreet() + "</street>");
						pw.println("\t" + "\t" + "\t" + "<city>" + personList.get(i).getAddress().getCity() + "</city>");
						pw.println("\t" + "\t" + "\t" + "<state>" + personList.get(i).getAddress().getState() + "</state>");
						pw.println("\t" + "\t" + "\t" + "<zipcode>" + personList.get(i).getAddress().getZipcode() + "</zipcode>");
						pw.println("\t" + "\t" + "\t" + "<country>" + personList.get(i).getAddress().getCountry() + "</country>");
					pw.println("\t" + "\t" + "</address>");
					pw.println("\t" + "\t" + "<emails>");
					//Iterate over an array of all email addresses
					String emails[] = personList.get(i).getEmail().split(",");
					for(int j = 0; j < emails.length; j++) {
						emails[j] = emails[j].trim();
						pw.println("\t" + "\t" + "\t" + "<string>" + emails[j] + "</string>");
					}
					pw.println("\t" + "\t" + "</emails>");
				pw.println("\t" + "</person>");
			} else {
				pw.println("\t" + "<person>");
					pw.println("\t" + "\t" + "<code>" + personList.get(i).getPersonCode() + "</code>");
					pw.println("\t" + "\t" + "<firstName>" + personList.get(i).getFirstName() + "</firstName>");
					pw.println("\t" + "\t" + "<lastName>" + personList.get(i).getLastName() + "</lastName>");
					pw.println("\t" + "\t" + "<address>");
						pw.println("\t" + "\t" + "\t" + "<street>" + personList.get(i).getAddress().getStreet() + "</street>");
						pw.println("\t" + "\t" + "\t" + "<city>" + personList.get(i).getAddress().getCity() + "</city>");
						pw.println("\t" + "\t" + "\t" + "<state>" + personList.get(i).getAddress().getState() + "</state>");
						pw.println("\t" + "\t" + "\t" + "<zipcode>" + personList.get(i).getAddress().getZipcode() + "</zipcode>");
						pw.println("\t" + "\t" + "\t" + "<country>" + personList.get(i).getAddress().getCountry() + "</country>");
					pw.println("\t" + "\t" + "</address>");
					pw.println("\t" + "\t" + "<emails>");
					//Iterate over an array of all email addresses
					String emails[] = personList.get(i).getEmail().split(",");
					for(int j = 0; j < emails.length; j++) {
						emails[j] = emails[j].trim();
						pw.println("\t" + "\t" + "\t" + "<string>" + emails[j] + "</string>");
					}
					pw.println("\t" + "\t" + "</emails>");
				pw.println("\t" + "</person>");
			}
		}
		pw.println("</Person>");
		pw.close();
	}
}

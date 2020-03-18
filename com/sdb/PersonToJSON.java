package com.sdb;
/**
 * PersonToJSON.java
 * Brooke Lampe
 * 2017/02/04
 * This class will print a Person object to a .json file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PersonToJSON {

	public static void PersonToJSON(ArrayList<Person> personList){

		//This method creates a .json file for the Person object
		File y = new File("data/Persons.json");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(y);
			
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		pw.println("{");
		pw.println("\"Person\": [");
		for(int i = 0; i < personList.size(); i++) {
			boolean exists = true;
			
			try {
				personList.get(i).getBroker().getClassification();
			} catch(NullPointerException n) {
				exists = false;
			}
			
			if(exists == true) {
				pw.println("\t" + "{");
					pw.println("\t" + "\t" + "\"code\": \"" + personList.get(i).getPersonCode() + "\",");
					pw.println("\t" + "\t" + "\"secIdentifier\": \"" + personList.get(i).getBroker().getIdentifier() + "\",");
					pw.println("\t" + "\t" + "\"type\": \"" + personList.get(i).getBroker().getClassification() + "\",");
					pw.println("\t" + "\t" + "\"firstName\": \"" + personList.get(i).getFirstName() + "\",");
					pw.println("\t" + "\t" + "\"lastName\": \"" + personList.get(i).getLastName() + "\",");
					pw.println("\t" + "\t" + "\"address\": {");
						pw.println("\t" + "\t" + "\t" + "\"street\": \"" + personList.get(i).getAddress().getStreet() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"city\": \"" + personList.get(i).getAddress().getCity() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"state\": \"" + personList.get(i).getAddress().getState() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"zipcode\": \"" + personList.get(i).getAddress().getZipcode() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"country\": \"" + personList.get(i).getAddress().getCountry() + "\"");
					pw.println("\t" + "\t" + "},");
					pw.println("\t" + "\t" + "\"emails\": [");
					//Iterate over an array of all email addresses
					String emails[] = personList.get(i).getEmail().split(",");
					for(int j = 0; j < emails.length; j++) {
						if(j < emails.length - 1) {
							emails[j] = emails[j].trim();
							pw.println("\t" + "\t" + "\t" + "\"" + emails[j] + "\",");
						} else {
							emails[j] = emails[j].trim();
							pw.println("\t" + "\t" + "\t" + "\"" + emails[j] + "\"");
						}
					}
					pw.println("\t" + "\t" + "]");
				if(i < personList.size() - 1) {
					pw.println("\t" + "},");
				} else {
					pw.println("\t" + "}");
				}
			} else {
				pw.println("\t" + "{");
					pw.println("\t" + "\t" + "\"code\": \"" + personList.get(i).getPersonCode() + "\",");
					pw.println("\t" + "\t" + "\"firstName\": \"" + personList.get(i).getFirstName() + "\",");
					pw.println("\t" + "\t" + "\"lastName\": \"" + personList.get(i).getLastName() + "\",");
					pw.println("\t" + "\t" + "\"address\": {");
						pw.println("\t" + "\t" + "\t" + "\"street\": \"" + personList.get(i).getAddress().getStreet() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"city\": \"" + personList.get(i).getAddress().getCity() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"state\": \"" + personList.get(i).getAddress().getState() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"zipcode\": \"" + personList.get(i).getAddress().getZipcode() + "\",");
						pw.println("\t" + "\t" + "\t" + "\"country\": \"" + personList.get(i).getAddress().getCountry() + "\"");
					pw.println("\t" + "\t" + "},");
					pw.println("\t" + "\t" + "\"emails\": [");
					//Iterate over an array of all email addresses
					String emails[] = personList.get(i).getEmail().split(",");
					for(int j = 0; j < emails.length; j++) {
						if(j < emails.length - 1) {
							emails[j] = emails[j].trim();
							pw.println("\t" + "\t" + "\t" + "\"" + emails[j] + "\",");
						} else {
							emails[j] = emails[j].trim();
							pw.println("\t" + "\t" + "\t" + "\"" + emails[j] + "\"");
						}
					}
					pw.println("\t" + "\t" + "]");
				if(i < personList.size() - 1) {
					pw.println("\t" + "},");
				} else {
					pw.println("\t" + "}");
				}
			}
		}
		pw.println("]}");
		pw.close();
	}
}

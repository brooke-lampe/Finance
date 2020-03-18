package com.sdb;
/**
 * LoadPerson.java
 * Brooke Lampe
 * 2017/03/08
 * This class will load person data from the database into Person objects
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadPerson {

	public static void getPerson() {
		Connection conn = Connect.Connect();
		
		String selectPersonData = "SELECT personId, personCode, classification, identifier, firstName, lastName, "
		+ "street, city, stateId, zipcode, countryId FROM person";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(selectPersonData);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer personId = rs.getInt("personId");
				String personCode = rs.getString("personCode");
				String classification = rs.getString("classification");
				String identifier = rs.getString("identifier");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String street = rs.getString("street");
				String city = rs.getString("city");
				Integer stateId = rs.getInt("stateId");
				String zipcode = rs.getString("zipcode");
				Integer countryId = rs.getInt("countryId");
				
				String state = Search.findStateById(stateId);
				String country = Search.findCountryById(countryId);
				String email = Search.findEmailById(personId);
				
				Address address = new Address(street, city, state, zipcode, country);
				Broker broker = new Broker(classification, identifier);
				Person person = new Person(personCode, broker, firstName, lastName, address, email);
				person.setPersonId(personId);
				Data.getPersonList().add(person);
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
	}
}

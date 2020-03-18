package com.sdb; //DO NOT CHANGE THIS

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class PortfolioData {

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		Connection conn = Connect.Connect();
		
		String deleteEmail = "DELETE FROM email";
		String updateOwner = "UPDATE portfolio SET ownerId = null";
		String updateManager = "UPDATE portfolio SET managerId = null";
		String updateBeneficiary = "UPDATE portfolio SET beneficiaryId = null";
		String deletePerson = "DELETE FROM person";
		
		PreparedStatement ps = null;
		 
		try {
			ps = conn.prepareStatement(deleteEmail);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(updateOwner);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(updateManager);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(updateBeneficiary);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(deletePerson);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		Connection conn = Connect.Connect();
		
		String deleteEmail = "DELETE FROM email WHERE personId = (SELECT personId FROM person WHERE personCode = ?)";
		String updateOwner = "UPDATE portfolio SET ownerId = null WHERE ownerId = (SELECT personId FROM person WHERE personCode = ?)";
		String updateManager = "UPDATE portfolio SET managerId = null WHERE managerId = (SELECT personId FROM person WHERE personCode = ?)";
		String updateBeneficiary = "UPDATE portfolio SET beneficiaryId = null WHERE beneficiaryId = (SELECT personId FROM person WHERE personCode = ?)";
		String deletePerson = "DELETE FROM person WHERE personCode = ?";
		
		PreparedStatement ps = null;
		 
		try {
			ps = conn.prepareStatement(deleteEmail);
			ps.setString(1, personCode);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(updateOwner);
			ps.setString(1, personCode);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(updateManager);
			ps.setString(1, personCode);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(updateBeneficiary);
			ps.setString(1, personCode);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(deletePerson);
			ps.setString(1, personCode);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>brokerType</code> will either be "E" or "J" (Expert or Junior) or 
	 * <code>null</code> if the person is not a broker.
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @param brokerType
	 */
	public static void addPerson(String personCode, String firstName, String lastName,
			String street, String city, String state, String zip, String country, String brokerType, String secBrokerId) {
		Connection conn = Connect.Connect();
		
		String addPerson = "INSERT INTO person (personCode, firstName, lastName, classification, identifier, street, city, stateId, zipcode, countryId) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = null;
		
		if(Search.findPersonByCode(personCode) != null) {
			throw new RuntimeException("Error:  Cannot insert a person with a duplicate personCode.");
		}
		
		if(Search.findBrokerByIdentifier(secBrokerId) != null) {
			throw new RuntimeException("Error:  Cannot insert a broker with a duplicate secIdentifier.");
		}
		 
		try {
			ps = conn.prepareStatement(addPerson);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, brokerType);
			ps.setString(5, secBrokerId);
			ps.setString(6, street);
			ps.setString(7, city);
			
			//Search for the state in the state table.  If not found, create the state
			Integer stateId = Search.findStateIdByState(state);
			if(stateId == 0) {
				if(state == null || state.compareTo("") == 0) {
					ps.setNull(8, stateId);
				} else {
					addState(state);
					stateId = Search.findStateIdByState(state);
					ps.setInt(8, stateId);
				}
			} else {
				ps.setInt(8, stateId);
			}
			
			ps.setString(9, zip);
			
			//Search for the country in the country table.  If not found, create the country
			Integer countryId = Search.findCountryIdByCountry(country);
			if(countryId == 0) {
				if(country == null || country.compareTo("") == 0) {
					ps.setNull(10, countryId);
				} else {
					addCountry(country);
					countryId = Search.findCountryIdByCountry(country);
					ps.setInt(10, countryId);
				}
			} else {
				ps.setInt(10, countryId);
			}
			
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	public static void addState(String state) {
		Connection conn = Connect.Connect();
		
		String addState = "INSERT INTO state (stateName) VALUES (?)";
		
		PreparedStatement ps = null;
		 
		try {
			ps = conn.prepareStatement(addState);
			ps.setString(1, state);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	public static void addCountry(String country) {
		Connection conn = Connect.Connect();
		
		String addCountry = "INSERT INTO country (countryName) VALUES (?)";
		
		PreparedStatement ps = null;
		 
		try {
			ps = conn.prepareStatement(addCountry);
			ps.setString(1, country);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = Connect.Connect();
		
		String addEmail = "INSERT INTO email (emailAddress, personId) "
				+ "VALUES (?, (SELECT personId FROM person WHERE personCode = ?))";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(addEmail);
			ps.setString(1, email);
			ps.setString(2, personCode);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		Connect.Disconnect(conn, ps);
	}

	/**
	 * Removes all asset records from the database
	 */
	public static void removeAllAssets() {
		Connection conn = Connect.Connect();
		
		String deleteEquity = "DELETE FROM equity";
		String deleteAsset = "DELETE FROM asset";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(deleteEquity);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(deleteAsset);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		Connect.Disconnect(conn, ps);
	}

	/**
	 * Removes the asset record from the database corresponding to the
	 * provided <code>assetCode</code>
	 * @param assetCode
	 */
	public static void removeAsset(String assetCode) {
		Connection conn = Connect.Connect();
		
		String deleteEquity = "DELETE FROM equity WHERE assetId = (SELECT assetId FROM asset WHERE assetCode = ?)";
		String deleteAsset = "DELETE FROM asset WHERE assetCode = (?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(deleteEquity);
			ps.setString(1, assetCode);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(deleteAsset);
			ps.setString(1, assetCode);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Adds a deposit account asset record to the database with the
	 * provided data. 
	 * @param assetCode
	 * @param label
	 * @param apr
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) {
		Connection conn = Connect.Connect();
		
		String addDepositAccount = "INSERT INTO asset (assetCode, assetType, label, apr) "
				+ "VALUES (?, 'D', ?, ?)";
		
		if(Search.findAssetByCode(assetCode) != null) {
			throw new RuntimeException("Error:  Cannot insert an asset with a duplicate assetCode.");
		}
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(addDepositAccount);
			ps.setString(1, assetCode);
			ps.setString(2, label);
			ps.setDouble(3, apr);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Adds a private investment asset record to the database with the
	 * provided data. 
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param baseOmega
	 * @param totalValue
	 */
	public static void addPrivateInvestment(String assetCode, String label, Double quarterlyDividend, 
			Double baseRateOfReturn, Double baseOmega, Double totalValue) {
		Connection conn = Connect.Connect();
		
		String addPrivateInvestment = "INSERT INTO asset (assetCode, assetType, label, quarterlyDividend, baseRateOfReturn, omegaMeasure, totalValue) "
				+ "VALUES (?, 'P', ?, ?, ?, ?, ?)";
		
		if(Search.findAssetByCode(assetCode) != null) {
			throw new RuntimeException("Error:  Cannot insert an asset with a duplicate assetCode.");
		}
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(addPrivateInvestment);
			ps.setString(1, assetCode);
			ps.setString(2, label);
			ps.setDouble(3, quarterlyDividend);
			ps.setDouble(4, baseRateOfReturn);
			ps.setDouble(5, baseOmega);
			ps.setDouble(6, totalValue);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Adds a stock asset record to the database with the
	 * provided data. 
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param beta
	 * @param stockSymbol
	 * @param sharePrice
	 */
	public static void addStock(String assetCode, String label, Double quarterlyDividend, 
			Double baseRateOfReturn, Double beta, String stockSymbol, Double sharePrice) {
		Connection conn = Connect.Connect();
		
		String addStock = "INSERT INTO asset (assetCode, assetType, label, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice) "
				+ "VALUES (?, 'S', ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = null;
		
		if(Search.findAssetByCode(assetCode) != null) {
			throw new RuntimeException("Error:  Cannot insert an asset with a duplicate assetCode.");
		}
		
		try {
			ps = conn.prepareStatement(addStock);
			ps.setString(1, assetCode);
			ps.setString(2, label);
			ps.setDouble(3, quarterlyDividend);
			ps.setDouble(4, baseRateOfReturn);
			ps.setDouble(5, beta);
			ps.setString(6, stockSymbol);
			ps.setDouble(7, sharePrice);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}

	/**
	 * Removes all portfolio records from the database
	 */
	public static void removeAllPortfolios() {
		Connection conn = Connect.Connect();
		
		String deleteEquity = "DELETE FROM equity";
		String deletePortfolio = "DELETE FROM portfolio";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(deleteEquity);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(deletePortfolio);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Removes the portfolio record from the database corresponding to the
	 * provided <code>portfolioCode</code>
	 * @param portfolioCode
	 */
	public static void removePortfolio(String portfolioCode) {
		Connection conn = Connect.Connect();
		
		String deleteEquity = "DELETE FROM equity WHERE portfolioId = (SELECT portfolioId FROM portfolio WHERE portfolioCode = ?)";
		String deletePortfolio = "DELETE FROM portfolio WHERE portfolioCode = (?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(deleteEquity);
			ps.setString(1, portfolioCode);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(deletePortfolio);
			ps.setString(1, portfolioCode);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Adds a portfolio records to the database with the given data.  If the portfolio has no
	 * beneficiary, the <code>beneficiaryCode</code> will be <code>null</code>
	 * @param portfolioCode
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 */
	public static void addPortfolio(String portfolioCode, String ownerCode, String managerCode, String beneficiaryCode) {
		Connection conn = Connect.Connect();
		
		String addPortfolio = "INSERT INTO portfolio (portfolioCode, ownerId, managerId, beneficiaryId) VALUES (?, ?, ?, ?)";
		
		if(Search.findPortfolioByCode(portfolioCode) != null) {
			throw new RuntimeException("Error:  Cannot insert a portfolio with a duplicate portfolioCode.");
		}
		
		PreparedStatement ps = null;
		Integer ownerId = 0;
		Integer managerId = 0;
		Integer beneficiaryId = 0;
		
		ownerId = Search.findPersonIdByPersonCode(ownerCode);
		managerId = Search.findPersonIdByPersonCode(managerCode);
		beneficiaryId = Search.findPersonIdByPersonCode(beneficiaryCode);
		
		try {
			ps = conn.prepareStatement(addPortfolio);
			ps.setString(1, portfolioCode);
			if(ownerId != 0) {
				ps.setInt(2, ownerId);
			} else {
				ps.setNull(2, ownerId);
			}
			
			if(managerId != 0) {
				ps.setInt(3, managerId);
			} else {
				ps.setNull(3, managerId);
			}
			
			if(beneficiaryId != 0) {
				ps.setInt(4, beneficiaryId);
			} else {
				ps.setNull(4, beneficiaryId);
			}
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		Connect.Disconnect(conn, ps);
	}
	
	/**
	 * Associates the asset record corresponding to <code>assetCode</code> with the 
	 * portfolio corresponding to the provided <code>portfolioCode</code>.  The third 
	 * parameter, <code>value</code> is interpreted as a <i>balance</i>, <i>number of shares</i>
	 * or <i>stake percentage</i> depending on the type of asset the <code>assetCode</code> is
	 * associated with.
	 * @param portfolioCode
	 * @param assetCode
	 * @param value
	 */
	public static void addAsset(String portfolioCode, String assetCode, double value) {
		Connection conn = Connect.Connect();
		
		String addEquity = "INSERT INTO equity (portfolioId, assetId, worth)"
				+ "VALUES ((SELECT portfolioId FROM portfolio WHERE portfolioCode = ?), "
				+ "(SELECT assetId FROM asset WHERE assetCode = ?), ?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(addEquity);
			ps.setString(1, portfolioCode);
			ps.setString(2, assetCode);
			ps.setDouble(3, value);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps);
	}
}
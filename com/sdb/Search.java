package com.sdb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Search.java
 * Brooke Lampe
 * 2017/02/09
 * This class searches data using a given code
 */

public class Search {
	
	//Finds a Person object given a personCode and returns the Person object
	public static Person findPersonByCode (String personCode) {
	    for(int i = 0; i < Data.getPersonList().size(); i++) {
	        Person person = Data.getPersonList().get(i);
	        if(person.getPersonCode().compareTo(personCode) == 0){
	             return person;
	        }
	    }
	    return null;
	}
	
	//Finds a Person object given a personCode and returns the Person object
	public static Person findBrokerByIdentifier (String identifier) {
	    for(int i = 0; i < Data.getPersonList().size(); i++) {
	        Person person = Data.getPersonList().get(i);
	        if(person.getBroker().getIdentifier().compareTo(identifier) == 0){
	             return person;
	        }
	    }
	    return null;
	}
	
	//Finds a Person object given a personId and returns the Person object
	public static Person findPersonById (Integer personId) {
	    for(int i = 0; i < Data.getPersonList().size(); i++) {
	        Person person = Data.getPersonList().get(i);
	        if(person.getPersonId().compareTo(personId) == 0){
	             return person;
	        }
	    }
	    return null;
	}
	
	//Finds a personId given a personCode and returns an integer
	public static Integer findPersonIdByPersonCode (String personCode) {
		Connection conn = Connect.Connect();
		
		String selectPersonId = "SELECT personId FROM person WHERE personCode = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer personId = 0;
		
		try {
			ps = conn.prepareStatement(selectPersonId);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				personId = rs.getInt("personId");
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return personId;
	}
	
	//Finds an Asset object given an assetCode and returns the Asset object
	public static Asset findAssetByCode (String assetCode) {
	    for(int i = 0; i < Data.getAssetList().size(); i++) {
	        Asset asset = Data.getAssetList().get(i);
	        if(asset.getAssetCode().compareTo(assetCode) == 0){
	             return asset;
	        }
	    }
	    return null;
	}
	
	//Finds an Asset object given an assetId and returns the Asset object
	public static Asset findAssetById (Integer assetId) {
	    for(int i = 0; i < Data.getAssetList().size(); i++) {
	        Asset asset = Data.getAssetList().get(i);
	        if(asset.getAssetId().compareTo(assetId) == 0){
	             return asset;
	        }
	    }
	    return null;
	}
	
	//Finds an assetId given an assetCode and returns an integer
	public static Integer findAssetIdByAssetCode (String assetCode) {
		Connection conn = Connect.Connect();
		
		String selectAssetId = "SELECT assetId FROM asset WHERE assetCode = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer assetId = 0;
		
		try {
			ps = conn.prepareStatement(selectAssetId);
			ps.setString(1, assetCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				assetId = rs.getInt("assetId");
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return assetId;
	}
	
	//Finds Asset objects given a portfolioId and returns an arrayList of Asset objects
	public static ArrayList<Asset> findEquityById(Integer portfolioId) {
		Connection conn = Connect.Connect();
		
		String selectEquity = "SELECT equityId, assetId, worth FROM equity WHERE portfolioId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Asset> asset = new ArrayList<Asset>();
		
		try {
			ps = conn.prepareStatement(selectEquity);
			ps.setInt(1, portfolioId);
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer equityId = rs.getInt("equityId");
				Integer assetId = rs.getInt("assetId");
				Double value = rs.getDouble("worth");
			
				Asset found = Search.findAssetById(assetId);
				
				if(found.getType().compareTo("D") == 0) {
					DepositAccount deposit = new DepositAccount((DepositAccount) found);
					deposit.setBalance(value);
					deposit.setAssetId(assetId);
					deposit.setEquityId(equityId);
					asset.add(deposit);
				} else if(found.getType().compareTo("P") == 0) {
					PrivateInvestment investment = new PrivateInvestment((PrivateInvestment) found);
					investment.setPercentage(value);
					investment.setAssetId(assetId);
					investment.setEquityId(equityId);
					asset.add(investment);
				} else if(found.getType().compareTo("S") == 0) {
					Stock stock = new Stock((Stock) found);
					stock.setSharesOwned(value);
					stock.setAssetId(assetId);
					stock.setEquityId(equityId);
					asset.add(stock);
				}
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return asset;
	}
	
	//Finds a state name given a stateId and returns a string
	public static String findStateById(Integer stateId) {
		Connection conn = Connect.Connect();
		
		String selectState = "SELECT stateName FROM state WHERE stateId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String state = null;
		
		try {
			ps = conn.prepareStatement(selectState);
			ps.setInt(1, stateId);
			rs = ps.executeQuery();
			if(rs.next()) {
				state = rs.getString("stateName");
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return state;
	}
	
	//Finds a Portfolio object given a portfolioCode and returns the Portfolio object
	public static Portfolio findPortfolioByCode (String portfolioCode) {
	    for(int i = 0; i < Data.getPortfolioList().size(); i++) {
	        Portfolio portfolio = Data.getPortfolioList().get(i);
	        if(portfolio.getPortfolioCode().compareTo(portfolioCode) == 0){
	             return portfolio;
	        }
	    }
	    return null;
	}
	
	//Finds a stateId given a state name and returns an integer
	public static Integer findStateIdByState (String state) {
		Connection conn = Connect.Connect();
		
		//Look for state in stateName column of the database
		String selectStateId = "SELECT stateId FROM state WHERE stateName = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer stateId = 0;
		boolean stateName = false;
		
		try {
			ps = conn.prepareStatement(selectStateId);
			ps.setString(1, state);
			rs = ps.executeQuery();
			if(rs.next()) {
				stateId = rs.getInt("stateId");
				stateName = true;
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		if(stateName == true) {
			Connect.Disconnect(conn, ps, rs);
			return stateId;
		}
		
		//Look for state in stateAbbreviation column of the database
		selectStateId = "SELECT stateId FROM state WHERE stateAbbreviation = ?";
		ps = null;
		rs = null;
		stateId = 0;
		
		try {
			ps = conn.prepareStatement(selectStateId);
			ps.setString(1, state);
			rs = ps.executeQuery();
			if(rs.next()) {
				stateId = rs.getInt("stateId");
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return stateId;
	}
	
	//Finds a country name given a countryId and returns a string
	public static String findCountryById(Integer countryId) {
		Connection conn = Connect.Connect();
		
		String selectCountry = "SELECT countryName FROM country WHERE countryId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String country = null;
		
		try {
			ps = conn.prepareStatement(selectCountry);
			ps.setInt(1, countryId);
			rs = ps.executeQuery();
			if(rs.next()) {
				country = rs.getString("countryName");
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return country;
	}
	
	//Finds a countryId given a country name and returns an integer
	public static Integer findCountryIdByCountry (String country) {
		Connection conn = Connect.Connect();
		
		String selectCountryId = "SELECT countryId FROM country WHERE countryName = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer countryId = 0;
		boolean countryName = false;
		
		try {
			ps = conn.prepareStatement(selectCountryId);
			ps.setString(1, country);
			rs = ps.executeQuery();
			if(rs.next()) {
				countryId = rs.getInt("countryId");
				countryName = true;
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		if(countryName == true) {
			Connect.Disconnect(conn, ps, rs);
			return countryId;
		}
		
		selectCountryId = "SELECT countryId FROM country WHERE countryAbbreviation = ?";
		ps = null;
		rs = null;
		countryId = 0;
		
		try {
			ps = conn.prepareStatement(selectCountryId);
			ps.setString(1, country);
			rs = ps.executeQuery();
			if(rs.next()) {
				countryId = rs.getInt("countryId");
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return countryId;
	}
	
	//Finds a person's email addresses given a personId and returns a comma-delimited string of email addresses
	public static String findEmailById(Integer personId) {
		Connection conn = Connect.Connect();
		
		String selectEmail = "SELECT emailAddress FROM email WHERE personId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String emails = "";
		int count = 0;
	
		try {
			ps = conn.prepareStatement(selectEmail);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				if(count == 0) {
					emails = rs.getString("emailAddress");
					count++;
				} else {
					emails = emails + "," + rs.getString("emailAddress");
				}
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
		return emails;
	}
}
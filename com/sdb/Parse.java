package com.sdb;
/**
 * Parse.java
 * Brooke Lampe
 * 2017/01/30
 * This class parses information from a line and stores it in objects
 */

import java.util.ArrayList;

public class Parse {
	
	public static Person parsePerson (String line) {
		String lineArray[] = line.split(";", 5);
		
		String personCode, tempBroker, tempName, tempAddress, email;
		
		//Determine personCode
		personCode = lineArray[0];
		
		//Determine whether or not broker
		tempBroker = lineArray[1];
		Broker broker = null;
		if(tempBroker.length() > 0) {
			String brokerArray[] = tempBroker.split(",");
			//Trim unneeded white space
			brokerArray[0] = brokerArray[0].trim();
			brokerArray[1] = brokerArray[1].trim();
			broker = new Broker(brokerArray[0], brokerArray[1]);
		}
		
		//Determine name
		tempName = lineArray[2];
		String nameArray[] = tempName.split(",");
		//Trim unneeded white space
		String lastName = nameArray[0].trim();
		String firstName = nameArray[1].trim();
		
		//Determine address and instantiate an address object
		tempAddress = lineArray[3];
		String addressArray[] = tempAddress.split(",");
		
		//Trim unneeded white space
		addressArray[0] = addressArray[0].trim();
		addressArray[1] = addressArray[1].trim();
		addressArray[2] = addressArray[2].trim();
		addressArray[3] = addressArray[3].trim();
		addressArray[4] = addressArray[4].trim();
		Address address = new Address(addressArray[0], addressArray[1], addressArray[2], addressArray[3], addressArray[4]);
		
		//Determine email
		email = "";
		if(lineArray.length > 4) {
			email = lineArray[4];
		}
		
		Person person = new Person(personCode, broker, firstName, lastName, address, email);
		return person;
	}
	
	public static Asset parseAsset (String line) {
		String lineArray[] = line.split(";");
		
		String assetCode = lineArray[0].trim();
		String type = lineArray[1].trim();
		String label = lineArray[2].trim();
		
		//Create a DepositAccount, Stock, or PrivateInvestment object depending on the type
		if(type.compareTo("D") == 0) {
			double apr = Double.parseDouble(lineArray[3]);
			DepositAccount deposit = new DepositAccount(assetCode, type, label, apr);
			return deposit;
		}

		if(type.compareTo("S") == 0) {
			double quarterlyDividend = Double.parseDouble(lineArray[3]);
			double baseRateOfReturn = Double.parseDouble(lineArray[4]);
			double betaMeasure = Double.parseDouble(lineArray[5]);
			String stockSymbol = lineArray[6].trim();
			double sharePrice = Double.parseDouble(lineArray[7]);
			Stock stock = new Stock(assetCode, type, label, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
			return stock;
		}

		if(type.compareTo("P") == 0) {
			double quarterlyDividend = Double.parseDouble(lineArray[3]);
			double baseRateOfReturn = Double.parseDouble(lineArray[4]);
			double omegaMeasure = Double.parseDouble(lineArray[5]);
			double totalValue = Double.parseDouble(lineArray[6]);
			PrivateInvestment investment = new PrivateInvestment(assetCode, type, label, quarterlyDividend, baseRateOfReturn, omegaMeasure, totalValue);
			return investment;
		}
		return null;
	}
	
	public static Portfolio parsePortfolio (String line) {
		//Split each line into the fields that will be used to instantiate objects
		String lineArray[] = line.split(";", 5);
		
		//Define all alphanumeric codes
		String portfolioCode = lineArray[0];
		String ownerCode = lineArray[1];
		String managerCode = lineArray[2];
		String beneficiaryCode = lineArray[3];
		
		Person owner = Search.findPersonByCode(ownerCode);
		Person manager = Search.findPersonByCode(managerCode);
		Person beneficiary = null;
		
		//Search for the beneficiary if a code exists to use to search
		if(beneficiaryCode.length() > 0) {
			beneficiary = Search.findPersonByCode(beneficiaryCode);
		}
		
		//Create a temporary array for the Asset
		ArrayList<Asset> asset = new ArrayList<Asset>();
		
		if(lineArray[4].length() > 0) {
			String tempAsset[] = lineArray[4].split(",");
			for(int i = 0; i < tempAsset.length; i++) {
				//Split each asset into assetCode and numerical value
				String temp[] = tempAsset[i].split(":", 2);
				String assetCode = temp[0];
				double value = Double.parseDouble(temp[1]);
				
				Asset found = Search.findAssetByCode(assetCode);
				
				if(found.getType().compareTo("D") == 0) {
					DepositAccount deposit = new DepositAccount((DepositAccount) found);
					deposit.setBalance(value);
					asset.add(deposit);
				} else if(found.getType().compareTo("P") == 0) {
					PrivateInvestment investment = new PrivateInvestment((PrivateInvestment) found);
					investment.setPercentage(value);
					asset.add(investment);
				} else if(found.getType().compareTo("S") == 0) {
					Stock stock = new Stock((Stock) found);
					stock.setSharesOwned(value);
					asset.add(stock);
				}
			}
		}
		Portfolio portfolio = new Portfolio(portfolioCode, owner, manager, beneficiary, asset);
		return portfolio;
	}
}

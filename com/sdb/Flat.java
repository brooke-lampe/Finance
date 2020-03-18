package com.sdb;
/**
 * Flat.java
 * Brooke Lampe
 * 2017/03/11
 * This class contains the functionality to load data from flat files
 */

import java.util.ArrayList;

public class Flat {
	
	public static void loadFlatFiles() {
		
		//Retrieve lines from flat files
		ArrayList<String> personLines = new ArrayList<String>();
		personLines = GetFileLines.getFileLines("data/Persons.dat");
		
		ArrayList<String> assetLines = new ArrayList<String>();
		assetLines = GetFileLines.getFileLines("data/Assets.dat");
		
		ArrayList<String> portfolioLines = new ArrayList<String>();
		portfolioLines = GetFileLines.getFileLines("data/Portfolios.dat");
		
		int m = Integer.parseInt(personLines.get(0));
		int n = Integer.parseInt(assetLines.get(0));
		int p = Integer.parseInt(portfolioLines.get(0));
		
		//Call a function that will tokenize by semicolon and place the information into an object
		for(int i = 1; i <= m; i++) {
			Data.getPersonList().add(Parse.parsePerson(personLines.get(i)));
		}
		
		for(int i = 1; i <= n; i++) {
			Data.getAssetList().add(Parse.parseAsset(assetLines.get(i)));
		}
		
		for(int i = 1; i <= p; i++) {
			Data.getPortfolioList().add(Parse.parsePortfolio(portfolioLines.get(i)));
		}
	}
}

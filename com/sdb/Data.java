package com.sdb;
/**
 * Data.java
 * Brooke Lampe
 * 2017/02/09
 * This class maintains all the data used by the system
 */

import java.util.ArrayList;
import java.util.Collections;

public class Data {
	
	private static ArrayList<Person> personList = new ArrayList<Person>();
	private static ArrayList<Asset> assetList = new ArrayList<Asset>();
	private static ArrayList<Portfolio> portfolioList = new ArrayList<Portfolio>();
	
	public static ArrayList<Person> getPersonList() {
		return personList;
	}
	
	public static ArrayList<Asset> getAssetList() {
		return assetList;
	}
	
	public static ArrayList<Portfolio> getPortfolioList() {
		return portfolioList;
	}
	
	public static void sortPortfolioByOwner (ArrayList<Portfolio> portfolio) {
		Collections.sort(portfolio, Portfolio.byOwner);
	}
	
	public static void sortPortfolioByManager (ArrayList<Portfolio> portfolio) {
		Collections.sort(portfolio, Portfolio.byManager);
	}
	
	public static void sortPortfolioByBeneficiary (ArrayList<Portfolio> portfolio) {
		Collections.sort(portfolio, Portfolio.byBeneficiary);
	}
}

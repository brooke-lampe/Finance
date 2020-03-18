package com.sdb;
/**
 * Portfolio.java
 * Brooke Lampe
 * 2017/02/09
 * This class provides support for the Portfolio object
 */

import java.util.ArrayList;
import java.util.Comparator;

public class Portfolio {

	private Integer portfolioId;
	private String portfolioCode;
	private Person owner;
	private Person manager;
	private Person beneficiary;
	private ArrayList<Asset> asset;
	
	public Portfolio(String portfolioCode, Person owner, Person manager, Person beneficiary, ArrayList<Asset> asset) {
		this.portfolioCode = portfolioCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.asset = asset;
	}
	
	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioCode() {
		return portfolioCode;
	}

	public Person getOwner() {
		try {
			return owner;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Person getManager() {
		try {
			return manager;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Person getBeneficiary() {
		try {
			return beneficiary;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public ArrayList<Asset> getAsset() {
		return asset;
	}
	
	public double getFees() {
		double fees = 0;
		String classification = null;
		
		try {
			classification = manager.getBroker().getClassification();
		} catch (NullPointerException e) {
			return 0;
		}
		
		if(classification.compareTo("E") == 0) {
			fees = 10 * asset.size();
		} else if(classification.compareTo("J") == 0) {
			fees = 50 * asset.size();
		}
		
		return fees;
	}
	
	public double getCommissions() {
		double commissions = 0;
		String classification;
		
		try {
			classification = manager.getBroker().getClassification();
		} catch (NullPointerException e) {
			return 0;
		}
		
		if(classification.compareTo("E") == 0) {
			commissions = 0.05 * getPortfolioAnnualReturn();
		} else if(classification.compareTo("J") == 0) {
			commissions = 0.02 * getPortfolioAnnualReturn();
		}
		
		return commissions;
	}
	
	public double getAggregateRiskMeasure() {
		double riskSum = 0, aggregateRiskMeasure;
		for(int i = 0; i < asset.size(); i++) {
			riskSum = riskSum + (asset.get(i).getRisk() * asset.get(i).getActualValue());
		}
		if(getPortfolioValue() == 0) {
			return 0;
		}
		aggregateRiskMeasure = riskSum / getPortfolioValue();
		return aggregateRiskMeasure;
	}
	
	public double getPortfolioValue() {
		double sum = 0;
		for(int i = 0; i < asset.size(); i++) {
			sum = sum + asset.get(i).getActualValue();
		}
		return sum;
	}
	
	public double getPortfolioAnnualReturn() {
		double sum = 0;
		for(int i = 0; i < asset.size(); i++) {
			sum = sum + asset.get(i).getAnnualReturn();
		}
		return sum;
	}
	
	//This comparator compares by owner name
	public static final Comparator<Portfolio> byOwner = new Comparator<Portfolio>() {
    	public int compare(Portfolio a, Portfolio b) {
    		return Person.comparePerson(a.owner, b.owner);
    	}
    };
    
    //This comparator compares by manager name
    public static final Comparator<Portfolio> byManager = new Comparator<Portfolio>() {
    	public int compare(Portfolio a, Portfolio b) {
    		return Person.comparePerson(a.manager, b.manager);
    	}
    };
    
    //This comparator compares by beneficiary name
    public static final Comparator<Portfolio> byBeneficiary = new Comparator<Portfolio>() {
    	public int compare(Portfolio a, Portfolio b) {
    		return Person.comparePerson(a.beneficiary, b.beneficiary);
    	}
    };
    
    //This comparator compares by broker type and then by broker name
    public static final Comparator<Portfolio> byBroker = new Comparator<Portfolio>() {
    	public int compare(Portfolio a, Portfolio b) {
    		return Person.compareBroker(a.manager, b.manager);
    	}
    };
    
    //This comparator compares by total value of the portfolio from lowest to highest
    public static final Comparator<Portfolio> byValueLowestToHighest = new Comparator<Portfolio>() {
    	public int compare(Portfolio a, Portfolio b) {
    		if(a.getPortfolioValue() > b.getPortfolioValue()) {
    			return 1;
    		} else if(a.getPortfolioValue() == b.getPortfolioValue()) {
    			return 0;
    		} else {
    			return -1;
    		}
    	}
    };
    
    //This comparator compares by total value of the portfolio from lowest to highest
    public static final Comparator<Portfolio> byValueHighestToLowest = new Comparator<Portfolio>() {
    	public int compare(Portfolio a, Portfolio b) {
    		if(a.getPortfolioValue() > b.getPortfolioValue()) {
    			return -1;
    		} else if(a.getPortfolioValue() == b.getPortfolioValue()) {
    			return 0;
    		} else {
    			return 1;
    		}
    	}
    };
}

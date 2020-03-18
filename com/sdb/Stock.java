package com.sdb;
/**
 * Stock.java
 * Brooke Lampe
 * 2017/02/02
 * This class provides support for the subclass Stock in the Asset superclass
 */

public class Stock extends Asset {
	
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double betaMeasure;
	private String stockSymbol;
	private double sharePrice;
	private double sharesOwned;
	
	public Stock(String assetCode, String type, String label, double quarterlyDividend,
	double baseRateOfReturn, double betaMeasure, String stockSymbol, double sharePrice) {
		this.assetCode = assetCode;
		this.type = type;
		this.label = label;
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.betaMeasure = betaMeasure;
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
	}

	public double getQuarterlyDividend() {
		return quarterlyDividend;
	}

	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}

	public double getBetaMeasure() {
		return betaMeasure;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public double getSharesOwned() {
		return sharesOwned;
	}
	
	@Override
	public double getRisk() {
		return betaMeasure;
	}
	
	@Override
	public double getActualValue() {
		return sharePrice * sharesOwned;
	}
	
	@Override
	public double getAnnualReturn() {
		double annualReturn;
		//Note:  baseRateOfReturn can be divided by 100 as needed to accomodate differences of scaling
		annualReturn = (getActualValue() * baseRateOfReturn) + (quarterlyDividend * sharesOwned * 4);
		return annualReturn;
	}

	public void setSharesOwned(double sharesOwned) {
		this.sharesOwned = sharesOwned;
	}
	
	//Copy constructor
	public Stock(Stock s) {
		this(s.assetCode, s.type, s.label, s.quarterlyDividend, s.baseRateOfReturn, s.betaMeasure, s.stockSymbol, s.sharePrice);
	}
}

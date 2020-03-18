package com.sdb;
/**
 * PrivateInvestment.java
 * Brooke Lampe
 * 2017/02/02
 * This class provides support for the subclass PrivateInvestment in the Asset superclass
 */

public class PrivateInvestment extends Asset {

	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double omegaMeasure;
	private double totalValue;
	private double percentage;
	
	public PrivateInvestment(String assetCode, String type, String label, double quarterlyDividend,
	double baseRateOfReturn, double omegaMeasure, double totalValue) {
		this.assetCode = assetCode;
		this.type = type;
		this.label = label;
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.omegaMeasure = omegaMeasure;
		this.totalValue = totalValue;
	}

	public double getQuarterlyDividend() {
		return quarterlyDividend;
	}

	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}

	public double getOmegaMeasure() {
		return omegaMeasure;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public double getPercentage() {
		return percentage;
	}
	
	@Override
	public double getRisk() {
		double offset, omega, v;
		v = -100000 / getTotalValue();
		offset = Math.pow(Math.E, v);
		omega = getOmegaMeasure() + offset;
		return omega;
	}
	
	@Override
	public double getActualValue() {
		//Note:  percentage can be divided by 100 as needed to accomodate differences of scaling
		return totalValue * percentage;
	}
	
	@Override
	public double getAnnualReturn() {
		double annualReturn;
		//Note:  percentage and baseRateOfReturn can be divided by 100 as needed to accomodate differences of scaling
		annualReturn = (getActualValue() * baseRateOfReturn) + (quarterlyDividend * percentage * 4);
		return annualReturn;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	//Copy constructor
	public PrivateInvestment(PrivateInvestment p) {
		this(p.assetCode, p.type, p.label, p.quarterlyDividend, p.baseRateOfReturn, p.omegaMeasure, p.totalValue);
	}
}

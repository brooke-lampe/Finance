package com.sdb;
/**
 * DepositAccount.java
 * Brooke Lampe
 * 2017/02/02
 * This class provides support for the subclass DepositAccount in the Asset superclass
 */

public class DepositAccount extends Asset {
	
	private double apr;
	private double balance;

	public DepositAccount(String assetCode, String type, String label, double apr) {
		this.assetCode = assetCode;
		this.type = type;
		this.label = label;
		this.apr = apr;
	}
	
	public double getApr() {
		return apr;
	}
	
	public double getBalance() {
		return balance;
	}
	
	@Override
	public double getRisk() {
		return 0;
	}
	
	@Override
	public double getActualValue() {
		return balance;
	}
	
	@Override
	public double getAnnualReturn() {
		double APR, APY, annualReturn;
		//Note:  apr can be divided by 100 as needed to accomodate differences of scaling
		APR = apr;
		APY = Math.pow(Math.E, APR) - 1;
		annualReturn = balance * APY;
		return annualReturn;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	//Copy constructor
	public DepositAccount(DepositAccount d) {
		this(d.assetCode, d.type, d.label, d.apr);
	}
}

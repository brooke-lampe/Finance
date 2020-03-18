package com.sdb;
/**
 * Asset.java
 * Brooke Lampe
 * 2017/01/29
 * This superclass provides support for the Asset object and its subclasses
 */

public class Asset {
	
	protected Integer assetId;
	protected Integer equityId;
	protected String assetCode;
	protected String type;
	protected String label;
	
	public Asset() {
		super();
	}

	public Asset(String assetCode, String type, String label) {
		this.assetCode = assetCode;
		this.type = type;
		this.label = label;
	}
	
	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public Integer getEquityId() {
		return equityId;
	}

	public void setEquityId(Integer equityId) {
		this.equityId = equityId;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public String getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
	
	public double getRisk() {
		return 0;
	}
	
	public double getActualValue() {
		return 0;
	}
	
	public double getAnnualReturn() {
		return 0;
	}
	
	public double getReturnRate() {
		if(getActualValue() == 0) {
			return 0;
		}
		return (getAnnualReturn() / getActualValue()) * 100;
	}
}

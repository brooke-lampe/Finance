package com.sdb;
/**
 * AssetToXML.java
 * Brooke Lampe
 * 2017/02/04
 * This class will print an Asset object to an .xml file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AssetToXML {

	public static void AssetToXML(ArrayList<Asset> assetList){

		//This method creates a .xml file for the Asset object
		File y = new File("data/Assets.xml");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(y);
			
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		pw.println("<Asset>");
		for(int i = 0; i < assetList.size(); i++) {
			if(assetList.get(i).getType().compareTo("D") == 0) {
				DepositAccount deposit = (DepositAccount) assetList.get(i);
				pw.println("\t" + "<asset>");
				pw.println("\t" + "\t" + "<code>" + deposit.getAssetCode() + "</code>");
				pw.println("\t" + "\t" + "<label>" + deposit.getLabel() + "</label>");
				pw.println("\t" + "\t" + "<type>" + deposit.getType() + "</type>");
				pw.println("\t" + "\t" + "<apr>" + deposit.getApr() + "</apr>");
				pw.println("\t" + "</asset>");
			}
			
			if(assetList.get(i).getType().compareTo("S") == 0) {
				Stock stock = (Stock) assetList.get(i);
				pw.println("\t" + "<asset>");
				pw.println("\t" + "\t" + "<code>" + stock.getAssetCode() + "</code>");
				pw.println("\t" + "\t" + "<label>" + stock.getLabel() + "</label>");
				pw.println("\t" + "\t" + "<type>" + stock.getType() + "</type>");
				pw.println("\t" + "\t" + "<baseRateOfReturn>" + stock.getBaseRateOfReturn() + "</baseRateOfReturn>");
				pw.println("\t" + "\t" + "<quarterlyDividend>" + stock.getQuarterlyDividend() + "</quarterlyDividend>");
				pw.println("\t" + "\t" + "<sharePrice>" + stock.getSharePrice() + "</sharePrice>");
				pw.println("\t" + "\t" + "<symbol>" + stock.getStockSymbol() + "</symbol>");
				pw.println("\t" + "\t" + "<beta>" + stock.getBetaMeasure() + "</beta>");
				pw.println("\t" + "</asset>");
			}
			
			if(assetList.get(i).getType().compareTo("P") == 0) {
				PrivateInvestment investment = (PrivateInvestment) assetList.get(i);
				pw.println("\t" + "<asset>");
				pw.println("\t" + "\t" + "<code>" + investment.getAssetCode() + "</code>");
				pw.println("\t" + "\t" + "<label>" + investment.getLabel() + "</label>");
				pw.println("\t" + "\t" + "<type>" + investment.getType() + "</type>");
				pw.println("\t" + "\t" + "<baseRateOfReturn>" + investment.getBaseRateOfReturn() + "</baseRateOfReturn>");
				pw.println("\t" + "\t" + "<quarterlyDividend>" + investment.getQuarterlyDividend() + "</quarterlyDividend>");
				pw.println("\t" + "\t" + "<omega>" + investment.getOmegaMeasure() + "</omega>");
				pw.println("\t" + "\t" + "<value>" + investment.getTotalValue() + "</value>");
				pw.println("\t" + "</asset>");
			}
			
		}
		pw.println("</Asset>");
		pw.close();
	}
}
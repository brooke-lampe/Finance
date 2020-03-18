package com.sdb;
/**
 * AssetToJSON.java
 * Brooke Lampe
 * 2017/02/04
 * This class will print an Asset object to a .json file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AssetToJSON {

	public static void AssetToJSON(ArrayList<Asset> assetList){

		//This method creates a .json file for the Asset object
		File y = new File("data/Assets.json");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(y);
			
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		pw.println("{");
		pw.println("\"Asset\": [");
		for(int i = 0; i < assetList.size(); i++) {
			if(assetList.get(i).getType().compareTo("D") == 0) {
				DepositAccount deposit = (DepositAccount) assetList.get(i);
				pw.println("\t" + "{");
				pw.println("\t" + "\t" + "\"code\": \"" + deposit.getAssetCode() + "\",");
				pw.println("\t" + "\t" + "\"label\": \"" + deposit.getLabel() + "\",");
				pw.println("\t" + "\t" + "\"type\": \"" + deposit.getType() + "\",");
				pw.println("\t" + "\t" + "\"apr\": \"" + deposit.getApr() + "\"");
				if(i < assetList.size() - 1) {
					pw.println("\t" + "},");
				} else {
					pw.println("\t" + "}");
				}
			}
			
			if(assetList.get(i).getType().compareTo("S") == 0) {
				Stock stock = (Stock) assetList.get(i);
				pw.println("\t" + "{");
				pw.println("\t" + "\t" + "\"code\": \"" + stock.getAssetCode() + "\",");
				pw.println("\t" + "\t" + "\"label\": \"" + stock.getLabel() + "\",");
				pw.println("\t" + "\t" + "\"type\": \"" + stock.getType() + "\",");
				pw.println("\t" + "\t" + "\"baseRateOfReturn\": \"" + stock.getBaseRateOfReturn() + "\",");
				pw.println("\t" + "\t" + "\"quarterlyDividend\": \"" + stock.getQuarterlyDividend() + "\",");
				pw.println("\t" + "\t" + "\"sharePrice\": \"" + stock.getSharePrice() + "\",");
				pw.println("\t" + "\t" + "\"symbol\": \"" + stock.getStockSymbol() + "\",");
				pw.println("\t" + "\t" + "\"beta\": \"" + stock.getBetaMeasure() + "\"");
				if(i < assetList.size() - 1) {
					pw.println("\t" + "},");
				} else {
					pw.println("\t" + "}");
				}
			}
			
			if(assetList.get(i).getType().compareTo("P") == 0) {
				PrivateInvestment investment = (PrivateInvestment) assetList.get(i);
				pw.println("\t" + "{");
				pw.println("\t" + "\t" + "\"code\": \"" + investment.getAssetCode() + "\",");
				pw.println("\t" + "\t" + "\"label\": \"" + investment.getLabel() + "\",");
				pw.println("\t" + "\t" + "\"type\": \"" + investment.getType() + "\",");
				pw.println("\t" + "\t" + "\"baseRateOfReturn\": \"" + investment.getBaseRateOfReturn() + "\",");
				pw.println("\t" + "\t" + "\"quarterlyDividend\": \"" + investment.getQuarterlyDividend() + "\",");
				pw.println("\t" + "\t" + "\"omega\": \"" + investment.getOmegaMeasure() + "\",");
				pw.println("\t" + "\t" + "\"value\": \"" + investment.getTotalValue() + "\"");
				if(i < assetList.size() - 1) {
					pw.println("\t" + "},");
				} else {
					pw.println("\t" + "}");
				}
			}
		}
		pw.println("]}");
		pw.close();
	}
}

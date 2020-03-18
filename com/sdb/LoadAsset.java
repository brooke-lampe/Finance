package com.sdb;
/**
 * LoadAsset.java
 * Brooke Lampe
 * 2017/03/08
 * This class will load asset data from the database into Asset objects
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadAsset {

	public static void getAsset() {
		
		Connection conn = Connect.Connect();
		
		String selectAssetData = "SELECT assetId, assetCode, assetType, label, apr, quarterlyDividend, baseRateOfReturn, "
		+ "betaMeasure, stockSymbol, sharePrice, omegaMeasure, totalValue FROM asset";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(selectAssetData);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer assetId = rs.getInt("assetId");
				String assetCode = rs.getString("assetCode");
				String type = rs.getString("assetType");
				String label = rs.getString("label");
				Double apr = rs.getDouble("apr");
				Double quarterlyDividend = rs.getDouble("quarterlyDividend");
				Double baseRateOfReturn = rs.getDouble("baseRateOfReturn");
				Double betaMeasure = rs.getDouble("betaMeasure");
				String stockSymbol = rs.getString("stockSymbol");
				Double sharePrice = rs.getDouble("sharePrice");
				Double omegaMeasure =rs.getDouble("omegaMeasure");
				Double totalValue = rs.getDouble("totalValue");
				
				if(type.compareTo("D") == 0) {
					DepositAccount deposit = new DepositAccount(assetCode, type, label, apr);
					deposit.setAssetId(assetId);
					Data.getAssetList().add(deposit);
				}

				if(type.compareTo("S") == 0) {
					Stock stock = new Stock(assetCode, type, label, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
					stock.setAssetId(assetId);
					Data.getAssetList().add(stock);
				}

				if(type.compareTo("P") == 0) {
					PrivateInvestment investment = new PrivateInvestment(assetCode, type, label, quarterlyDividend, baseRateOfReturn, omegaMeasure, totalValue);
					investment.setAssetId(assetId);
					Data.getAssetList().add(investment);
				}
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
	}
}
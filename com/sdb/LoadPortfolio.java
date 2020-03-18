package com.sdb;
/**
 * LoadPortfolio.java
 * Brooke Lampe
 * 2017/03/08
 * This class will load asset data from the database into Asset objects
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadPortfolio {

	public static void getPortfolio() {
		
		Connection conn = Connect.Connect();
		
		String selectPortfolioData = "SELECT portfolioId, portfolioCode, ownerId, managerId, beneficiaryId FROM portfolio";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(selectPortfolioData);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer portfolioId = rs.getInt("portfolioId");
				String portfolioCode = rs.getString("portfolioCode");
				Integer ownerId = rs.getInt("ownerId");
				Integer managerId = rs.getInt("managerId");
				Integer beneficiaryId = rs.getInt("beneficiaryId");
				
				Person owner = null;
				Person manager = null;
				Person beneficiary = null;
				
				if(ownerId != null && ownerId != 0) {
					owner = Search.findPersonById(ownerId);
				}
				
				if(managerId != null && managerId != 0) {
					manager = Search.findPersonById(managerId);
				}
				
				if(beneficiaryId != null && beneficiaryId != 0) {
					beneficiary = Search.findPersonById(beneficiaryId);
				}
				
				ArrayList<Asset> asset = Search.findEquityById(portfolioId);
				
				Portfolio portfolio = new Portfolio(portfolioCode, owner, manager, beneficiary, asset);
				portfolio.setPortfolioId(portfolioId);
				Data.getPortfolioList().add(portfolio);
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		Connect.Disconnect(conn, ps, rs);
	}
}
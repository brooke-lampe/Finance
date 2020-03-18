package com.sdb;
/**
 * Data.java
 * Brooke Lampe
 * 2017/03/07
 * This class is used to connect to the database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
	
	public static Connection Connect() {

		//Connect to the database
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			Log.logger.error("InstantiationException: ", e1);
			throw new RuntimeException();
		} catch (IllegalAccessException e2) {
			Log.logger.error("IllegalAccessException: ", e2);
			throw new RuntimeException();
		} catch (ClassNotFoundException e3) {
			Log.logger.error("ClassNotFoundException: ", e3);
			throw new RuntimeException();
		}
		
		String url = "jdbc:mysql://cse.unl.edu/blampe";
		String username = "blampe";
		String password = "Qw7AGy";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
		
		return conn;
	}
	
	public static void Disconnect(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	}
	
	public static void Disconnect(Connection conn, PreparedStatement ps) {
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			Log.logger.error("SQLException: ", sqle);
			throw new RuntimeException();
		}
	}
}
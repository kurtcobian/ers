package com.revature.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	private static String url = "ASk me for URL";
	private static String user = "reimburse";
	private static String pass = "p4ssw0rd"; //figure a way out of this
	
	public static Connection getConnection(){
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url,user,pass);
			conn.setAutoCommit(false);
			return conn;
		} catch (Exception e){
			throw new IllegalStateException("Something went wrong while getting connected.");
		}
	}
}

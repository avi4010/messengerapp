/**
 * 
 */
package com.restapiyoutube.www.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author avi4010
 *
 */
public class DBConnection {
	
	private static final String DB_URL = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER = "jdbc:mysql://messengerMySQLDB:3306/messengerdb";
    private static final String DB_USER = "root";
    private static final String DB_PASWORD = "root";
    private static Connection con = null;
    
	public static Connection getConnection() {
			
			try {
				Class.forName(DB_URL).newInstance();
				con = DriverManager.getConnection(DRIVER, DB_USER, DB_PASWORD);
				boolean reachable = con.isValid(10);
				System.out.println("Status of the connection :"+reachable);
			} catch (Exception e) {
				System.out.println("Failed..."+e);
			}
			return con;
	}
	
	public static void main(String[] args) {
		con = DBConnection.getConnection();
		if(con==null)
			System.out.println("Connection is null");
		else
			System.out.println("Connection is not null");
	}

}

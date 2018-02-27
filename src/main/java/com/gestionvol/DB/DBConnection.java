package com.gestionvol.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;


public class DBConnection {

	private final static String database_name = "test";
    private final static String databaseURL = "jdbc:mysql://localhost:3306/" ;
    private final static String user = "root";
    private final static String password = "";
	
    private static Connection connect = null;
    
    
	public static Connection setConnection() {
		try{
			
			
			
            Class.forName("com.mysql.jdbc.Driver");
            
            connect = (Connection) DriverManager.getConnection(databaseURL+database_name, user, password);
            return connect;
           
        }catch (ClassNotFoundException e) {
  		  System.out.println("error: failed to load MySQL driver.");
  		  e.printStackTrace();
  		} catch (SQLException e) {
  		  System.out.println("error: failed to create a connection object.");
  		  e.printStackTrace();
  		} catch (Exception e) {
  		  System.out.println("other error:");
  		  e.printStackTrace();
  		}
		return null;
	
	}


	
	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return connect;
	}


	
	public static  void closeConnection() {
		// TODO Auto-generated method stub
		
			try {
				if(connect != null)
					connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}

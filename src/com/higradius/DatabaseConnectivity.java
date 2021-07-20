package com.higradius;
import java.sql.*;

public class DatabaseConnectivity {
	
	protected static Connection initializeDatabase() throws SQLException,ClassNotFoundException{  
		 
		String dbUsername="root";
		String dbPassword="abuzaid";
		String dbURL="jdbc:mysql://localhost:3306/invoice";
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection( dbURL,dbUsername,dbPassword);  
		return con;
		
}
}

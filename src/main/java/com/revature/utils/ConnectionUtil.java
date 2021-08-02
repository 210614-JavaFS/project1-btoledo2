package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		//For many frameworks using JDBC it is necessary to "register" the driver 
		//in order for the framework to be aware of it. 
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://bankdatabase.cnl6gksjpkku.us-east-2.rds.amazonaws.com:5432/project0";
		String username = "postgres"; //It is possible to use env variables to hide this information
		String password = "password"; // you would access them with System.getenv("var-name");
		
		return DriverManager.getConnection(url, username, password);
	}
	// encryption
//	public static void main(String[] args) {
//		String password ="password";
//		int workload = 12;
//		String salt = BCrypt.gensalt(workload);
//		String encryptpw =	BCrypt.hashpw(password, salt);
//		System.out.println(encryptpw.length());
//		System.out.println(encryptpw);
//		boolean passwordMatch = false;
//		passwordMatch = BCrypt.checkpw(password, "$2a$12$OHgww9U9x8m5Jxly.kRkieEnjC4LzHDZqzu.CzQ/dXpnDDsiExzSq");
//		if(passwordMatch) {
//			System.out.println("True");
//		}else {System.out.println("False");}
//		
//	}

}

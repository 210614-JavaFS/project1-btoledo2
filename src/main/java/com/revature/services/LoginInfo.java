package com.revature.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.models.Users;

public class LoginInfo {
	
	private static UserService userService = new UserService();
	
	public boolean userLogin(String inputUsername, String password) {
		List<Users> users = userService.allUsers();
		String tempUserName = null;
		String tempPW = null;
		for(Users e: users) {
			tempUserName = e.getUserName();
			tempPW = e.getPassword();
			if(inputUsername.equals(tempUserName) && password.equals(tempPW)) {
				return true;
			}
		}
		
		
		/*
		 * int workload = 12; String salt = BCrypt.gensalt(workload); boolean
		 * passwordMatch = false;
		 */
		//passwordMatch = BCrypt.checkpw(password,  );
		return false;	
	}
	
	
	
	
}
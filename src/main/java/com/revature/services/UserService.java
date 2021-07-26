package com.revature.services;

import java.util.List;

import com.revature.models.Users;
import com.revature.repos.UsersDAO;
import com.revature.repos.UsersDAOImpl;

public class UserService {
	private static UsersDAO usersDAO = new UsersDAOImpl(); 

	public List<Users> allUsers(){
		return usersDAO.findAll();
	}
}

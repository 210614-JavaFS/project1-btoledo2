package com.revature.services;

import com.revature.models.UserRole;
import com.revature.repos.UserRoleDAO;
import com.revature.repos.UserRoleDAOImpl;

public class UserRoleService {
	private static UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
	public UserRole getRole(int id) {
		return userRoleDAO.findRole(id);
	}
	
}

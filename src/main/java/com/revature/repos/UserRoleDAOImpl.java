package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserRoleDAOImpl implements UserRoleDAO {

	@Override
	public UserRole findRole(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From ERS_USERS_ROLE  WHERE ERS_USER_ROLE_ID = ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			UserRole userRole = new UserRole();
			while(result.next()) {
				userRole.setRoleID(result.getInt("ERS_USER_ROLE_ID"));
				userRole.setRole(result.getString("USER_ROLE"));
			}
			return userRole;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

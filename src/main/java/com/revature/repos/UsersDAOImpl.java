package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.UserRole;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class UsersDAOImpl implements UsersDAO {
	
	private static UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

	@Override
	public List<Users> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ERS_USERS";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Users> list = new ArrayList<>();
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				Users users = new Users();
				users.setUserId(result.getInt("ERS_USERS_ID"));
				users.setUserName(result.getString("ERS_USERNAME"));
				users.setPassword(result.getString("ERS_PASSWORD"));
				users.setFirstName(result.getString("USER_FIRST_NAME"));
				users.setLastName(result.getString("USER_LAST_NAME"));
				users.setEmailString(result.getString("USER_EMAIL"));
				int roleId = result.getInt("USER_ROLE_ID");
				if(roleId != 0){
					UserRole userRole = userRoleDAO.findRole(roleId);
					users.setUserRole(userRole);
				}
				list.add(users);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Users findUser(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From ERS_USERS  WHERE ERS_USERS_ID = ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Users users = new Users();
			while(result.next()) {
				users.setUserId(result.getInt("ERS_USERS_ID"));
				users.setUserName(result.getString("ERS_USERNAME"));
				users.setPassword(result.getString("ERS_PASSWORD"));
				users.setFirstName(result.getString("USER_FIRST_NAME"));
				users.setLastName(result.getString("USER_LAST_NAME"));
				users.setEmailString(result.getString("USER_EMAIL"));
				int roleId = result.getInt("USER_ROLE_ID");
				if(roleId != 0){
					UserRole userRole = userRoleDAO.findRole(roleId);
					users.setUserRole(userRole);
				}
				
			}
			return users;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Users findUserByUsername(String userName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From ERS_USERS  WHERE ERS_USERNAME = ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setString(1, userName);
			ResultSet result = statement.executeQuery();
			Users users = new Users();
			while(result.next()) {
				users.setUserId(result.getInt("ERS_USERS_ID"));
				users.setUserName(result.getString("ERS_USERNAME"));
				users.setPassword(result.getString("ERS_PASSWORD"));
				users.setFirstName(result.getString("USER_FIRST_NAME"));
				users.setLastName(result.getString("USER_LAST_NAME"));
				users.setEmailString(result.getString("USER_EMAIL"));
				int roleId = result.getInt("USER_ROLE_ID");
				if(roleId != 0){
					UserRole userRole = userRoleDAO.findRole(roleId);
					users.setUserRole(userRole);
				}
				
			}
			return users;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package com.revature.repos;

import java.util.List;

import com.revature.models.Users;

public interface UsersDAO {
	public List<Users> findAll();
	public Users findUser(int id);
	public Users findUserByUsername(String userName);
}

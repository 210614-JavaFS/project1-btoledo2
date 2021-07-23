package com.revature.models;

public class UserRole {
	private int roleID;
	private String role;
	
		
	public UserRole() {
		super();
	}

	public UserRole(int roleID, String role) {
		super();
		this.roleID = roleID;
		this.role = role;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleID != other.roleID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [RoleID=" + roleID + ", Role=" + role + "]";
	}
	
}

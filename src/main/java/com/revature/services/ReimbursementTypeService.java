package com.revature.services;

import com.revature.models.ReimbursementType;
import com.revature.repos.ReimbursementTypeDAO;
import com.revature.repos.ReimbursementTypeDAOImpl;

public class ReimbursementTypeService {
	private static ReimbursementTypeDAO typeDAO = new ReimbursementTypeDAOImpl();

	public ReimbursementType getType(int id) {
		return typeDAO.findType(id);
	}
}

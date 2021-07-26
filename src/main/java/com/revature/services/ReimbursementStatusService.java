package com.revature.services;

import com.revature.models.ReimbursementStatus;
import com.revature.repos.ReimbursementStatusDAO;
import com.revature.repos.ReimbursementStatusDAOImpl;

public class ReimbursementStatusService {
	public static ReimbursementStatusDAO statusDAO = new ReimbursementStatusDAOImpl();
	
	public ReimbursementStatus getStatus(int id) {
		return statusDAO.findStatus(id);
	}
}

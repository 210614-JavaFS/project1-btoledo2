package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;

public class ReimbursementService {
	private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
	
	public List<Reimbursement> getAll(){
		return reimbursementDAO.findAll();
	}
	
	public boolean addReimbursement(Reimbursement reim) {
		return reimbursementDAO.createReimbursement(reim);
	}
	
	public boolean updateStatus(int reimId, int resolverId, int newStatus) {
		return reimbursementDAO.updateReimbursement(reimId, resolverId, newStatus);
	}
	
}

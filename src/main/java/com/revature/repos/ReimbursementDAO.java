package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	public List<Reimbursement> findAll();
	public Reimbursement findById(int id);
	public boolean createReimbursement(Reimbursement reim);
	public boolean updateReimbursement(Reimbursement reim);
	
	
	
}

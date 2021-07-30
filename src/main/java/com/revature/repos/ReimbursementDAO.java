package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Users;

public interface ReimbursementDAO {
	public List<Reimbursement> findAll();
	
	public List<Reimbursement> findby(Users user);
	
	public List<Reimbursement> findById(int id);
	public boolean createReimbursement(Reimbursement reim);
	public boolean updateReimbursement(int reimId, int resolverId, int newStatus );
	
	
	
}

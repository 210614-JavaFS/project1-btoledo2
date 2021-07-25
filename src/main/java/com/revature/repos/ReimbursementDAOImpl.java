package com.revature.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	
	private static UsersDAO usersDAO = new UsersDAOImpl();
	private static ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
	private static ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();
	

	@Override
	public List<Reimbursement> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENT";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Reimbursement> list = new ArrayList<>();
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement(); 
				reimbursement.setReimbId(result.getInt("REIMB_ID"));
				reimbursement.setAmount(result.getDouble("REIMB_AMOUNT"));
				reimbursement.setSubmitted(result.getTimestamp("REIM_SUBMITTED"));
				reimbursement.setResolved(result.getTimestamp("REIMB_RESOLVED"));
				reimbursement.setDescription(result.getString("REIMB_DESCRIPTION"));
				int author = result.getInt("REIMB_AUTHOR");
				String temp = result.getString("REIMB_RESOLVER");
				int status = result.getInt("REIMB_STATUS_ID");
				int type = result.getInt("REIMB_TYPE_ID");
				if(author != 0) {
					Users users = usersDAO.findUser(author);
					reimbursement.setAuthor(users);
				}
				if(status != 0) {
					ReimbursementStatus reimbursementStatus = reimbursementStatusDAO.findStatus(status);
					reimbursement.setStatus(reimbursementStatus);
				}
				if(type != 0) {
					ReimbursementType reimbursementType = reimbursementTypeDAO.findType(type);
					reimbursement.setType(reimbursementType);
				}
				if(temp == null) {
					reimbursement.setResolver(null);
				}else {
					int resolver = Integer.parseInt(temp);
					Users users2 = usersDAO.findUser(resolver);
					reimbursement.setAuthor(users2);
					
				}	
				list.add(reimbursement);
			}			
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createReimbursement(Reimbursement reim) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reim) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO {

	@Override
	public ReimbursementStatus findStatus(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From ERS_REIMBURSEMENT_STATUS  WHERE REIMB_STATUS_ID = ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			ReimbursementStatus status = new ReimbursementStatus();
			while(result.next()) {
				status.setStatusID(result.getInt("REIMB_STATUS_ID"));
				status.setStatus(result.getString("REIMB_STATUS"));
			}
			return status;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

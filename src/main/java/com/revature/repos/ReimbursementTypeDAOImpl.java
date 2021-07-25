package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO {

	@Override
	public ReimbursementType findType(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			ReimbursementType type = new ReimbursementType();
			while(result.next()) {
				type.setTypeId(result.getInt("REIMB_TYPE_ID"));
				type.setType(result.getString("REIM_TYPE"));		
			}
			return type;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

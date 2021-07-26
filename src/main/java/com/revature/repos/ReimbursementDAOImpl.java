package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From ERS_REIMBURSEMENT WHERE REIMB_ID= ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Reimbursement reimbursement = new Reimbursement(); 
			while(result.next()) {
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
			
			
			}
			return reimbursement;	
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createReimbursement(Reimbursement reim) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO  ERS_REIMBURSEMENT(Reimb_amount,REIMB_SUBMITTED,REIMB_DESCRIPTION,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID)"
					+"Values(?,CURRENT_TIMESTAMP,?,?,?,?)";
	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, reim.getAmount());
			statement.setString(2, reim.getDescription());
			statement.setInt(3, reim.getAuthor().getUserId());
			statement.setInt(4, 1);
			statement.setInt(5, reim.getType().getTypeId());
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public boolean updateReimbursement(int reimId, int resolverId, int newStatus) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE ERS_REIMBURSEMENT\r\n"
					+ "SET  REIMB_RESOLVER = ? , REIMB_RESOLVED = CURRENT_TIMESTAMP , REIMB_STATUS_ID = ?" +
					"WHERE REIMB_STATUS_ID = 1 AND REIMB_ID = ? ";
	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, resolverId);
			statement.setInt(2, newStatus);
			statement.setInt(3, reimId);

			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}



}

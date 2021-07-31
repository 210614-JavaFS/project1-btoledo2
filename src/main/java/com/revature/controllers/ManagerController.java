package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ManagerController {
	
	private static ReimbursementService reimbService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void getTicketsById(HttpServletResponse response, String statusID) throws IOException, ServletException{
		int statusId = Integer.parseInt(statusID);
		//System.out.println(userId);
		List<Reimbursement> reimTickets = reimbService.findByStatus(statusId);
		
		String json = objectMapper.writeValueAsString(reimTickets);
		System.out.println(json);
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
		
	}
	public void getApproveOrDeny(HttpServletResponse response, String statusID, String ticket, String resolver) throws IOException, ServletException{
		int statusId = Integer.parseInt(statusID);
		int ticketId = Integer.parseInt(ticket);
		int userId = Integer.parseInt(resolver);
		
		//System.out.println(userId);
		if(reimbService.updateStatus(ticketId, userId, statusId)) {
			response.setStatus(201);
		}else {
			response.setStatus(406);
			System.out.println("something went wrong");
		}	
			
		
	}
	

}
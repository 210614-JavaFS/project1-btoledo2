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

public class EmployeeController {

	private static ReimbursementService reimbService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public boolean newTicket(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		System.out.println("In Ticket");
		System.out.println(line);
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Reimbursement reim = objectMapper.readValue(body, Reimbursement.class);
		
		if(reimbService.addReimbursement(reim)) {
			response.setStatus(201);
			System.out.println("New ticket");
		}else {
			response.setStatus(406);
			System.out.println("something went wrong");
		}	
		return false;
		
	}
	
	public void getTicketsById(HttpServletResponse response, String employeeID) throws IOException, ServletException{
		int userId = Integer.parseInt(employeeID);
		//System.out.println(userId);
		List<Reimbursement> reimTickets = reimbService.findTickets(userId);
		
		String json = objectMapper.writeValueAsString(reimTickets);
		System.out.println(json);
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
		
	}
	
	
	
}

package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.LoginController;
import com.revature.models.Users;
import com.revature.services.UserService;


public class FrontControllerServlet extends HttpServlet {
	
	private LoginController loginController = new LoginController();
	private EmployeeController employeeController = new EmployeeController();
	private ObjectMapper objectMapper = new ObjectMapper();
	private static UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//System.out.println("Request URI: " + request.getRequestURI() + ". Method: " + request.getMethod());
		response.setContentType("application/json");

		response.setStatus(404); // Overrides Tomcat's 200 default for malformed requests.

		final String URL = request.getRequestURI().replace("/project1/", ""); // strips out base URL info.
		final String employeeURL = request.getRequestURI().replace("/project1/employee/", "");
		System.out.println(employeeURL);
		String[] UrlSections = URL.split("/");
		String[] SectionsEmployee = employeeURL.split("/");
		//System.out.println(UrlSections[0]);
		//System.out.println(SectionsEmployee[0]);
		switch (UrlSections[0]) {	
		case "userLogin":
			//System.out.println("Here");
			//System.out.println(request.getMethod());
			if (request.getMethod().equals("POST")) {
				//System.out.println("Here");
				if (loginController.validatLogin(request, response)) {
//					response.setStatus(201);
					System.out.println("Check Final set status: " + response.getStatus());
					System.out.println("User Servlet Login is successful");
									
				} else {
					System.out.println("User Login is NOT successful");
				}
				//System.out.println("Here");
			}
			break;
		}
		//System.out.println(SectionsEmployee[0]);
		switch(SectionsEmployee[0]) {
		case "check": 
			//System.out.println("Employee Check");
			response.setStatus(201);
			String userName = session.getAttribute("userName").toString();
			String userID = session.getAttribute("userID").toString();
			String userPassword = session.getAttribute("password").toString();
			Users foundUser =  userService.findUser(userName);
			String json = objectMapper.writeValueAsString(foundUser);
			response.getWriter().print(json);
		
		break;
		case "createForm":
			System.out.println(SectionsEmployee[0]);
			if (request.getMethod().equals("POST")) {
				System.out.println("Here");
				String userID1 = session.getAttribute("userID").toString();
				System.out.println(userID1);
				if (employeeController.newTicket(request, response)) {
//					response.setStatus(201);
					System.out.println("Check Final set status: " + response.getStatus());
					System.out.println("User Servlet Login is successful");
									
				} 
				//System.out.println("Here");
			}
			
			break;
		case "viewForm":
			
			if(SectionsEmployee.length == 2) {
				System.out.println("Should be here");
				if(request.getMethod().equals("GET")) {
					employeeController.getTicketsById(response, SectionsEmployee[1].toLowerCase());
					System.out.println("Sending Tickets to employee page");
				}
				
			}
			
			
			break;
		
		}
	
	
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}

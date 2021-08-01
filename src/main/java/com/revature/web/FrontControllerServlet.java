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
import com.revature.controllers.ManagerController;
import com.revature.models.Users;
import com.revature.services.UserService;

public class FrontControllerServlet extends HttpServlet {

	private LoginController loginController = new LoginController();
	private EmployeeController employeeController = new EmployeeController();
	private ManagerController managerController = new ManagerController();
	private ObjectMapper objectMapper = new ObjectMapper();
	private static UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// System.out.println("Request URI: " + request.getRequestURI() + ". Method: " +
		// request.getMethod());
		response.setContentType("application/json");

		response.setStatus(404); // Overrides Tomcat's 200 default for malformed requests.

		final String URL = request.getRequestURI().replace("/project1/", ""); // strips out base URL info.
		final String employeeURL = request.getRequestURI().replace("/project1/employee/", "");
		final String managerURL = request.getRequestURI().replace("/project1/manager/", "");
		System.out.println(managerURL);
		String[] UrlSections = URL.split("/");
		String[] SectionsEmployee = employeeURL.split("/");
		String[] SectionsManager = managerURL.split("/");
		switch (UrlSections[0]) {
		case "userLogin":
			if (request.getMethod().equals("POST")) {
				if (loginController.validatLogin(request, response)) {
					//log.info("Login Seccessful");
					System.out.println("Check Final set status: " + response.getStatus());
					System.out.println("User Servlet Login is successful");

				} else {
					System.out.println("User Login is NOT successful");
				}
				// System.out.println("Here");
			}
			break;
		}
			switch (SectionsEmployee[0]) {
			case "check":
				
				System.out.println("Employee Check");
				String userName = session.getAttribute("userName").toString();
				Users foundUser = userService.findUser(userName);
				String json = objectMapper.writeValueAsString(foundUser);
				response.getWriter().print(json);
				response.setStatus(201);
				break;
			case "createForm":
				System.out.println(SectionsEmployee[0]);
				if (request.getMethod().equals("POST")) {
					System.out.println("Here");
					String userID1 = session.getAttribute("userID").toString();
					System.out.println(userID1);
					if (employeeController.newTicket(request, response)) {

					}
				}

				break;
			case "viewForm":

				if (SectionsEmployee.length == 2) {
					System.out.println("Should be here");
					if (request.getMethod().equals("GET")) {
						//log.info("User look at their forms");
						employeeController.getTicketsById(response, SectionsEmployee[1].toLowerCase());
						System.out.println("Sending Tickets to employee page");
					}

				}
				break;
			case "logout":
				session.invalidate();
				break;
			}
			
			switch(SectionsManager[0]) {
			case "managerCheck":
				System.out.println("In manager Page");
				String userName = session.getAttribute("userName").toString();
				Users foundUser = userService.findUser(userName);
				String json = objectMapper.writeValueAsString(foundUser);
				response.getWriter().print(json);
				response.setStatus(201);
				break;
			case "viewStatus":
				if (SectionsManager.length == 2) {
					System.out.println("Should be here");
					if (request.getMethod().equals("GET")) {
						//log.info("Manager is Looking a tickets by Status");
						managerController.getTicketsById(response, SectionsManager[1]);
						System.out.println("Sending Tickets to employee page");
					}
				}
				break;
			case "newStatus":
				if (SectionsManager.length == 4) {
					System.out.println("Should be here");
					if (request.getMethod().equals("PUT")) {
						//log.info("Manager is Looking a tickets by Status");
						managerController.getApproveOrDeny(response, SectionsManager[1], SectionsManager[2], SectionsManager[3]);
						System.out.println("Ticket Approve or Deny");
					}
				}			
			}
		}
		
	//}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
}

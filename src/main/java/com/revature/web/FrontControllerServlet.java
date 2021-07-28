package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.LoginController;


public class FrontControllerServlet extends HttpServlet {
	
	private LoginController loginController = new LoginController();
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//System.out.println("Request URI: " + request.getRequestURI() + ". Method: " + request.getMethod());
		response.setContentType("application/json");

		response.setStatus(404); // Overrides Tomcat's 200 default for malformed requests.

		final String URL = request.getRequestURI().replace("/project1/", ""); // strips out base URL info.

		System.out.println(URL);
		String[] UrlSections = URL.split("/");
		//System.out.println(UrlSections[0]);
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
				System.out.println("Here");
			}
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}

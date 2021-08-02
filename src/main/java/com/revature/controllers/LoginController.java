package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.LoginInfo;
import com.revature.services.UserService;

public class  LoginController {
	private static LoginInfo  loginInfo = new LoginInfo();
	private ObjectMapper objectMapper = new ObjectMapper();
	private static UserService userService = new UserService(); 
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String username = req.getParameter("userId");
//		String password = req.getParameter("password");
//		
//		RequestDispatcher reqDispatch = null;
//		PrintWriter printWriter = resp.getWriter();
//		if(loginInfo.userLogin(username,password)) {
//			//Create a new session object
//			HttpSession session = req.getSession();
//			session.setAttribute("username",username);
//			//forwards to baseURL/success
//			reqDispatch = req.getRequestDispatcher("employee.jsp");
//			reqDispatch.forward(req, resp);
//		}else {
//			reqDispatch = req.getRequestDispatcher("index.html");
//			reqDispatch.include(req, resp);
//			printWriter.print("<span>Invalid Username or password</span>");
//			
//		}
//		
//	}
	public boolean validatLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		Users userObjectInput = objectMapper.readValue(body, Users.class);
		String inputUserName = userObjectInput.getUserName();
		String inputPassword = userObjectInput.getPassword();
		
		Users retrievedUser = userService.findUser(inputUserName);
		String retrievedHash = retrievedUser.getPassword();
		
		boolean password_verified = false;
		password_verified = BCrypt.checkpw(inputPassword, retrievedHash);
		//inputPassword.equals(retrievedUser.getPassword())
		if (retrievedUser.getUserName() == null) {
			System.out.println("Error: Account not found");
			response.setStatus(404);
			return false;
		} else if (retrievedUser.getUserName().equals(inputUserName) && password_verified ) {
			System.out.println("Login is successful");
			if(retrievedUser.getUserRole().getRoleID() == 1) {
				response.setStatus(201);
			}else if(retrievedUser.getUserRole().getRoleID() == 2) {
				response.setStatus(200);
			}
			
			//create new session
			HttpSession session = request.getSession();
			session.setAttribute("userID", retrievedUser.getUserId());
			session.setAttribute("userEmail", retrievedUser.getEmailString());
			session.setAttribute("userName", retrievedUser.getUserName());
			session.setAttribute("password", retrievedUser.getPassword());
			session.setAttribute("userRoleID", retrievedUser.getUserRole());
			session.setAttribute("userRole", retrievedUser.getUserRole().getRole());
			
			return true;
		} else {
			System.out.println("went bad to be here");
			response.setStatus(501);
			
			return false;
		}
	}
	
	
	
	
	
}
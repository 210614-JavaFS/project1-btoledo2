package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Users;
import com.revature.services.UserService;

public class SuccessServlet extends HttpServlet {
	private static UserService userService= new UserService();
	
	//This is bad practice but possible. sometimes it maybe necessary.
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//This is a flag to the browser about what format the response body is in. 
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		PrintWriter printWriter = response.getWriter();
		if(session == null) {
			printWriter.print("<h1>You are NOT LOGGED IN!!!!!</h1>");
		}else {

		String username = (String) session.getAttribute("username");
		Users user = userService.findUser(username);
		printWriter.print("<h2>Welcome " + user.getFirstName() + ",you sucessfully loggen in!</h2>");
		printWriter.print("<a href ='logout'>Click here to logout!</a>");
		}
	}
	
}

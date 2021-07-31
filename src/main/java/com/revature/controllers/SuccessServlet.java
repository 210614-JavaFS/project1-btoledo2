package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.Users;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class SuccessServlet extends HttpServlet {
	private static UserService userService= new UserService();
	private static ReimbursementService reimbursementService = new ReimbursementService();
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
		printWriter.print("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Employee Page</title>\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
				+ "</head>\r\n"
				+ "<h3>Welcome Employee "+ user.getFirstName()  +" </h3>\r\n"
				+ "\r\n"
				+ "<button id=\"create\" class=\"btn btn-success\">Create Reimbursement</button>\r\n"
				+ "<a href ='logout'  style=\"float:right\">Click here to logout!</a>\r\n"
				+ "<form class=\"form-control\">\r\n"
				+ "    <label for=\"Amount\">Enter Amount:</label>\r\n"
				+ "    <input type=\"text\" id=\"amount\" >\r\n"
				+ "    <label for=\"Description\">Enter Description:</label>\r\n"
				+ "    <input type=\"text\" id=\"shortDescription\" >\r\n"
				+ "    <label for=\"Type\">Select From Drop Menu:</label>\r\n"
				+ "    <select id=\"selection\">\r\n"
				+ "        <option value=\"1\">LODGING</option>\r\n"
				+ "        <option value=\"2\">TRAVEL</option>\r\n"
				+ "        <option value=\"3\">FOOD</option>\r\n"
				+ "        <option selected value=\"4\">OTHER</option>\r\n"
				+ "      </select>\r\n"
				+ "</form>\r\n"
				+ "<button id=\"viewReim\" class=\"btn btn-primary\">View All Reimbursement</button>\r\n"
				+ "<table class=\"table\">\r\n"
				+ "    <thead>\r\n"
				+ "      <tr>\r\n"
				+ "        <th class=\"col-sm-1\">Amount</th>\r\n"
				+ "        <th class=\"col-sm-1\">Submitted</th>\r\n"
				+ "        <th class=\"col-sm-1\">Resolved</th>\r\n"
				+ "        <th class=\"col-sm-2\">Description</th>\r\n"
				+ "        <th class=\"col-sm-1\">Resolver</th>\r\n"
				+ "        <th class=\"col-sm-1\">Status</th>\r\n"
				+ "        <th class=\"col-sm-1\">Type</th>\r\n"
				+ "      </tr>\r\n"
				+ "    </thead>\r\n"
				+ "    <tbody id=\"reimbursements\">\r\n"
				+ "    </tbody>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "    \r\n"
				+ "</body>\r\n"
				+ "</html>");
		}
	}
	

}

package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontControllerServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		response.setStatus(404); // Overrides Tomcat's 200 default for malformed requests.

		final String URL = request.getRequestURI().replace("/HelloJackson/", ""); // strips out base URL info.

		System.out.println(URL);

		String[] UrlSections = URL.split("/");

		switch (UrlSections[0]) {
		case "avenger":
			break;
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}

}
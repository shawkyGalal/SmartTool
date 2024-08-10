package com.smartValue.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
			 

@WebServlet("/ApigeeAdmin/rest/*")
public class RestServelet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)            throws ServletException, IOException {

	        // Get the requested URL
	        String pathInfo = request.getPathInfo();
	        request.setAttribute("pathInfo" , pathInfo);
       
	        String jspPage = "/ApigeeAdmin/root/root.jsp" ; // determineJspPage(segmentNames, segmentValues);

	        // Forward the request to the selected JSP
	        request.getRequestDispatcher(jspPage).forward(request, response);
	    }
	
	

	}

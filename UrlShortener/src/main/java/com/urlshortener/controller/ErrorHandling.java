package com.urlshortener.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
/*
 * Unhandled requests will be redirected to this controller
 */

@Controller
@RequestMapping(ErrorHandling.Err_Base_URI)

public class ErrorHandling{
	
	public static final String Err_Base_URI="/errorPage";
	
	@RequestMapping( method = RequestMethod.GET)
	protected void getErr(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/*
		 * Feferenced from: https://www.tutorialspoint.com/servlets/servlets-exception-handling.htm
		 * 
		 */
		HttpSession session = request.getSession();

		/*
		 * Get Error Codes in a Variable
		 */
		Throwable throwable = (Throwable)
				request.getAttribute("javax.servlet.error.exception");

		Integer statusCode = (Integer)
				request.getAttribute("javax.servlet.error.status_code");

		String servletName = (String)
				request.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null){
			servletName = "Unknown";
		}

		String requestUri = (String)
				request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null){
			requestUri = "Unknown";
		}

		/*
		 * Check error cause
		 * Sent appropriate request attribute 
		 * Forward the request to a JSP
		 */
		if (throwable == null && statusCode == null){
			request.setAttribute("errorMessage", "Error - Information is missing");
		}else if (statusCode != null){
			request.setAttribute("errorMessage", "Status Code: "+ statusCode.toString());
		}else{
			String errMessage = "For the Servlet: "+servletName +", Following exception has occured: " +throwable.getClass().getName();
			request.setAttribute("errorMessage", errMessage);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/errorPages/error404.jsp").forward(request, response);
	}

	@RequestMapping( method = RequestMethod.POST)
	protected void postErr(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.getErr(request, response);

	}
}

package com.urlshortener.controller;

import com.urlshortener.model.dao.AuthDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import com.urlshortener.model.dto.User;
/*
 * This servlet handles requests and responses to/from the Home Page for SignUP/Login Requests
 */

@Controller
@RequestMapping(LoginServlet.login_Base_URI)
public class LoginServlet
{   public static final String login_Base_URI="/login";
	//DBRequesthandler reqHandler = new DBRequesthandler();
	@Autowired
	private AuthDao authentication;

	
//	public void init(ServletConfig config) throws ServletException{
//		super.init(config);
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//
//	}
	
	public void setAuthentication(AuthDao authentication)
	{
		this.authentication = authentication;
	}

	


	@RequestMapping( method = RequestMethod.GET)
	protected void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("loginServlet");
		HttpSession session = request.getSession();

		/*This part handles the actions which can happen on the home page*/
		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}
		else if(session.getAttribute("username") != null)
		{

			response.sendRedirect("userprofile");
			return;
		}

		request.setAttribute("loginFailed", false);
		request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
		.forward(request, response);

	}

	@RequestMapping( method = RequestMethod.POST)
	protected void postLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		/*This part handles the actions which can happen on the home page*/
		String action = request.getParameter("action");
		if(action == null)
			action = "page";
		switch(action)
		{
		case "login":
			this.login(request, response, authentication);
			break;
		case "signup":
		default:
			System.out.println(authentication);
			this.signup(request, response, authentication);
			break;
		}

	}

	/*This Function handles requests to Login*/
	private void login(HttpServletRequest request, HttpServletResponse response, AuthDao authentication)
			throws ServletException, IOException
	{

		/*
		 * 1. Get Session
		 * 2. Check if Username is null
		 * 	  Yes: return
		 * 	  No:
		 * 		1. Get username and password from request parameters
		 * 		2. Check is username is null or password is null or if the 
		 * 			given username does not exist or the password does not match the specified Username
		 * 			YES:
		 * 				a. Login failed
		 * 				b. Redirect to home page
		 * 			No:
		 * 				a. Set username to user session
		 * 				b. redirect to userprofile servlet 
		 * 				
		 */
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null)
		{
			return;
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(username!=null && password != null){
			User user = authentication.loginUser(username, password);
			if(user == null){
				session.setAttribute("loginFailed", "Enter valid detials !");
				response.sendRedirect("home");
			}
			else{
				if(password.equals(user.getPassword())){
					session.setAttribute("username", username);
					request.changeSessionId();
					response.sendRedirect("userprofile");
				}
				else{
					session.setAttribute("loginFailed", "Username or Password is incorrect !");
					response.sendRedirect("home");
				}
			}
		}
	}


	/*This Function handles requests to Login*/	
	private void signup(HttpServletRequest request, HttpServletResponse response,AuthDao authentication )
			throws ServletException, IOException
	{
		System.out.println("In Sign Up !");
		//this.authentication = authentication;

		System.out.println(authentication);

		/*
		 * 1. Get Session
		 * 2. Check if Username is null
		 * 	  Yes: return
		 * 	  No:
		 * 		1. Get username and password from request parameters
		 * 		2. Check is username is null or password is null or if the 
		 * 			given username already exists
		 * 			YES:
		 * 				a. Signup failed
		 * 				b. Redirect to home page
		 * 			No:
		 * 				a. Set username to user session
		 * 				b. redirect to userprofile servlet 
		 * 				
		 */
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null)
		{
			return;
		}

		String username = request.getParameter("new_username");
		String password = request.getParameter("new_password");

		if(username==null || password == null ||!authentication.signupUsr(username, password)){
			session.setAttribute("signupFailed", "true");
			response.sendRedirect("home");
		}
		else{
			session.setAttribute("username", username);
			request.changeSessionId();
			response.sendRedirect("userprofile");
		}
	}

	
}

package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import model.DBRequesthandler;
import model.dao.AuthenticationDao;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/*
 * This servlet handles requests and responses to/from the Home Page for SignUP/Login Requests
 */

@WebServlet(
		name = "loginServlet",
		urlPatterns = "/login"
		)
@Controller
public class LoginServlet extends HttpServlet
{
	DBRequesthandler reqHandler = new DBRequesthandler();
	private AuthenticationDao authentication;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		/*This part handles the actions which can happen on the home page*/
		String action = request.getParameter("action");
		if(action == null)
			action = "page";
		switch(action)
		{
		case "login":
			this.login(request, response);
			break;
		case "signup":
		default:
			this.signup(request, response);
			break;
		}

	}

	/*This Function handles requests to Login*/
	private void login(HttpServletRequest request, HttpServletResponse response)
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

		if(!reqHandler.validateUsersFromDB(username, password)){
			session.setAttribute("loginFailed", "true");
			response.sendRedirect("home");
		}
		else{
			session.setAttribute("username", username);
			request.changeSessionId();
			response.sendRedirect("userprofile");
		}

	}


	/*This Function handles requests to Login*/	
	private void signup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
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
		this.authentication.createNewUser(username, password);
		session.setAttribute("username", username);
		request.changeSessionId();
		response.sendRedirect("userprofile");
		
		
/*
		if(!reqHandler.addNewUserToDB(username, password)){
			session.setAttribute("signupFailed", "true");
			response.sendRedirect("home");
		}
		else{
			session.setAttribute("username", username);
			request.changeSessionId();
			response.sendRedirect("userprofile");
		}
	*/
		

	}

}

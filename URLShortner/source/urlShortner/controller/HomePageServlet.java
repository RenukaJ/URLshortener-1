package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import model.DBRequesthandler;
import model.dao.GlobalURLDao;



/*
 * This servlet handles requests and responses to/from the Home Page
 */


@WebServlet(
		name = "HomePageServlet",
		urlPatterns = {"/home"},
		loadOnStartup = 1
		)

@Controller
public class HomePageServlet extends HttpServlet{
	
	DBRequesthandler reqHandler = new DBRequesthandler();
	
	@Autowired
	private GlobalURLDao globalurlDao;
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

	}
	
	public void setGlobalurlDao(GlobalURLDao globalurlDao){
		this.globalurlDao = globalurlDao;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{   
		HttpSession session = request.getSession();

		//ToDo: If user clicks on back button and is currently logged in, then Home page should not be shown

		/*The below code checks if user is logged in or not.
		 * If logged in, user is redirected to /userprofile Servlet
		 */
		if(request.getSession().getAttribute("username") != null)
		{
			response.sendRedirect("userprofile");
			return;
		}

		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}

		/*If none of the above cases match, /home servlet redirects to home.jsp for displaying main page content*/
		this.loadHomePage(request, response);
	}     

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		/*The below code checks if user is logged in or not.
		 * If logged in, user is redirected to /userprofile Servlet
		 */
		if(request.getSession().getAttribute("username") != null)
		{
			response.sendRedirect("userprofile");
			return;
		}

		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}

		/*This part handles the actions which can happen on the home page*/
		String action = request.getParameter("action");
		if(action == null)
			action = "page";

		switch(action)
		{
		case "getLongUrl":
			System.out.println("get Long Url");
			this.getLongURL(request, response);
			break;
		case "page":
		default:
			this.loadHomePage(request, response);
			break;
		}
	}


	/*This Function handles requests from users to get the Original URL when the user inputs Long Url*/
	private void getLongURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/*
		 *1. Get Short URL from the request parameter
		 *2. Validate with the domain name
		 *	2.a Check if the entered SHort URL exists in the DB
		 *		YES:
		 *		1. Get the Long URL from DB
		 *		2. Set request Attribute 'longUrl' to be the returned value
		 *		3. Forward the request to home.jsp 
		 *		NO:
		 *		1. Set the value of retrened parameter to undefined
		 *		2. Forward the request to home.jsp 
		 *3. If URL not validated with the domain name
		 *		a. Set the value of retrened parameter to undefined
		 *		b. Forward the request to home.jsp 
		 */
		String shUrl = request.getParameter("shortUrl");
		System.out.println("shorturl "+shUrl);
		HttpSession session = request.getSession();
		System.out.println("shorturl+domain "+shUrl);

		if(shUrl.startsWith("http://localhost:8080/URLShortner/")){
			if(globalurlDao.shortUrlexists(shUrl)){
				String longUrl = globalurlDao.getoriLongURL(shUrl);
				session.setAttribute("longUrl", longUrl);
				System.out.println("longurl:"+longUrl);
			}
			else{
				session.setAttribute("longUrl", "undefined");
				System.out.println("longurl: undefined");
			}		
		}
		else{
			session.setAttribute("longUrl", "undefined");
		}	
		response.sendRedirect("home");
	}

	/*Responsible for rendering requests to the home.jsp page*/
	private void loadHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(request, response);
	}



}

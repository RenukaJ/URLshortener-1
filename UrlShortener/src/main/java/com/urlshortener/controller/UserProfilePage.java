package com.urlshortener.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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


import com.urlshortener.model.dto.UserUrl;
import com.urlshortener.model.dao.*;
import com.urlshortener.model.dto.*;
@WebServlet(
		name = "UserProfileServlet",
		urlPatterns = "/userprofile"
		)

/**************Check logout belongs to get or post**************/
@Controller
public class UserProfilePage extends HttpServlet{

	//DBRequesthandler reqHandler = new DBRequesthandler();
	@Autowired
	private GlobalURLDao globalurlDao;
	@Autowired
	private UserURLDao userurlDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

	}
	
	public void setGlobalurlDao(GlobalURLDao globalurlDao){
		this.globalurlDao = globalurlDao;
	}
	public void setUserurlDao(UserURLDao userurlDao){
		this.userurlDao = userurlDao;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		//this need to be optimized

		/*The below code checks if user is logged in or not.
		 * If logged in, user is redirected to /userprofile Servlet
		 */
		if(request.getSession().getAttribute("username") == null || request.getSession() == null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}

		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}


		String action = request.getParameter("action");

		if(action == null)
			action = "page";

		switch(action)
		{
		case "logout":
			this.logout(request, response);
			break;
		case "page":
		default:
			this.loadPage(request, response);
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		/*The below code checks if user is logged in or not.
		 * If logged in, user is redirected to /userprofile Servlet
		 */
		if(request.getSession().getAttribute("username") == null || request.getSession() == null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}

		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("home");
			return;
		}

		String action = request.getParameter("action");

		if(action == null)
			action = "page";

		switch(action)
		{
		case "logout":
			this.logout(request, response);
			break;
		case "deleteUrl":
			this.deleteUrlFromUserList(request, response);
			break;
		case "shortenURL":
			this.shortenURL(request, response);
			break;
		case "page":
		default:
			this.loadPage(request, response);
			break;
		}
	}


	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("home");
		return;
	}


	private void shortenURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//Todo - Need to handle per user longurl
		//Todo - Handle if null in URL/
		/*
		 * 1. Get all session Details - Long URL, and Username
		 * 2. Check if Url is null; if null send error // may be do the check in UI itself *******************
		 * 3. Check if shortened URL already exists
		 * 		YES:
		 * 		Get shortened URL 
		 *      Add shortened URL and Long URl to UserUrlList
		 *  NO:
		 *  	Create Shortened URL
		 *  	Add shortened URL to and Long URL to UserUrList
		 *  	Add URL to Global urlCount and initialize it to 0
		 *  	Add URL to Global urlMapping
		 */
		HttpSession session = request.getSession();
		String longUrl = request.getParameter("longUrl");
		String username = (String) session.getAttribute("username");

		UrlMappingList urlList = globalurlDao.getshortURL(longUrl);
		if(urlList == null){
			String shortUrl = globalurlDao.addNewValueToGlobalURLList(longUrl);
			if(shortUrl != null){
				userurlDao.addUrlToUserList(username, shortUrl, longUrl);
				response.sendRedirect("userprofile");
			}
			else{
				System.out.println("Something went wrong in processing the long URL");
			}
			
		}
		else{
			userurlDao.addUrlToUserList(username, urlList.getShortUrl(), longUrl);
		}
		response.sendRedirect("userprofile");
	}


	private void loadPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/*
		 * 1. Check if UserUrlList exists for current user
		 * 	YES:
		 * 		a. Get the list of shortenedUrl for a user
		 * 		b. Set request Attributes
		 * 			Attributes:
		 * 			b.1 username = Username of Current User
		 * 			b.2 links = UserUrlList  - Map of all long and short urls by user 
		 * 			b.3 linksCount = GLobal Map of url visits count
		 * 		c. Send the control over to the userprofileJsp
		 */
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		request.setAttribute("username", username);
		List<UserUrl> userurl = userurlDao.getUserUrlList(username);
		if(userurl != null){
//			System.out.println("Pasing values");
			request.setAttribute("links", userurl);
		}
		else{
			request.setAttribute("links", null);
		}
		
		//return hashmap having shorturl,visitcount. and set to linksCount.
		 HashMap<String,Integer> globalUrlCount = globalurlDao.getAllVisitCountMap(userurl);		 
		 request.setAttribute("linksCount", globalUrlCount);
		 
		request.getRequestDispatcher("/WEB-INF/jsp/view/userprofile.jsp").forward(request, response);

	}


	private void deleteUrlFromUserList (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		/*

		 * a. Get the list object of shortenedUrl for a user
		 * b. Delete entry from url list object
		 * c.Send the control over to the userprofileJsp
		 */
		System.out.println("In delete");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String urlToRemove = request.getParameter("urlToRemove");
		userurlDao.deleteUserListValue(username, urlToRemove);
		response.sendRedirect("userprofile");
	}

}
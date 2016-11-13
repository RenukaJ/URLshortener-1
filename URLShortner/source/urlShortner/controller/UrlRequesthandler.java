package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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
import model.dao.UserURLDao;
import model.dto.UrlMappingList;

/*
 * This servlet handles requests and responses for all responses for the domainname/short/* URL
 */
@WebServlet(
		name = "shortURLHandler",
		urlPatterns = "/short/*"
		)
@Controller
public class UrlRequesthandler extends HttpServlet{

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
			throws ServletException , IOException
	{
		/*This part handles the actions which can happen on the home page*/
		String action = request.getParameter("action");

		if(action == null)
			action = "page";

		switch(action)
		{
		case "gotoUrl":
			this.gotoUrl(request, response);
			break;
		case "page":
		default:
			browserUrlRequstAction(request, response);
			break;
		}
	}

	/*Handle requests from User Click from User profile page*/
	public void gotoUrl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/*
		 * 1. Get URL from request parameters
		 * 2. Check if the short URL exists in DB
		 * 		2.a YES
		 * 			1. Get the corresponding long URL
		 * 			2. Add the count for URL visits
		 * 			3. Try to make a connection the the Long/original URL
		 * 			4. Get response code
		 * 			5. Check if Response Code is 22
		 * 				YES:
		 * 				a. Redirect to the Original URL
		 * 				No:
		 * 				a. Redirect to Error Page
		 * 		2.b No
		 * 			Redirect to Error Page
		 */

		HttpSession session = request.getSession();
		String shortUrl = request.getParameter("url");
		
		if(shortUrl != null){
		UrlMappingList urlList = globalurlDao.getLongURL(shortUrl);
		
		globalurlDao.addURLVisitCount(shortUrl);
		
		int responseCode = 404;
		
		try{
			HttpURLConnection huc = (HttpURLConnection) new URL(urlList.getLongUrl()).openConnection();
			responseCode = huc.getResponseCode();
		}
		catch(Exception e){
			responseCode = 404;
		}

		if (responseCode == 200) {
			response.sendRedirect(urlList.getLongUrl());
		}
		else{
			response.sendRedirect("/URLShortner/errorPage");
		}
		}
		else{
			response.sendRedirect("/URLShortner/errorPage");
			return;
		}

		/*String orgUrl = "";
		String shUrl = request.getParameter("url");

		if(reqHandler.shortUrlexists(shUrl)){
			orgUrl = reqHandler.getLongUrl(shUrl);
			reqHandler.addUrlVisitCount(shUrl);

			int responseCode = 404;

			try{
				HttpURLConnection huc = (HttpURLConnection) new URL(orgUrl).openConnection();
				responseCode = huc.getResponseCode();
			}
			catch(Exception e){
				responseCode = 404;
			}
			if (responseCode == 200) {

				response.sendRedirect(orgUrl);
			}
			else{
				response.sendRedirect("/URLShortner/errorPage");
			}
		}
		else{
			response.sendRedirect("/URLShortner/errorPage");
			return;
		}*/

	}


	/*Handle requests from User query from browser*/
	public void browserUrlRequstAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		/*
		 * 1. Get URL from request parameters
		 * 2. Append http://localhost:8080 to the URL
		 * 3. Check if the short URL exists in DB
		 * 		3.a YES
		 * 			1. Get the corresponding long url
		 * 			2. Add the count for URL vists
		 * 			3. Try to make a coonection the the Long/original URL
		 * 			4. Get response code
		 * 			5. Check if Response Code is 22
		 * 				YES:
		 * 				a. Redirect to the Original URL
		 * 				No:
		 * 				a. Redirect to Error Page
		 * 		3.b No
		 * 			Redirect to Error Page
		 */
		String shUrl = request.getRequestURI();
		shUrl = "http://localhost:8080" + shUrl;

		String orgUrl = "";

		if(reqHandler.shortUrlexists(shUrl)){
			orgUrl = reqHandler.getLongUrl(shUrl);
			reqHandler.addUrlVisitCount(shUrl);

			int responseCode = 404;
			try{
				HttpURLConnection huc = (HttpURLConnection) new URL(orgUrl).openConnection();
				responseCode = huc.getResponseCode();
			}
			catch(Exception e){
				responseCode = 404;
			}

			if (responseCode == 200) {
				response.sendRedirect(orgUrl);
			}
			else{
				response.sendRedirect("/URLShortner/errorPage");
			}

		}
		else{
			response.sendRedirect("/URLShortner/errorPage");
			return;
		}
	}
}



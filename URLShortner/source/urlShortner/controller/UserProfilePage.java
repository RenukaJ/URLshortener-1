package controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBRequesthandler;
import model.UserUrlList;


@WebServlet(
        name = "UserProfileServlet",
        urlPatterns = "/userprofile"
)

/**************Check logout belongs to get or post**************/

public class UserProfilePage extends HttpServlet{
	
	DBRequesthandler reqHandler = new DBRequesthandler();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
		HttpSession session = request.getSession();
		//this need to be optimized
		  
		  /*The below code checks if user is logged in or not.
		   * If logged in, user is redirected to /userprofile Servlet
		   */
		  if(request.getSession().getAttribute("username") == null)
	        {
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
        System.out.println(action);
        
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
		  if(request.getSession().getAttribute("username") == null)
	        {
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
        System.out.println(action);
        
        if(action == null)
            action = "page";
        
        switch(action)
        {
            case "logout":
                this.logout(request, response);
                break;
            case "shortenURL":
            	this.shortenURL(request, response);
            case "page":
            default:
            	this.loadPage(request, response);
                break;
        }
    }
	
	
	private void logout(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		 System.out.println("InLogout");
		 HttpSession session = request.getSession();
		 session.invalidate();
		 request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
         .forward(request, response);
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
		 String encoded = "";
		 

			 if(reqHandler.globalurlMappingExists(longUrl)){
				 encoded  = reqHandler.getShortURl(longUrl);
				 reqHandler.addUrlMappingToUser(username, longUrl, encoded);
				 
			 }
			 else{
				 encoded = reqHandler.generateShortURL(longUrl);
				 reqHandler.addUrlMappingToUser(username, longUrl, encoded);
				 reqHandler.addUrlToCountsList(encoded);
				 reqHandler.addUrltoMappingList(encoded, longUrl);
			 }
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
		 
		 if(reqHandler.userUrlListExists(username)){
			 System.out.println(reqHandler.getUserUrlList(username));
			 request.setAttribute("links", reqHandler.getUserUrlList(username));
			 request.setAttribute("linksCount", reqHandler.getGlobalUrlCount());
		 }
		 else{
			 System.out.println("No Username");
			 request.setAttribute("links", null);
		 }
		 
	     request.getRequestDispatcher("/WEB-INF/jsp/view/userprofile.jsp").forward(request, response);
	
		 
	}
	 
	 

	


		
		
}
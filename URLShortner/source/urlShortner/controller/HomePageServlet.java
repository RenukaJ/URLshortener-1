package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(
        name = "HomePageServlet",
        urlPatterns = {"/home"},
        loadOnStartup = 1
)

public class HomePageServlet extends HttpServlet{

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
			  
			  /*This part handles the actions which can happen on the hoem page*/
	    	String action = request.getParameter("action");
	        System.out.println(action);
	        
	        if(action == null)
	            action = "page";
	        
	        switch(action)
	        {
	            case "generateLongUrl":
	                this.generateLongURL(request, response);
	                break;
	            case "page":
	            default:
	            	this.loadHomePage(request, response);
	                break;
	        }
	    }
	    
	    private String generateLongURL(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
	    {
			return null;
		}
	    
	    private void loadHomePage(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
	    {
	    	request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(request, response);
		}
	    
	    

}

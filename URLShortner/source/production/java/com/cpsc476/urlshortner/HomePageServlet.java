package com.cpsc476.urlshortner;

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

		   
	        request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
	               .forward(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	    {
	        
	    }
	    
	    public String generateLongURL(String shortURL){

			if(UrlMap.URLMapping.containsKey(shortURL)){
				return UrlMap.URLMapping.get(shortURL);
			}
			return null;
		}

}

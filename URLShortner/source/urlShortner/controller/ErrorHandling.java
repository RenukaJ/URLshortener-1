package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * Unhandled requests will be redirected to this servlet
 */
@WebServlet(
		name = "errorHandlingServlet",
		urlPatterns = "/errorPage"
		)

public class ErrorHandling extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/*
		 * https://www.tutorialspoint.com/servlets/servlets-exception-handling.htm
		 * 
		 */
		HttpSession session = request.getSession();
		
		
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request, response);

	}
}

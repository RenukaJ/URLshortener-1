package com.cpsc476.urlshortner;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name = "shortURLHandler",
        urlPatterns = "/short/*"
)

public class URLRequestHandler extends HttpServlet{
// convert url to shortened url
	
	protected static final Map<String, Integer> urlCount = new Hashtable<>();
	
	public void addNewUrl(String url){
		urlCount.put(url, 0);
	}
	public void addUrlCount(String url){
		int count = urlCount.get(url);
		count++;
		urlCount.put(url, count);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException
    {
		//Get all objects of URLHandler class.
		//search for relevant match
		//add clicks to URL
		
		String action = request.getParameter("action");
        System.out.println(action);
        
        if(action == null)
            action = "page";
        
        switch(action)
        {
            case "processshURL":
            	System.out.println("in PP");
                this.processShURL(request, response);
                break;
            case "page":
            default:
            	defaction(request, response);
                break;
        }
    }
	
	public void processShURL(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			 {
		
				
				
				System.out.println("In process");
				String orgUrl = "";
				String shUrl = request.getParameter("url");
				String parts[] = shUrl.split("short/");
				System.out.println("parts is: " + parts[1]);
				//Search for original URL and decode
				//Add count
				if(UrlMap.URLMapping.containsKey(parts[1])){
					orgUrl = UrlMap.URLMapping.get(parts[1]);
					addUrlCount(orgUrl);
					System.out.println("Incremented Count: " + urlCount.get(orgUrl));
					response.sendRedirect(orgUrl);
				}
				else{
					
				}
				
				

			 }
	
	public void defaction(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			 {
		//get value after short/ of url
		//split
		//add count
		//BUG - Invalid url not handled
				String shUrl = request.getRequestURI();
				System.out.println(shUrl);
				String orgUrl = "";
				String parts[] = shUrl.split("short/");
				System.out.println("parts is: " + parts[1]);
				
				//search for key 
				//return org url
				//add count
				if(UrlMap.URLMapping.containsKey(parts[1])){
					orgUrl = UrlMap.URLMapping.get(parts[1]);
					addUrlCount(orgUrl);
					System.out.println("Incremented Count: " + urlCount.get(orgUrl));
					response.sendRedirect(orgUrl);
				}
				else{
					
				}
				
				
			 }

	
}

package com.cpsc476.urlshortner;

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

@WebServlet(
        name = "UserProfileServlet",
        urlPatterns = "/userprofile"
)
public class UserProfilePage extends HttpServlet{
	
	URLShortner urlMapperUtil = new URLShortner();
	
	private Map<String, URLHandler> urlhandler = new LinkedHashMap<>();
	//Key = username, Value = Object of URLDb hashmap
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
		
		//this need to be optimized
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
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
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
		 //Todo - Handle if null in URL
		 
		 HttpSession session = request.getSession();
		 String longUrl = request.getParameter("longUrl");
		 String username = (String) session.getAttribute("username");
		 
		 if(urlhandler.containsKey(username)){
			 URLHandler hd = urlhandler.get(username);
			 String encoded = generateShortenedURL(longUrl);
			 hd.urlList.put(longUrl, encoded);
			 UrlMap.urlCount.put(longUrl, 0);
		 }
		 else{
			 String encod = generateShortenedURL(longUrl);
			 URLHandler uh = new URLHandler(longUrl, encod);
			 urlhandler.put(username, uh);
			
		 }
		 
		 
	}
	 
	 private void loadPage(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		 HttpSession session = request.getSession();
		 String username = (String) session.getAttribute("username");
		 request.setAttribute("username", username);
		 if(urlhandler.containsKey(username)){
			 System.out.println(urlhandler.get(username).urlList);
			 request.setAttribute("links", urlhandler.get(username).urlList);
			 request.setAttribute("linksCount", UrlMap.urlCount);
		 }
		 else{
			 request.setAttribute("links", null);
		 }
		 
	     request.getRequestDispatcher("/WEB-INF/jsp/view/userprofile.jsp")
	            .forward(request, response);
	
		 
	}
	 
	 

	 public String generateShortenedURL(String longUrl){
			//convert longUrl into 36 bit hash value
			//take max bits
			//convert to String with base 36 encoding
		 String encodedUrl = "";
			try{
				if(UrlMap.URLMapping.containsValue(longUrl)){
					for(String o : UrlMap.URLMapping.keySet()){
						if(UrlMap.URLMapping.get(o).equals(longUrl)){
							encodedUrl = o;
						}
					}
				}
				else{
					Integer hashKey = (int) UUID.nameUUIDFromBytes(longUrl.getBytes()).getMostSignificantBits();
					encodedUrl = Integer.toString(hashKey, 36);
					UrlMap.URLMapping.put(encodedUrl, longUrl);
					
				}

			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}

			return "http://localhost:8080/URLShortner/short/" +encodedUrl;
		
		}


		
		
}
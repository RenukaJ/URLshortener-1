package model;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GlobalUrlMap;
import model.UserUrlList;

public class DBRequesthandler{
	
	GlobalUrlMap globalmap = new GlobalUrlMap();
	
	public boolean globalurlMappingExists(String longUrl){
		return globalmap.urlMappingexists(longUrl);
	}
	
	public boolean shortUrlexists(String shortUrl){
		return globalmap.shortUrlexists(shortUrl);
	}
	
	public String getLongUrl(String shortUrl){
		return globalmap.getLongUrl(shortUrl);
	}
	
	public String getShortURl(String longUrl){
		return globalmap.getShortUrl(longUrl);
	}
	
	public Map<String, String> getUserUrlList(String username){
		return globalmap.getUserUrlList(username);
	}
	
	public boolean deleteUrlFromUserList(String username, String urlToRemove){
		if(userUrlListExists(username)){
			System.out.println("Url List exists");
			UserUrlList urlList = globalmap.getUserUrlhandler(username);
			if(urlList.deleteUserListValue(urlToRemove)){
				return true;
			}
		}
		return false;
	}
	
	public Map<String, Integer> getGlobalUrlCount(){
		return globalmap.getGlobalUrlCount();
	}
	
	public boolean userUrlListExists(String username){
		return globalmap.userUrlhandlerExists(username);	
	}
	
	public void addUrlMappingToUser(String username, String longurl, String shorturl){
		/*
		 * Check if  UserUrlList Handler for user exists in GlobalUrl Map - userUrlHandler
		 * Yes:
		 * 	1. Update the Handler with new values
		 * No:
		 * 	1. Create new Handler and add to list
		 *  2. Add to GlobalUrlMap - userUrlHandler
		 */
		if(userUrlListExists(username)){
			UserUrlList urlList = globalmap.getUserUrlhandler(username);
			urlList.addNewUserListValue(shorturl, longurl);
		}
		else{
			UserUrlList urlList = new UserUrlList(shorturl, longurl);
			globalmap.addNewUserToUrlhanlder(username, urlList);
			
		}
	}
	
	public void addUrlToCountsList(String shortUrl){
		System.out.println("Going to add");
		globalmap.addNewUrlTourlCount(shortUrl);
	}
	
	public void addUrltoMappingList(String shortUrl, String longUrl){
		globalmap.addNewUrlTourlMapping(shortUrl, longUrl);
	}
	
	public void addUrlVisitCount(String shortUrl){
		globalmap.addUrlCount(shortUrl);
	}
	
	public String generateShortURL(String longUrl){
		/*
		 * 1. convert longUrl into 36 bit hash value
		 * 2. ake Max bits
		 * 3. Convert to String with base 36 encoding
		 * 4. Return value
		 */

	 String encodedUrl = "";
	 DBRequesthandler reqHandler = new DBRequesthandler();
	
		Integer hashKey = (int) UUID.nameUUIDFromBytes(longUrl.getBytes()).getMostSignificantBits();
		encodedUrl = Integer.toString(hashKey, 36);
		return "http://localhost:8080/URLShortner/short/" +encodedUrl;
	
	}
}

	/*
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException
    {
		//Get all objects of UserUrlList class.
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
				if(GlobalUrlMap.URLMapping.containsKey(parts[1])){
					orgUrl = GlobalUrlMap.URLMapping.get(parts[1]);
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
				if(GlobalUrlMap.URLMapping.containsKey(parts[1])){
					orgUrl = GlobalUrlMap.URLMapping.get(parts[1]);
					addUrlCount(orgUrl);
					System.out.println("Incremented Count: " + urlCount.get(orgUrl));
					response.sendRedirect(orgUrl);
				}
				else{
					
				}
				
				
			 }
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException
    {
		//Get all objects of UserUrlList class.
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
				if(URLMapping.containsKey(parts[1])){
					orgUrl = URLMapping.get(parts[1]);
				}
				addUrlCount(orgUrl);
				System.out.println("Incremented Count: " + urlCount.get(orgUrl));
				response.sendRedirect(orgUrl);
				

			 }
	
	public void defaction(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			 {
		//get value after short/ of url
		//split
		//add count
		//BUG - Invalid url not handled
				String shUrl = request.getRequestURI();
				String orgUrl = "";
				System.out.println(shUrl);
				String parts[] = shUrl.split("short/");
				System.out.println("parts is: " + parts[1]);
				if(URLMapping.containsKey(parts[1])){
					orgUrl = URLMapping.get(parts[1]);
				}
				System.out.println("Org url def action:" +orgUrl);
				addUrlCount(orgUrl);
				System.out.println("Incremented Count: " + urlCount.get(orgUrl));
				response.sendRedirect(orgUrl);
				
			 }

	
	
	 public String generateShortenedURL(String longUrl){
			//convert longUrl into 36 bit hash value
			//take max bits
			//convert to String with base 36 encoding
		 String encodedUrl = "";
			try{
				if(GlobalUrlMap.URLMapping.containsValue(longUrl)){
					for(String o : GlobalUrlMap.URLMapping.keySet()){
						if(GlobalUrlMap.URLMapping.get(o).equals(longUrl)){
							encodedUrl = o;
						}
					}
				}
				else{
					Integer hashKey = (int) UUID.nameUUIDFromBytes(longUrl.getBytes()).getMostSignificantBits();
					encodedUrl = Integer.toString(hashKey, 36);
					GlobalUrlMap.URLMapping.put(encodedUrl, longUrl);
					
				}

			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}

			return "http://localhost:8080/UrlRequesthandler/short/" +encodedUrl;
		
		}
		
		
		
		
		try{
			if(GlobalUrlMap.URLMapping.containsValue(longUrl)){
				for(String o : GlobalUrlMap.URLMapping.keySet()){
					if(GlobalUrlMap.URLMapping.get(o).equals(longUrl)){
						encodedUrl = o;
					}
				}
			}
			else{
				Integer hashKey = (int) UUID.nameUUIDFromBytes(longUrl.getBytes()).getMostSignificantBits();
				encodedUrl = Integer.toString(hashKey, 36);
				GlobalUrlMap.URLMapping.put(encodedUrl, longUrl);
				
			}

	

*/
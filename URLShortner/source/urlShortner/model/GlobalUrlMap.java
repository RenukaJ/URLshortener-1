
package model;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginServlet;

public class GlobalUrlMap extends HttpServlet{

	//This HashTable consists of short URLs with the list of their visit Count
	private static Map<String, Integer> urlCount = new Hashtable<>();
	
	//This Hashmap consists of mapping of shortened URL to the log URL
    private static HashMap<String, String> urlMapping = new HashMap<>();
    
    private static Map<String, UserUrlList> userUrlHandler = new LinkedHashMap<>();
	//Key = username, Value = Object of URLDB hashmap
	
    
    protected static final Map<String, String> userDatabase = new Hashtable<>();
	/*Map of existing Users in DB */

	static {
		userDatabase.put("Nicholas", "password");
		userDatabase.put("Sarah", "drowssap");
		userDatabase.put("Mike", "wordpass");
		userDatabase.put("John", "green");
	}
	
	public void addNewUrlTourlCount(String url){
		urlCount.put(url, 0);
	}
	
	public boolean validateUser(String username, String password){
		if(username == null || password == null ||
					!userDatabase.containsKey(username) ||
				!password.equals(userDatabase.get(username)))
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	public boolean addNewUserToDB(String username, String password){
		if(username == null || password == null ||
				userDatabase.containsKey(username))
		{
			return false;
		}
		else
		{
			userDatabase.put(username, password);
			return true;
		}
	}

	public void addNewUrlTourlMapping(String shortUrl, String longUrl){
		urlMapping.put(shortUrl, longUrl);
	}
	
	public void addUrlCount(String url){
		int count = urlCount.get(url);
		count++;
		urlCount.put(url, count);
	}
	
	public Map<String, Integer> getGlobalUrlCount(){
		return urlCount;
	}
	
	public boolean urlMappingexists(String longUrl){
		return urlMapping.containsValue(longUrl);
	}
	
	public boolean shortUrlexists(String shortUrl){
		return urlMapping.containsKey(shortUrl);
	}
	
	public String getLongUrl(String shortUrl){
		return urlMapping.get(shortUrl);
	}
	
	public void addNewUserToUrlhanlder(String username, UserUrlList urlList){
		userUrlHandler.put(username, urlList);
	}
	
	public boolean userUrlhandlerExists(String username){
		return userUrlHandler.containsKey(username);
	}
	
	public UserUrlList getUserUrlhandler(String username){
		return userUrlHandler.get(username);
	}
	
	public Map<String, String> getUserUrlList(String username){
		UserUrlList urlList  = userUrlHandler.get(username);
		return urlList.getUserUrlList();
	}
	
	public String getShortUrl(String longUrl){
		try{
			if(urlMapping.containsValue(longUrl)){
				for(String shorturl : urlMapping.keySet()){
					if(urlMapping.get(shorturl).equals(longUrl)){
						return shorturl;
					}
				}
			}
			else{
				return null;
			}
		
		}
		catch(Exception ex){
			ex.printStackTrace(System.out);
		}
		return null;
	}
	
	
}
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

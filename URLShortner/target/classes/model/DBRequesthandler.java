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
/*
 * This Java file act as the "Model" in our Architecture
 * Servlets use the functions in this class to update database
 * The functions in this class communicate with the DB to perform necessary operations
 */
public class DBRequesthandler{

	GlobalUrlMap globalmap = new GlobalUrlMap();

	/* Check if URL exists in URLMapping Hashmap*/
	public boolean globalurlMappingExists(String longUrl){
		return globalmap.urlMappingexists(longUrl);
	}

	/*Check if Short URL exixts in HashMap*/
	public boolean shortUrlexists(String shortUrl){
		return globalmap.shortUrlexists(shortUrl);
	}

	/*Get Long URL corresponding to the short URL*/
	public String getLongUrl(String shortUrl){
		return globalmap.getLongUrl(shortUrl);
	}

	/*GEt Short URL corresponding to a long URL*/
	public String getShortURl(String longUrl){
		return globalmap.getShortUrl(longUrl);
	}

	/*Get the Map of all URLs shortened by a User*/
	public Map<String, String> getUserUrlList(String username){
		return globalmap.getUserUrlList(username);
	}

	/*Delete URL from User URL List*/
	public boolean deleteUrlFromUserList(String username, String urlToRemove){
		if(userUrlListExists(username)){
			UserUrlList urlList = globalmap.getUserUrlhandler(username);
			if(urlList.deleteUserListValue(urlToRemove)){
				return true;
			}
		}
		return false;
	}

	/*Fetch Map containing the shortened URL and their visit count*/
	public Map<String, Integer> getGlobalUrlCount(){
		return globalmap.getGlobalUrlCount();
	}

	/*Check if UserURLLIst object exists for a user*/
	public boolean userUrlListExists(String username){
		return globalmap.userUrlhandlerExists(username);	
	}

	/*Add URL to a userurllist*/
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

	/*Add URL to urlcount Map*/
	public void addUrlToCountsList(String shortUrl){
		globalmap.addNewUrlTourlCount(shortUrl);
	}
	/*Add url to global list of URL Mappings*/
	public void addUrltoMappingList(String shortUrl, String longUrl){
		globalmap.addNewUrlTourlMapping(shortUrl, longUrl);
	}

	/*Add URL visit Count*/
	public void addUrlVisitCount(String shortUrl){
		globalmap.addUrlCount(shortUrl);
	}

	/*Generate short URL*/
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
	/*Validate users for Login*/
	public boolean validateUsersFromDB(String username, String password){
		return (globalmap.validateUser(username, password));
	}

	/*Add New user to DB*/
	public boolean addNewUserToDB(String username, String password){
		return (globalmap.addNewUserToDB(username, password));
	}
}

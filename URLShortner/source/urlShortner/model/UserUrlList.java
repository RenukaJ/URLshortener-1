package model;


import java.util.Hashtable;
import java.util.Map;

public class UserUrlList {
	
	//This HashMap consists the list of URLs shortened per user
    private  Map<String, String> userUrlList = new Hashtable<String, String>();
    
    public UserUrlList(String shortUrl, String longUrl){
    	this.userUrlList.put(shortUrl, longUrl);
    }
    
    public void addNewUserListValue(String shortUrl, String longUrl){
    	this.userUrlList.put(shortUrl, longUrl);
    	
    }
    
    public Map<String, String> getUserUrlList(){
    	return this.userUrlList;
    }
}

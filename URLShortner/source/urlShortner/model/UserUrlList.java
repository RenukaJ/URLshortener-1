package model;


import java.util.Hashtable;
import java.util.Iterator;
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
    
    public boolean deleteUserListValue(String urlToRemove){
    	
    	for(Iterator<Map.Entry<String, String>> it = userUrlList.entrySet().iterator(); it.hasNext(); ) {
    	      Map.Entry<String, String> entry = it.next();
    	      if(entry.getKey().equals(urlToRemove)) {
    	        it.remove();
    	        return true;
    	      }
    	    }
    	return false;	
    }
    
    public Map<String, String> getUserUrlList(){
    	return this.userUrlList;
    }
}

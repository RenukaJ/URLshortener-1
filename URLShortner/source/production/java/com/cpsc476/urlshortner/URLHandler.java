package com.cpsc476.urlshortner;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class URLHandler {
	
	
    protected Map<String, String> urlList = new Hashtable<>();
    protected Map<String, Integer> urlCount = new Hashtable<>();
    public URLHandler(String url, String shortenedurl){
    	urlList.put(url, shortenedurl);
    	urlCount.put(url, 0);
    }
 
    

   
}

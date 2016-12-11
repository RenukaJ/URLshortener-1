package com.urlshortener.model.dto;

public class UrlMappingList {
	private Integer urlID;
	private String shortUrl;
	private String longUrl;
	private int visitCount;
	/*
	 * Setter Methods
	 */
	public void setUrlId(Integer urlID){
		this.urlID = urlID;
	}
	
	public void setShortUrl(String shortUrl){
		this.shortUrl = shortUrl;
	}
	
	public void setLongUrl(String longUrl){
		this.longUrl = longUrl;
	}
	public void setVisitCount(int count){
		this.visitCount = count;		
		 
	}
	
	/*
	 * Getter Methods
	 */
	
	public Integer getUrlId(){
		return this.urlID;
	}
	
	public String getShortUrl(){
		return this.shortUrl;
	}
	
	public String getLongUrl(){
		return this.longUrl;
	}
	public int getVisitCount(){
		return this.visitCount;
	}
	
	
}

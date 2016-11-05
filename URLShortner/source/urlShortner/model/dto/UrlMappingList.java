package model.dto;

public class UrlMappingList {
	private Integer urlID;
	private String shortUrl;
	private String longUrl;
	
	/*
	 * Setter Methods
	 */
	public void setUrlId(Integer urlID){
		this.urlID = urlID;
	}
	
	public void setShortUrl(String shortUrl){
		this.shortUrl = shortUrl;
	}
	
	public void setlongUrl(String longUrl){
		this.longUrl = longUrl;
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
	
	
}

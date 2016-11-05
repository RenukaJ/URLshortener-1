package model.dto;

public class UrlCountDto {
	private Integer id; //unclear - will depend on DB design what this ID stands for.
	private String shortUrl;
	private Integer urlVisitCount;
	
	/*
	 * Setter Methods
	 */
	
	/*
	 * Todo: Add Getter and setter for the ID filed
	 */
	
	public void setShortUrl(String shortUrl){
		this.shortUrl = shortUrl;
	}
	
	public void setUrlVisitCount(Integer urlVisitCount){
		this.urlVisitCount = urlVisitCount;
	}
	
	/*
	 * Getter Methods
	 */
	
	public String getShortUrl(){
		return this.shortUrl;
	}
	
	public Integer getUrlVisitCount()
	{
		return this.urlVisitCount;
	}	
}

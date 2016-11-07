package model.dto;

public class UserUrl {
	private Integer urlid; //unclear about this ID - WIll depend on DB Structure
	private String shortUrl;
	private String longUrl;
	
	/*
	 * Setter Methods
	 */
	/*
	 * Add and setter methods for ID field
	 */
	
	public void setShortUrl(String shortUrl){
		this.shortUrl = shortUrl;
	}
	
	public void setLongUrl(String longUrl){
		this.longUrl = longUrl;
	}
	
	/*
	 * Getter Methods
	 */
	
	public String getShortUrl(){
		return this.shortUrl;
	}
	
	public String getLongUrl(){
		return this.longUrl;
	}
}

package model.dto;

public class GlobalURLBean {

	private String shortUrl;
	private String longUrl;
	private Integer visitCount;
	
	
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	
		
}

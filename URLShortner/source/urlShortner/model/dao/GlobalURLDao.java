package model.dao;
import model.dto.*;

public interface GlobalURLDao {
	public String addNewValueToGlobalURLList(String longUrl);
	public UrlMappingList getshortURL(String longUrl);
	public GlobalURLBean getVisitCountList(String shortUrl);
	public void addURLVisitCount();
	public String shortenUrl(String longUrl);
	
}

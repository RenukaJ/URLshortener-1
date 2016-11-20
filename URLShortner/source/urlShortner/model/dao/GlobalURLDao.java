package model.dao;
import model.dto.*;

public interface GlobalURLDao {
	public String addNewValueToGlobalURLList(String longUrl);
	public UrlMappingList getshortURL(String longUrl);
	public Boolean shortUrlexists(String shortUrl);
	public String getLongURL(String shortUrl);
	public void getVisitCountList();
	public void addURLVisitCount();
	public String shortenUrl(String longUrl);
}

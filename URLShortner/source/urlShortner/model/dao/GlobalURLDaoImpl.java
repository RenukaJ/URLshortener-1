package model.dao;

import java.util.UUID;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import model.dto.User;
import model.mapper.UserMapper;
import model.dto.UrlMappingList;
import model.mapper.ShortUrlMapper;
import model.dto.*;


public class GlobalURLDaoImpl implements GlobalURLDao{
	private BasicDataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;


	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	
	public String addNewValueToGlobalURLList(String longUrl){
		/*
		 * 1. Process Method for getting new shortURL
		 * 2. Add to global list
		 * 	  Format:
		 * 	  UrlID, shortUrl, longUrl, visitCOunt
		 */
		
		String shortUrl = shortenUrl(longUrl);
		String SQL = "insert into GlobalUrlDB (shortUrl, longUrl, visitCount) values (?, ?, ?)";
		Object[] params = new Object[] { shortUrl, longUrl, 0 };
		try{
			jdbcTemplateObject.update( SQL, params);
			return shortUrl;
		}
		catch(Exception e){
			System.out.println("Exception occured while user tried to shorten URL");
			/*Put Stack trace into logger file*/
			System.out.println(e);
			return null;
		}
	}
	public UrlMappingList getshortURL(String longUrl){
		String SQL = "select shortUrl from GlobalUrlDB where longUrl =  (?)";
		Object[] params = new Object[] { longUrl };
		ShortUrlMapper mapper = new ShortUrlMapper();
		
		try{
			UrlMappingList urlList = this.jdbcTemplateObject.queryForObject( SQL, params, mapper);
			return urlList;
			
		}
		catch(EmptyResultDataAccessException e){
			System.out.println("Shortened URL does not exists for the entered Long URL");
			return null;
		}
		catch(Exception e){
			System.out.println("Some other Exception");
			return null;
		}
	
		
	}
	public void getVisitCountList(){
		
	}
	public void addURLVisitCount(){
		
	}
	public String shortenUrl(String longUrl){
		/*
		 * 1. convert longUrl into 36 bit hash value
		 * 2. Take Max bits
		 * 3. Convert to String with base 36 encoding
		 * 4. Return value
		 */
		String encodedUrl = "";
		Integer hashKey = (int) UUID.nameUUIDFromBytes(longUrl.getBytes()).getMostSignificantBits();
		encodedUrl = Integer.toString(hashKey, 36);
		return "http://localhost:8080/URLShortner/short/" +encodedUrl;
	}
}

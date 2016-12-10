package com.urlshortener.model.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.urlshortener.model.dto.UserUrl;

public class UserUrlMapper implements RowMapper<UserUrl>{
	public UserUrl mapRow(ResultSet rs, int rowNum) throws SQLException {
		  UserUrl userurl = new UserUrl();
		  
		  
		  try{
			  userurl.setShortUrl(rs.getString("shortUrl"));
	      }
	      catch(Exception e){
	    	  System.out.println("Did not get shortURL for User");
	      }
	      try{
	    	  userurl.setLongUrl(rs.getString("longUrl"));
	      }
	      catch(Exception e){
	    	  System.out.println("Did not get longURL for User");
	      }

	      return userurl;
	   }
}


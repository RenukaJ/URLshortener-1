package com.urlshortener.model.mapper;
import com.urlshortener.model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	      User user = new User();
	      Integer userid = new Integer(rs.getInt("userid"));
	      try{
	    	  user.setID(rs.getInt("userid"));
	      }
	      catch(Exception e){
	    	  System.out.println("Did not get userid");
	      }
	      try{
	    	  user.setPassword(rs.getString("password"));
	      }
	      catch(Exception e){
	    	  System.out.println("Did not get password");
	      }
	    	  
	      
	      return user;
	   }
}

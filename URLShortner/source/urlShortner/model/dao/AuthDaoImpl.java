package model.dao;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import model.dto.UserDto;

public class AuthDaoImpl implements AuthDao{

	private BasicDataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	
	 public void setDataSource(BasicDataSource dataSource) {
		 System.out.println("in sertData SOURCE");
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	 
	 
	public boolean loginUser(String username, String password){
		System.out.println("In login");
		String SQL = "select password from Users where (username) = (?)";
		try{
			//UserDto pass = jdbcTemplateObject.query( SQL, (ResultSetExtractor<T>) new UserDto());
			//System.out.println(pass);
		    return true;
		}
		catch(Exception e){
			System.out.println("Username Not found");
			return false;
		}
		
	      
	}
	

	
	public boolean signupUsr(String username, String password){
		String SQL = "insert into Users (username, password) values (?, ?)";
	      try{
	    	  jdbcTemplateObject.update( SQL, username, password);
		      System.out.println("Created Record Name = " + username + " Age = " + password);
		      return true;
	      }
	      catch(DuplicateKeyException e){
	    	  System.out.println("Duplicate Username");
	    	  return false;
	      }
	      catch(Exception e){
	    	  System.out.println("Some other Exception");
	    	  return false;
	      }
	      
	      
	      
	}
}

/*
 public boolean validateUser(String username, String password){
		if(username == null || password == null ||
				!userDatabase.containsKey(username) ||
				!password.equals(userDatabase.get(username)))
		{
			return false;
		}
		else
		{
			return true;
		}

	}

	public boolean addNewUserToDB(String username, String password){
		if(username == null || password == null ||
				userDatabase.containsKey(username))
		{
			return false;
		}
		else
		{
			userDatabase.put(username, password);
			return true;
		}
	}

*/

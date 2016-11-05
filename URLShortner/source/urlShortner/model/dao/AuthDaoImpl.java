package model.dao;

import java.util.Map;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;


public class AuthDaoImpl implements AuthDao{

	private BasicDataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;


	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}


	public boolean loginUser(String username, String password){
		System.out.println("In login");
		String SQL = "select password from Users where username = (?)";
		try{
			Map<String, Object> pass = jdbcTemplateObject.queryForMap( SQL, username);
			String dbpassword = (String) pass.get("PASSWORD");
			if(password.equals(dbpassword)){
				System.out.println("in true");
				return true;
			}
			else{
				System.out.println("in false");
				return false;
			}		
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


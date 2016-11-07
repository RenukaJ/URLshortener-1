package model.dao;
import model.mapper.*;
import java.util.Map;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import model.dto.*;

public class AuthDaoImpl implements AuthDao{

	private BasicDataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public User loginUser(String username, String password){
		String SQL = "select userid, password from Users where username = (?)";
		Object[] params = new Object[] { username };
		UserMapper mapper = new UserMapper();
		
		try{
			User user = this.jdbcTemplateObject.queryForObject(SQL, params, mapper);
			return user;
		}
		catch(EmptyResultDataAccessException e){
			System.out.println("Invalid username Login attempt");
			return null;
		}
		catch(Exception e){
			System.out.println("Exception occured while user trried to login");
			/*Put Stack trace into logger file */
			return null;
		}
	}



	public boolean signupUsr(String username, String password){
		String SQL = "insert into Users (username, password) values (?, ?)";
		Object[] params = new Object[] { username, password };
		try{
			jdbcTemplateObject.update( SQL, params);
			System.out.println("Created Record Name = " + username + " Age = " + password);
			return true;
		}
		catch(DuplicateKeyException e){
			System.out.println("Duplicate Username Singup Attempt");
			return false;
		}
		catch(Exception e){
			System.out.println("Exception occured while user tried to signup");
			/*Put Stack trace into logger file*/
			return false;
		}
	
	}
}


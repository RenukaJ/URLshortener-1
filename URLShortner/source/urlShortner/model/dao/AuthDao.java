package model.dao;
import model.dto.*;

import org.apache.commons.dbcp2.BasicDataSource;

public interface AuthDao {
	public User loginUser(String username, String password);
	public boolean signupUsr(String username, String password);
}

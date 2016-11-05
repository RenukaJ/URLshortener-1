package model.dao;

import org.apache.commons.dbcp2.BasicDataSource;

public interface AuthDao {
	public void setDataSource(BasicDataSource basicDataSource);
	public boolean loginUser(String username, String password);
	public boolean signupUsr(String username, String password);
}

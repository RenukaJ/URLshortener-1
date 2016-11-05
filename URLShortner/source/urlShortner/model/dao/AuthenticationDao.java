package model.dao;

public interface AuthenticationDao {
	public boolean loginUser(String username, String password);
	public boolean signupUsr(String username, String password);
}

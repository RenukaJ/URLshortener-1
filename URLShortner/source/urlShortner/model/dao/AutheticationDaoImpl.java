package model.dao;

public class AutheticationDaoImpl implements AuthenticationDao{

	public boolean loginUser(String username, String password){
		return false;
	}
	public boolean signupUsr(String username, String password){
		return false;
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

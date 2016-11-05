package model.dto;

public class UserDto {
	private Integer userID;
	private String username;
	private String password;
	
	/*
	 * Setter methods
	 */
	public void setID(Integer userID){
		this.userID = userID;
	}
	
	public void setUserName(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	/*
	 * Getter Methods
	 */
	public Integer getID(){
		return this.userID;
	}
	
	public String getUserName(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
}

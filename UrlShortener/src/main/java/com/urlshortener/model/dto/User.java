package com.urlshortener.model.dto;

public class User{
	private Integer userid;
	private String username;
	private String password;
	
	/*
	 * Setter methods
	 */

	
	public void setID(Integer userid){
		this.userid = userid;
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
		return this.userid;
	}
	
	public String getUserName(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
}

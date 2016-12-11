package com.urlshortener.model.dao;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.urlshortener.model.dto.UserUrl;

@Configuration
public interface UserURLDao {
	/*
	 * This DAO will contain shortened URL List Per user
	 */
	public void addUrlToUserList(String username, String shortUrl, String LongUrl);
	public void deleteUserListValue(String username, String urlToRemove);
	public List<UserUrl> getUserUrlList(String username);
	public boolean checkIfUrlExistsForUser(int userid, String shortUrl);
	public int getUserId(String username);

}

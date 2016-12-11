package com.urlshortener.model.dao;
import com.urlshortener.model.dto.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public interface AuthDao {

	public User loginUser(String username, String password);
	public boolean signupUsr(String username, String password);
}

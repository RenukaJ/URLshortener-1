package com.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.Logger;
import org.springframework.core.SpringVersion;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class UrlShortenerApplication {
	private static Logger logger = Logger.getLogger(UrlShortenerApplication.class.getName());
	public static void main(String[] args) {
		logger.info("SPRING VERSION: " + SpringVersion.getVersion());
		SpringApplication.run(UrlShortenerApplication.class, args);
	}
}

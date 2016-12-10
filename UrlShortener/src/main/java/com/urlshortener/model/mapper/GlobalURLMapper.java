package com.urlshortener.model.mapper;
import com.urlshortener.model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class GlobalURLMapper implements RowMapper<UrlMappingList>{

	public UrlMappingList mapRow(ResultSet rs, int rowNum) throws SQLException {
		UrlMappingList globalUrlDb = new UrlMappingList();
		try{
			globalUrlDb.setVisitCount(rs.getInt("visitCount"));
		}
		catch(Exception e){
			
		}
		try{
			globalUrlDb.setLongUrl(rs.getString("longUrl"));
		}
		catch(Exception e){
			
		} 
	    return globalUrlDb;
	}
}

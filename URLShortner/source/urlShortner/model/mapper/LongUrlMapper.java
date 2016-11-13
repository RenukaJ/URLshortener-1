package model.mapper;

import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LongUrlMapper implements RowMapper<UrlMappingList>{
	public UrlMappingList mapRow(ResultSet rs, int rowNum) throws SQLException {
		  UrlMappingList urlList = new UrlMappingList();
		  urlList.setlongUrl(rs.getString("longUrl"));
	      return urlList;
	   }
}

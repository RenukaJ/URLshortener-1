package model.mapper;
import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ShortUrlMapper implements RowMapper<UrlMappingList>{
	public UrlMappingList mapRow(ResultSet rs, int rowNum) throws SQLException {
		  UrlMappingList urlList = new UrlMappingList();
		  urlList.setShortUrl(rs.getString("shortUrl"));
	      return urlList;
	   }

}

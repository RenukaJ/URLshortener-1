package model.mapper;
import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class GlobalURLMapper implements RowMapper<GlobalURLBean>{

	public GlobalURLBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		GlobalURLBean globalUrlDb = new GlobalURLBean();
		globalUrlDb.setVisitCount(rs.getInt("visitCount"));
	    return globalUrlDb;
	}
}

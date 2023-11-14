package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class GeneratorMaxDataDate implements RowMapper<Date> {

	//--- Constructors --------------------------
	private GeneratorMaxDataDate() {}
	private static GeneratorMaxDataDate instance = new GeneratorMaxDataDate();
	public static GeneratorMaxDataDate getInstance() { return GeneratorMaxDataDate.instance; }
	
	//--- Implemented methods -------------------
	@Override public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getTimestamp(1);
	}

}

package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.GenMetadataVo;

@javax.annotation.Generated(value = "Renovus") public class GenMetadataRowWrapper implements RowMapper<GenMetadataVo> {

	//--- Constructors --------------------------
	private GenMetadataRowWrapper() {}
	private static GenMetadataRowWrapper instance = new GenMetadataRowWrapper();
	public static GenMetadataRowWrapper getInstance() { return GenMetadataRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetMetadataCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetMetadataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GenMetadataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenMetadataVo vo = new GenMetadataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenMetadataVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenMetadataVo.COLUMN_GEN_ID));
		vo.setMetadataCode(this.getResultSetMetadataCode(resultSet, GenMetadataVo.COLUMN_METADATA_CODE));
		vo.setMetadataTitle(this.getResultSetMetadataTitle(resultSet, GenMetadataVo.COLUMN_METADATA_TITLE));
		vo.setMetadataValue(this.getResultSetMetadataValue(resultSet, GenMetadataVo.COLUMN_METADATA_VALUE));
		vo.setMetadataDateAdded(this.getResultSetMetadataDateAdded(resultSet, GenMetadataVo.COLUMN_METADATA_DATE_ADDED));

		return vo;
	}
}


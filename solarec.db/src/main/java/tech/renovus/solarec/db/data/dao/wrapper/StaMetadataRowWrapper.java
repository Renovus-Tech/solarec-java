package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.StaMetadataVo;

@javax.annotation.Generated(value = "Renovus") public class StaMetadataRowWrapper implements RowMapper<StaMetadataVo> {

	//--- Constructors --------------------------
	private StaMetadataRowWrapper() {}
	private static StaMetadataRowWrapper instance = new StaMetadataRowWrapper();
	public static StaMetadataRowWrapper getInstance() { return StaMetadataRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetMetadataName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetMetadataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StaMetadataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StaMetadataVo vo = new StaMetadataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, StaMetadataVo.COLUMN_CLI_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, StaMetadataVo.COLUMN_STA_ID));
		vo.setMetadataName(this.getResultSetMetadataName(resultSet, StaMetadataVo.COLUMN_METADATA_NAME));
		vo.setMetadataTitle(this.getResultSetMetadataTitle(resultSet, StaMetadataVo.COLUMN_METADATA_TITLE));
		vo.setMetadataValue(this.getResultSetMetadataValue(resultSet, StaMetadataVo.COLUMN_METADATA_VALUE));
		vo.setMetadataDateAdded(this.getResultSetMetadataDateAdded(resultSet, StaMetadataVo.COLUMN_METADATA_DATE_ADDED));

		return vo;
	}
}


package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.LocMetadataVo;

public class LocMetadataRowWrapper implements RowMapper<LocMetadataVo> {

	//--- Constructors --------------------------
	private LocMetadataRowWrapper() {}
	private static LocMetadataRowWrapper instance = new LocMetadataRowWrapper();
	public static LocMetadataRowWrapper getInstance() { return LocMetadataRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetMetadataName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetMetadataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocMetadataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocMetadataVo vo = new LocMetadataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocMetadataVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocMetadataVo.COLUMN_LOC_ID));
		vo.setMetadataName(this.getResultSetMetadataName(resultSet, LocMetadataVo.COLUMN_METADATA_NAME));
		vo.setMetadataTitle(this.getResultSetMetadataTitle(resultSet, LocMetadataVo.COLUMN_METADATA_TITLE));
		vo.setMetadataValue(this.getResultSetMetadataValue(resultSet, LocMetadataVo.COLUMN_METADATA_VALUE));
		vo.setMetadataDateAdded(this.getResultSetMetadataDateAdded(resultSet, LocMetadataVo.COLUMN_METADATA_DATE_ADDED));

		return vo;
	}
}


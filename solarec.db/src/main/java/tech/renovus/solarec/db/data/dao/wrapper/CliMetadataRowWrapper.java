package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliMetadataVo;

@javax.annotation.Generated(value = "Renovus") public class CliMetadataRowWrapper implements RowMapper<CliMetadataVo> {

	//--- Constructors --------------------------
	private CliMetadataRowWrapper() {}
	private static CliMetadataRowWrapper instance = new CliMetadataRowWrapper();
	public static CliMetadataRowWrapper getInstance() { return CliMetadataRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetMetadataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetMetadataName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetMetadataValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliMetadataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliMetadataVo vo = new CliMetadataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliMetadataVo.COLUMN_CLI_ID));
		vo.setMetadataDateAdded(this.getResultSetMetadataDateAdded(resultSet, CliMetadataVo.COLUMN_METADATA_DATE_ADDED));
		vo.setMetadataName(this.getResultSetMetadataName(resultSet, CliMetadataVo.COLUMN_METADATA_NAME));
		vo.setMetadataTitle(this.getResultSetMetadataTitle(resultSet, CliMetadataVo.COLUMN_METADATA_TITLE));
		vo.setMetadataValue(this.getResultSetMetadataValue(resultSet, CliMetadataVo.COLUMN_METADATA_VALUE));

		return vo;
	}
}
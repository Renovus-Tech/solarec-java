package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.RepTypeVo;

@javax.annotation.Generated(value = "Renovus") public class RepTypeRowWrapper implements RowMapper<RepTypeVo> {

	//--- Constructors --------------------------
	private RepTypeRowWrapper() {}
	private static RepTypeRowWrapper instance = new RepTypeRowWrapper();
	public static RepTypeRowWrapper getInstance() { return RepTypeRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetRepTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetRepTypeName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetRepTypeTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetRepFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetRepExecutable(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetRepOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public RepTypeVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		RepTypeVo vo = new RepTypeVo();

		vo.setRepTypeId(this.getResultSetRepTypeId(resultSet, RepTypeVo.COLUMN_REP_TYPE_ID));
		vo.setRepTypeName(this.getResultSetRepTypeName(resultSet, RepTypeVo.COLUMN_REP_TYPE_NAME));
		vo.setRepTypeTitle(this.getResultSetRepTypeTitle(resultSet, RepTypeVo.COLUMN_REP_TYPE_TITLE));
		vo.setRepFlags(this.getResultSetRepFlags(resultSet, RepTypeVo.COLUMN_REP_FLAGS));
		vo.setRepExecutable(this.getResultSetRepExecutable(resultSet, RepTypeVo.COLUMN_REP_EXECUTABLE));
		vo.setRepOrder(this.getResultSetRepOrder(resultSet, RepTypeVo.COLUMN_REP_ORDER));

		return vo;
	}
}


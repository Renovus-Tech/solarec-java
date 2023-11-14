package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.StatTypeVo;

public class StatTypeRowWrapper implements RowMapper<StatTypeVo> {

	//--- Constructors --------------------------
	private StatTypeRowWrapper() {}
	private static StatTypeRowWrapper instance = new StatTypeRowWrapper();
	public static StatTypeRowWrapper getInstance() { return StatTypeRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetStatTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetStatTypeName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStatTypeUnit(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStatTypeDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public StatTypeVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StatTypeVo vo = new StatTypeVo();

		vo.setStatTypeId(this.getResultSetStatTypeId(resultSet, StatTypeVo.COLUMN_STAT_TYPE_ID));
		vo.setStatTypeName(this.getResultSetStatTypeName(resultSet, StatTypeVo.COLUMN_STAT_TYPE_NAME));
		vo.setStatTypeUnit(this.getResultSetStatTypeUnit(resultSet, StatTypeVo.COLUMN_STAT_TYPE_UNIT));
		vo.setStatTypeDescription(this.getResultSetStatTypeDescription(resultSet, StatTypeVo.COLUMN_STAT_TYPE_DESCRIPTION));

		return vo;
	}
}


package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.StatDefSourceVo;

public class StatDefSourceRowWrapper implements RowMapper<StatDefSourceVo> {

	//--- Constructors --------------------------
	private StatDefSourceRowWrapper() {}
	private static StatDefSourceRowWrapper instance = new StatDefSourceRowWrapper();
	public static StatDefSourceRowWrapper getInstance() { return StatDefSourceRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetStatDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StatDefSourceVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StatDefSourceVo vo = new StatDefSourceVo();

		vo.setStatDefId(this.getResultSetStatDefId(resultSet, StatDefSourceVo.COLUMN_STAT_DEF_ID));
		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, StatDefSourceVo.COLUMN_DATA_TYPE_ID));

		return vo;
	}
}


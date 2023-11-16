package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.StatDefResultVo;

public class StatDefResultRowWrapper implements RowMapper<StatDefResultVo> {

	//--- Constructors --------------------------
	private StatDefResultRowWrapper() {}
	private static StatDefResultRowWrapper instance = new StatDefResultRowWrapper();
	public static StatDefResultRowWrapper getInstance() { return StatDefResultRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetStatDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StatDefResultVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StatDefResultVo vo = new StatDefResultVo();

		vo.setStatDefId(this.getResultSetStatDefId(resultSet, StatDefResultVo.COLUMN_STAT_DEF_ID));
		vo.setStatTypeId(this.getResultSetStatTypeId(resultSet, StatDefResultVo.COLUMN_STAT_TYPE_ID));

		return vo;
	}
}


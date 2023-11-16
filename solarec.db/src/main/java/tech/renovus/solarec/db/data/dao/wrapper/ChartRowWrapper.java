package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.ChartVo;

public class ChartRowWrapper implements RowMapper<ChartVo> {

	//--- Constructors --------------------------
	private ChartRowWrapper() {}
	private static ChartRowWrapper instance = new ChartRowWrapper();
	public static ChartRowWrapper getInstance() { return ChartRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetChrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetChrName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetChrTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetChrDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetChrFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public ChartVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ChartVo vo = new ChartVo();

		vo.setChrId(this.getResultSetChrId(resultSet, ChartVo.COLUMN_CHR_ID));
		vo.setChrName(this.getResultSetChrName(resultSet, ChartVo.COLUMN_CHR_NAME));
		vo.setChrTitle(this.getResultSetChrTitle(resultSet, ChartVo.COLUMN_CHR_TITLE));
		vo.setChrDescription(this.getResultSetChrDescription(resultSet, ChartVo.COLUMN_CHR_DESCRIPTION));
		vo.setChrFlags(this.getResultSetChrFlags(resultSet, ChartVo.COLUMN_CHR_FLAGS));

		return vo;
	}
}


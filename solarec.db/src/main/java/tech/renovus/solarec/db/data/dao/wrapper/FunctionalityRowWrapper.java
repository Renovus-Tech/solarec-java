package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.FunctionalityVo;

@javax.annotation.Generated(value = "Renovus") public class FunctionalityRowWrapper implements RowMapper<FunctionalityVo> {

	//--- Constructors --------------------------
	private FunctionalityRowWrapper() {}
	private static FunctionalityRowWrapper instance = new FunctionalityRowWrapper();
	public static FunctionalityRowWrapper getInstance() { return FunctionalityRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetFncId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetFncName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetFncTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetFncDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetFncFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetFncUrl(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetFncOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public FunctionalityVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		FunctionalityVo vo = new FunctionalityVo();

		vo.setFncId(this.getResultSetFncId(resultSet, FunctionalityVo.COLUMN_FNC_ID));
		vo.setFncName(this.getResultSetFncName(resultSet, FunctionalityVo.COLUMN_FNC_NAME));
		vo.setFncTitle(this.getResultSetFncTitle(resultSet, FunctionalityVo.COLUMN_FNC_TITLE));
		vo.setFncDescription(this.getResultSetFncDescription(resultSet, FunctionalityVo.COLUMN_FNC_DESCRIPTION));
		vo.setFncFlags(this.getResultSetFncFlags(resultSet, FunctionalityVo.COLUMN_FNC_FLAGS));
		vo.setFncUrl(this.getResultSetFncUrl(resultSet, FunctionalityVo.COLUMN_FNC_URL));
		vo.setFncOrder(this.getResultSetFncOrder(resultSet, FunctionalityVo.COLUMN_FNC_ORDER));

		return vo;
	}
}


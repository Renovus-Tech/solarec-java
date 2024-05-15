package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocSdgVo;

public class LocSdgRowWrapper implements RowMapper<LocSdgVo> {

	//--- Constructors --------------------------
	protected LocSdgRowWrapper() {}
	private static LocSdgRowWrapper instance = new LocSdgRowWrapper();
	public static LocSdgRowWrapper getInstance() { return LocSdgRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetSdgId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetLocSdgDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public LocSdgVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocSdgVo vo = new LocSdgVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocSdgVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocSdgVo.COLUMN_LOC_ID));
		vo.setSdgId(this.getResultSetSdgId(resultSet, LocSdgVo.COLUMN_SDG_ID));
		vo.setLocSdgDescription(this.getResultSetLocSdgDescription(resultSet, LocSdgVo.COLUMN_LOC_SDG_DESCRIPTION));

		return vo;
	}
}
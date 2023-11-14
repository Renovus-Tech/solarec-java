package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.WeaCallVo;

public class WeaCallRowWrapper implements RowMapper<WeaCallVo> {

	//--- Constructors --------------------------
	private WeaCallRowWrapper() {}
	private static WeaCallRowWrapper instance = new WeaCallRowWrapper();
	public static WeaCallRowWrapper getInstance() { return WeaCallRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetWeaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetWeaCallId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetWeaCallDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetWeaCallResonseStatus(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetWeaCallResponse(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public WeaCallVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		WeaCallVo vo = new WeaCallVo();

		vo.setCliId(this.getResultSetCliId(resultSet, WeaCallVo.COLUMN_CLI_ID));
		vo.setWeaId(this.getResultSetWeaId(resultSet, WeaCallVo.COLUMN_WEA_ID));
		vo.setWeaCallId(this.getResultSetWeaCallId(resultSet, WeaCallVo.COLUMN_WEA_CALL_ID));
		vo.setWeaCallDate(this.getResultSetWeaCallDate(resultSet, WeaCallVo.COLUMN_WEA_CALL_DATE));
		vo.setWeaCallResonseStatus(this.getResultSetWeaCallResonseStatus(resultSet, WeaCallVo.COLUMN_WEA_CALL_RESONSE_STATUS));
		vo.setWeaCallResponse(this.getResultSetWeaCallResponse(resultSet, WeaCallVo.COLUMN_WEA_CALL_RESPONSE));

		return vo;
	}
}


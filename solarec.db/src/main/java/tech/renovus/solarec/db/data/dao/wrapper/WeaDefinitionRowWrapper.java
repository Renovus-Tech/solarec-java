package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.WeaDefinitionVo;

@javax.annotation.Generated(value = "Renovus") public class WeaDefinitionRowWrapper implements RowMapper<WeaDefinitionVo> {

	//--- Constructors --------------------------
	private WeaDefinitionRowWrapper() {}
	private static WeaDefinitionRowWrapper instance = new WeaDefinitionRowWrapper();
	public static WeaDefinitionRowWrapper getInstance() { return WeaDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetWeaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetWeaName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetWeaDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Double getResultSetWeaCoordLat(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetWeaCoordLng(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Integer getResultSetWeaCheckType(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetWeaCheckFrequency(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetWeaFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public WeaDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		WeaDefinitionVo vo = new WeaDefinitionVo();

		vo.setCliId(this.getResultSetCliId(resultSet, WeaDefinitionVo.COLUMN_CLI_ID));
		vo.setWeaId(this.getResultSetWeaId(resultSet, WeaDefinitionVo.COLUMN_WEA_ID));
		vo.setWeaName(this.getResultSetWeaName(resultSet, WeaDefinitionVo.COLUMN_WEA_NAME));
		vo.setWeaDescription(this.getResultSetWeaDescription(resultSet, WeaDefinitionVo.COLUMN_WEA_DESCRIPTION));
		vo.setWeaCoordLat(this.getResultSetWeaCoordLat(resultSet, WeaDefinitionVo.COLUMN_WEA_COORD_LAT));
		vo.setWeaCoordLng(this.getResultSetWeaCoordLng(resultSet, WeaDefinitionVo.COLUMN_WEA_COORD_LNG));
		vo.setWeaCheckType(this.getResultSetWeaCheckType(resultSet, WeaDefinitionVo.COLUMN_WEA_CHECK_TYPE));
		vo.setWeaCheckFrequency(this.getResultSetWeaCheckFrequency(resultSet, WeaDefinitionVo.COLUMN_WEA_CHECK_FREQUENCY));
		vo.setWeaFlags(this.getResultSetWeaFlags(resultSet, WeaDefinitionVo.COLUMN_WEA_FLAGS));

		return vo;
	}
}


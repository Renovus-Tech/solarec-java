package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.GenPowerVo;

public class GenPowerRowWrapper implements RowMapper<GenPowerVo> {

	//--- Constructors --------------------------
	private GenPowerRowWrapper() {}
	private static GenPowerRowWrapper instance = new GenPowerRowWrapper();
	public static GenPowerRowWrapper getInstance() { return GenPowerRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetPwrWindSpeed(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetPwrAirDensity(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetGenPower(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GenPowerVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenPowerVo vo = new GenPowerVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenPowerVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenPowerVo.COLUMN_GEN_ID));
		vo.setPwrWindSpeed(this.getResultSetPwrWindSpeed(resultSet, GenPowerVo.COLUMN_PWR_WIND_SPEED));
		vo.setPwrAirDensity(this.getResultSetPwrAirDensity(resultSet, GenPowerVo.COLUMN_PWR_AIR_DENSITY));
		vo.setGenPower(this.getResultSetGenPower(resultSet, GenPowerVo.COLUMN_GEN_POWER));

		return vo;
	}
}


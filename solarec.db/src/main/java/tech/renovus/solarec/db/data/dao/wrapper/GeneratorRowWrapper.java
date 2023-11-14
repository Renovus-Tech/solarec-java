package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.GeneratorVo;

public class GeneratorRowWrapper implements RowMapper<GeneratorVo> {

	//--- Constructors --------------------------
	private GeneratorRowWrapper() {}
	private static GeneratorRowWrapper instance = new GeneratorRowWrapper();
	public static GeneratorRowWrapper getInstance() { return GeneratorRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetGenName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetGenDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Double getResultSetGenCoordLat(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetGenCoordLng(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetGenBrand(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetGenModel(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetGenSerialNum(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Double getResultSetGenRatePower(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetGenFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetGenCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Date getResultSetGenDataDateMax(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Date getResultSetGenDataDateMin(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GeneratorVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GeneratorVo vo = new GeneratorVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GeneratorVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GeneratorVo.COLUMN_GEN_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, GeneratorVo.COLUMN_DATA_DEF_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, GeneratorVo.COLUMN_LOC_ID));
		vo.setGenName(this.getResultSetGenName(resultSet, GeneratorVo.COLUMN_GEN_NAME));
		vo.setGenDescription(this.getResultSetGenDescription(resultSet, GeneratorVo.COLUMN_GEN_DESCRIPTION));
		vo.setGenCoordLat(this.getResultSetGenCoordLat(resultSet, GeneratorVo.COLUMN_GEN_COORD_LAT));
		vo.setGenCoordLng(this.getResultSetGenCoordLng(resultSet, GeneratorVo.COLUMN_GEN_COORD_LNG));
		vo.setGenBrand(this.getResultSetGenBrand(resultSet, GeneratorVo.COLUMN_GEN_BRAND));
		vo.setGenModel(this.getResultSetGenModel(resultSet, GeneratorVo.COLUMN_GEN_MODEL));
		vo.setGenSerialNum(this.getResultSetGenSerialNum(resultSet, GeneratorVo.COLUMN_GEN_SERIAL_NUM));
		vo.setGenRatePower(this.getResultSetGenRatePower(resultSet, GeneratorVo.COLUMN_GEN_RATE_POWER));
		vo.setGenFlags(this.getResultSetGenFlags(resultSet, GeneratorVo.COLUMN_GEN_FLAGS));
		vo.setGenCode(this.getResultSetGenCode(resultSet, GeneratorVo.COLUMN_GEN_CODE));
		vo.setGenDataDateMax(this.getResultSetGenDataDateMax(resultSet, GeneratorVo.COLUMN_GEN_DATA_DATE_MAX));
		vo.setGenDataDateMin(this.getResultSetGenDataDateMin(resultSet, GeneratorVo.COLUMN_GEN_DATA_DATE_MIN));

		return vo;
	}
}


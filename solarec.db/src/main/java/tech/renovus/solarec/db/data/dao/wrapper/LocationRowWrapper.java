package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocationVo;

public class LocationRowWrapper implements RowMapper<LocationVo> {

	//--- Constructors --------------------------
	private LocationRowWrapper() {}
	private static LocationRowWrapper instance = new LocationRowWrapper();
	public static LocationRowWrapper getInstance() { return LocationRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCtrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetLocOutputCapacity(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocOutputTotalCapacity(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocReferenceDensity(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetLocDataDateMax(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetLocDataDateMin(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetLocDemoDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetLocCoordLat(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocCoordLng(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetLocName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocAddress(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocState(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocType(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocGmt(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public LocationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocationVo vo = new LocationVo();

		vo.setCtrId(this.getResultSetCtrId(resultSet, LocationVo.COLUMN_CTR_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocationVo.COLUMN_LOC_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, LocationVo.COLUMN_DATA_DEF_ID));
		vo.setLocOutputCapacity(this.getResultSetLocOutputCapacity(resultSet, LocationVo.COLUMN_LOC_OUTPUT_CAPACITY));
		vo.setLocOutputTotalCapacity(this.getResultSetLocOutputTotalCapacity(resultSet, LocationVo.COLUMN_LOC_OUTPUT_TOTAL_CAPACITY));
		vo.setLocReferenceDensity(this.getResultSetLocReferenceDensity(resultSet, LocationVo.COLUMN_LOC_REFERENCE_DENSITY));
		vo.setLocDataDateMax(this.getResultSetLocDataDateMax(resultSet, LocationVo.COLUMN_LOC_DATA_DATE_MAX));
		vo.setLocDataDateMin(this.getResultSetLocDataDateMin(resultSet, LocationVo.COLUMN_LOC_DATA_DATE_MIN));
		vo.setLocDemoDate(this.getResultSetLocDemoDate(resultSet, LocationVo.COLUMN_LOC_DEMO_DATE));
		vo.setCliId(this.getResultSetCliId(resultSet, LocationVo.COLUMN_CLI_ID));
		vo.setLocCoordLat(this.getResultSetLocCoordLat(resultSet, LocationVo.COLUMN_LOC_COORD_LAT));
		vo.setLocCoordLng(this.getResultSetLocCoordLng(resultSet, LocationVo.COLUMN_LOC_COORD_LNG));
		vo.setLocName(this.getResultSetLocName(resultSet, LocationVo.COLUMN_LOC_NAME));
		vo.setLocAddress(this.getResultSetLocAddress(resultSet, LocationVo.COLUMN_LOC_ADDRESS));
		vo.setLocState(this.getResultSetLocState(resultSet, LocationVo.COLUMN_LOC_STATE));
		vo.setLocType(this.getResultSetLocType(resultSet, LocationVo.COLUMN_LOC_TYPE));
		vo.setLocFlags(this.getResultSetLocFlags(resultSet, LocationVo.COLUMN_LOC_FLAGS));
		vo.setLocCode(this.getResultSetLocCode(resultSet, LocationVo.COLUMN_LOC_CODE));
		vo.setLocGmt(this.getResultSetLocGmt(resultSet, LocationVo.COLUMN_LOC_GMT));

		return vo;
	}
}
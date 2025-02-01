package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.StationVo;

public class StationRowWrapper implements RowMapper<StationVo> {

	//--- Constructors --------------------------
	private StationRowWrapper() {}
	private static StationRowWrapper instance = new StationRowWrapper();
	public static StationRowWrapper getInstance() { return StationRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetFrqId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetStaCoordLat(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetStaCoordLng(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStaDataDateMax(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetStaDataDateMin(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetStaName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStaDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStaCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStaCertProvData(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStaFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public StationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StationVo vo = new StationVo();

		vo.setFrqId(this.getResultSetFrqId(resultSet, StationVo.COLUMN_FRQ_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, StationVo.COLUMN_STA_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, StationVo.COLUMN_DATA_DEF_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, StationVo.COLUMN_LOC_ID));
		vo.setStaCoordLat(this.getResultSetStaCoordLat(resultSet, StationVo.COLUMN_STA_COORD_LAT));
		vo.setStaCoordLng(this.getResultSetStaCoordLng(resultSet, StationVo.COLUMN_STA_COORD_LNG));
		vo.setStaDataDateMax(this.getResultSetStaDataDateMax(resultSet, StationVo.COLUMN_STA_DATA_DATE_MAX));
		vo.setStaDataDateMin(this.getResultSetStaDataDateMin(resultSet, StationVo.COLUMN_STA_DATA_DATE_MIN));
		vo.setCliId(this.getResultSetCliId(resultSet, StationVo.COLUMN_CLI_ID));
		vo.setStaName(this.getResultSetStaName(resultSet, StationVo.COLUMN_STA_NAME));
		vo.setStaDescription(this.getResultSetStaDescription(resultSet, StationVo.COLUMN_STA_DESCRIPTION));
		vo.setStaCode(this.getResultSetStaCode(resultSet, StationVo.COLUMN_STA_CODE));
		vo.setStaCertProvData(this.getResultSetStaCertProvData(resultSet, StationVo.COLUMN_STA_CERT_PROV_DATA));
		vo.setStaFlags(this.getResultSetStaFlags(resultSet, StationVo.COLUMN_STA_FLAGS));

		return vo;
	}
}
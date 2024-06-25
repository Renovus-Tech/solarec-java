package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.GenDataVo;

public class GenDataRowWrapper implements RowMapper<GenDataVo> {

	//--- Constructors --------------------------
	private GenDataRowWrapper() {}
	private static GenDataRowWrapper instance = new GenDataRowWrapper();
	public static GenDataRowWrapper getInstance() { return GenDataRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetDataValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetGenDataCertProvData(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public GenDataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenDataVo vo = new GenDataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenDataVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenDataVo.COLUMN_GEN_ID));
		vo.setDataDate(this.getResultSetDataDate(resultSet, GenDataVo.COLUMN_DATA_DATE));
		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, GenDataVo.COLUMN_DATA_TYPE_ID));
		vo.setDataProId(this.getResultSetDataProId(resultSet, GenDataVo.COLUMN_DATA_PRO_ID));
		vo.setDataValue(this.getResultSetDataValue(resultSet, GenDataVo.COLUMN_DATA_VALUE));
		vo.setDataDateAdded(this.getResultSetDataDateAdded(resultSet, GenDataVo.COLUMN_DATA_DATE_ADDED));
		vo.setGenDataCertProvData(this.getResultSetGenDataCertProvData(resultSet, GenDataVo.COLUMN_GEN_DATA_CERT_PROV_DATA));

		return vo;
	}
}
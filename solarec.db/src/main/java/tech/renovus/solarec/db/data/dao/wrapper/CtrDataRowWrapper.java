package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CtrDataVo;

public class CtrDataRowWrapper implements RowMapper<CtrDataVo> {

	//--- Constructors --------------------------
	private CtrDataRowWrapper() {}
	private static CtrDataRowWrapper instance = new CtrDataRowWrapper();
	public static CtrDataRowWrapper getInstance() { return CtrDataRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCtrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetDataValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Double.valueOf(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public CtrDataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CtrDataVo vo = new CtrDataVo();

		vo.setCtrId(this.getResultSetCtrId(resultSet, CtrDataVo.COLUMN_CTR_ID));
		vo.setDataDate(this.getResultSetDataDate(resultSet, CtrDataVo.COLUMN_DATA_DATE));
		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, CtrDataVo.COLUMN_DATA_TYPE_ID));
		vo.setDataProId(this.getResultSetDataProId(resultSet, CtrDataVo.COLUMN_DATA_PRO_ID));
		vo.setDataValue(this.getResultSetDataValue(resultSet, CtrDataVo.COLUMN_DATA_VALUE));
		vo.setDataDateAdded(this.getResultSetDataDateAdded(resultSet, CtrDataVo.COLUMN_DATA_DATE_ADDED));

		return vo;
	}
}
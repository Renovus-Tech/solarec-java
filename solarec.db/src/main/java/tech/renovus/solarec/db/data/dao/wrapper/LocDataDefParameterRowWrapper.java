package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;

@javax.annotation.Generated(value = "Renovus") public class LocDataDefParameterRowWrapper implements RowMapper<LocDataDefParameterVo> {

	//--- Constructors --------------------------
	protected LocDataDefParameterRowWrapper() {}
	private static LocDataDefParameterRowWrapper instance = new LocDataDefParameterRowWrapper();
	public static LocDataDefParameterRowWrapper getInstance() { return LocDataDefParameterRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefParId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetLocDataDefParValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public LocDataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocDataDefParameterVo vo = new LocDataDefParameterVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocDataDefParameterVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocDataDefParameterVo.COLUMN_LOC_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, LocDataDefParameterVo.COLUMN_DATA_DEF_ID));
		vo.setDataDefParId(this.getResultSetDataDefParId(resultSet, LocDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID));
		vo.setLocDataDefParValue(this.getResultSetLocDataDefParValue(resultSet, LocDataDefParameterVo.COLUMN_LOC_DATA_DEF_PAR_VALUE));

		return vo;
	}
}
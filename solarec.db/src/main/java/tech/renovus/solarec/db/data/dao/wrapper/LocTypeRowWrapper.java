package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocTypeVo;

public class LocTypeRowWrapper implements RowMapper<LocTypeVo> {

	//--- Constructors --------------------------
	private LocTypeRowWrapper() {}
	private static LocTypeRowWrapper instance = new LocTypeRowWrapper();
	public static LocTypeRowWrapper getInstance() { return LocTypeRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetLocTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetLocTypeCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetLocTypeText(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public LocTypeVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocTypeVo vo = new LocTypeVo();

		vo.setLocTypeId(this.getResultSetLocTypeId(resultSet, LocTypeVo.COLUMN_LOC_TYPE_ID));
		vo.setLocTypeCode(this.getResultSetLocTypeCode(resultSet, LocTypeVo.COLUMN_LOC_TYPE_CODE));
		vo.setLocTypeText(this.getResultSetLocTypeText(resultSet, LocTypeVo.COLUMN_LOC_TYPE_TEXT));

		return vo;
	}
}
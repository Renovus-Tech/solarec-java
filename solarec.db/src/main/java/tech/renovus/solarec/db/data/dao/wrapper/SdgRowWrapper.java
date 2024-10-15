package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.SdgVo;

@javax.annotation.Generated(value = "Renovus") public class SdgRowWrapper implements RowMapper<SdgVo> {

	//--- Constructors --------------------------
	private SdgRowWrapper() {}
	private static SdgRowWrapper instance = new SdgRowWrapper();
	public static SdgRowWrapper getInstance() { return SdgRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetSdgId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetSdgCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSdgName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public SdgVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		SdgVo vo = new SdgVo();

		vo.setSdgId(this.getResultSetSdgId(resultSet, SdgVo.COLUMN_SDG_ID));
		vo.setSdgCode(this.getResultSetSdgCode(resultSet, SdgVo.COLUMN_SDG_CODE));
		vo.setSdgName(this.getResultSetSdgName(resultSet, SdgVo.COLUMN_SDG_NAME));

		return vo;
	}
}
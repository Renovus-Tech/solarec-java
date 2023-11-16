package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.ProfileVo;

public class ProfileRowWrapper implements RowMapper<ProfileVo> {

	//--- Constructors --------------------------
	private ProfileRowWrapper() {}
	private static ProfileRowWrapper instance = new ProfileRowWrapper();
	public static ProfileRowWrapper getInstance() { return ProfileRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetPrfId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetPrfName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetPrfDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetPrfFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public ProfileVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ProfileVo vo = new ProfileVo();

		vo.setPrfId(this.getResultSetPrfId(resultSet, ProfileVo.COLUMN_PRF_ID));
		vo.setPrfName(this.getResultSetPrfName(resultSet, ProfileVo.COLUMN_PRF_NAME));
		vo.setPrfDescription(this.getResultSetPrfDescription(resultSet, ProfileVo.COLUMN_PRF_DESCRIPTION));
		vo.setPrfFlags(this.getResultSetPrfFlags(resultSet, ProfileVo.COLUMN_PRF_FLAGS));

		return vo;
	}
}


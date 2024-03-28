package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CountryVo;

public class CountryRowWrapper implements RowMapper<CountryVo> {

	//--- Constructors --------------------------
	private CountryRowWrapper() {}
	private static CountryRowWrapper instance = new CountryRowWrapper();
	public static CountryRowWrapper getInstance() { return CountryRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCtrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCtrDataDateMax(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetCtrDataDateMin(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetCtrCode3(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCtrCode2(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCtrName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCtrNameShow(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CountryVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CountryVo vo = new CountryVo();

		vo.setCtrId(this.getResultSetCtrId(resultSet, CountryVo.COLUMN_CTR_ID));
		vo.setCtrDataDateMax(this.getResultSetCtrDataDateMax(resultSet, CountryVo.COLUMN_CTR_DATA_DATE_MAX));
		vo.setCtrDataDateMin(this.getResultSetCtrDataDateMin(resultSet, CountryVo.COLUMN_CTR_DATA_DATE_MIN));
		vo.setCtrCode3(this.getResultSetCtrCode3(resultSet, CountryVo.COLUMN_CTR_CODE_3));
		vo.setCtrCode2(this.getResultSetCtrCode2(resultSet, CountryVo.COLUMN_CTR_CODE_2));
		vo.setCtrName(this.getResultSetCtrName(resultSet, CountryVo.COLUMN_CTR_NAME));
		vo.setCtrNameShow(this.getResultSetCtrNameShow(resultSet, CountryVo.COLUMN_CTR_NAME_SHOW));

		return vo;
	}
}
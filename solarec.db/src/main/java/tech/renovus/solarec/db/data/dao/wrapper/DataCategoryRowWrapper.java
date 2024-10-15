package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DataCategoryVo;

@javax.annotation.Generated(value = "Renovus") public class DataCategoryRowWrapper implements RowMapper<DataCategoryVo> {

	//--- Constructors --------------------------
	private DataCategoryRowWrapper() {}
	private static DataCategoryRowWrapper instance = new DataCategoryRowWrapper();
	public static DataCategoryRowWrapper getInstance() { return DataCategoryRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetDataCatId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetDataCatName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataCatDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DataCategoryVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataCategoryVo vo = new DataCategoryVo();

		vo.setDataCatId(this.getResultSetDataCatId(resultSet, DataCategoryVo.COLUMN_DATA_CAT_ID));
		vo.setDataCatName(this.getResultSetDataCatName(resultSet, DataCategoryVo.COLUMN_DATA_CAT_NAME));
		vo.setDataCatDescription(this.getResultSetDataCatDescription(resultSet, DataCategoryVo.COLUMN_DATA_CAT_DESCRIPTION));

		return vo;
	}
}
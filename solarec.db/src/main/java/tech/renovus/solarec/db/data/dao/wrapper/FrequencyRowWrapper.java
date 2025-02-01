package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.FrequencyVo;

public class FrequencyRowWrapper implements RowMapper<FrequencyVo> {

	//--- Constructors --------------------------
	private FrequencyRowWrapper() {}
	private static FrequencyRowWrapper instance = new FrequencyRowWrapper();
	public static FrequencyRowWrapper getInstance() { return FrequencyRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetFrqId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetFrqAmount(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetFrqName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetFrqUnit(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetFrqFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public FrequencyVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		FrequencyVo vo = new FrequencyVo();

		vo.setFrqId(this.getResultSetFrqId(resultSet, FrequencyVo.COLUMN_FRQ_ID));
		vo.setFrqAmount(this.getResultSetFrqAmount(resultSet, FrequencyVo.COLUMN_FRQ_AMOUNT));
		vo.setFrqName(this.getResultSetFrqName(resultSet, FrequencyVo.COLUMN_FRQ_NAME));
		vo.setFrqUnit(this.getResultSetFrqUnit(resultSet, FrequencyVo.COLUMN_FRQ_UNIT));
		vo.setFrqFlags(this.getResultSetFrqFlags(resultSet, FrequencyVo.COLUMN_FRQ_FLAGS));

		return vo;
	}
}
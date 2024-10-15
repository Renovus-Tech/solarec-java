package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.PrfFunctionalityVo;

@javax.annotation.Generated(value = "Renovus") public class PrfFunctionalityRowWrapper implements RowMapper<PrfFunctionalityVo> {

	//--- Constructors --------------------------
	private PrfFunctionalityRowWrapper() {}
	private static PrfFunctionalityRowWrapper instance = new PrfFunctionalityRowWrapper();
	public static PrfFunctionalityRowWrapper getInstance() { return PrfFunctionalityRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetPrfId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetFncId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public PrfFunctionalityVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		PrfFunctionalityVo vo = new PrfFunctionalityVo();

		vo.setPrfId(this.getResultSetPrfId(resultSet, PrfFunctionalityVo.COLUMN_PRF_ID));
		vo.setFncId(this.getResultSetFncId(resultSet, PrfFunctionalityVo.COLUMN_FNC_ID));

		return vo;
	}
}


package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocEstimationVo;

@javax.annotation.Generated(value = "Renovus") public class LocEstimationRowWrapper implements RowMapper<LocEstimationVo> {

	//--- Constructors --------------------------
	private LocEstimationRowWrapper() {}
	private static LocEstimationRowWrapper instance = new LocEstimationRowWrapper();
	public static LocEstimationRowWrapper getInstance() { return LocEstimationRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocEstId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocEstOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetLocEstTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Double getResultSetLocEst01(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst02(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst03(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst04(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst05(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst06(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst07(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst08(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst09(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst10(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst11(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetLocEst12(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocEstimationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocEstimationVo vo = new LocEstimationVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocEstimationVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocEstimationVo.COLUMN_LOC_ID));
		vo.setLocEstId(this.getResultSetLocEstId(resultSet, LocEstimationVo.COLUMN_LOC_EST_ID));
		vo.setLocEstOrder(this.getResultSetLocEstOrder(resultSet, LocEstimationVo.COLUMN_LOC_EST_ORDER));
		vo.setLocEstTitle(this.getResultSetLocEstTitle(resultSet, LocEstimationVo.COLUMN_LOC_EST_TITLE));
		vo.setLocEst01(this.getResultSetLocEst01(resultSet, LocEstimationVo.COLUMN_LOC_EST_01));
		vo.setLocEst02(this.getResultSetLocEst02(resultSet, LocEstimationVo.COLUMN_LOC_EST_02));
		vo.setLocEst03(this.getResultSetLocEst03(resultSet, LocEstimationVo.COLUMN_LOC_EST_03));
		vo.setLocEst04(this.getResultSetLocEst04(resultSet, LocEstimationVo.COLUMN_LOC_EST_04));
		vo.setLocEst05(this.getResultSetLocEst05(resultSet, LocEstimationVo.COLUMN_LOC_EST_05));
		vo.setLocEst06(this.getResultSetLocEst06(resultSet, LocEstimationVo.COLUMN_LOC_EST_06));
		vo.setLocEst07(this.getResultSetLocEst07(resultSet, LocEstimationVo.COLUMN_LOC_EST_07));
		vo.setLocEst08(this.getResultSetLocEst08(resultSet, LocEstimationVo.COLUMN_LOC_EST_08));
		vo.setLocEst09(this.getResultSetLocEst09(resultSet, LocEstimationVo.COLUMN_LOC_EST_09));
		vo.setLocEst10(this.getResultSetLocEst10(resultSet, LocEstimationVo.COLUMN_LOC_EST_10));
		vo.setLocEst11(this.getResultSetLocEst11(resultSet, LocEstimationVo.COLUMN_LOC_EST_11));
		vo.setLocEst12(this.getResultSetLocEst12(resultSet, LocEstimationVo.COLUMN_LOC_EST_12));

		return vo;
	}
}


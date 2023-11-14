package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.EmberCountryOverviewVo;

public class EmberCountryOverviewRowWrapper implements RowMapper<EmberCountryOverviewVo> {

	//--- Constructors --------------------------
	private EmberCountryOverviewRowWrapper() {}
	private static EmberCountryOverviewRowWrapper instance = new EmberCountryOverviewRowWrapper();
	public static EmberCountryOverviewRowWrapper getInstance() { return EmberCountryOverviewRowWrapper.instance; }

	//--- Private methods -----------------------
	protected String getResultSetCountryOrRegion(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCountryCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetYear(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetDemandTwh(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetDemandMwhPerCapita(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected Double getResultSetEmissionsIntensityGco2PerKwh(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetContinent(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetEmberRegion(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetEuFlag(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetG20Flag(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetG7Flag(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetOecdFlag(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetWorldDemandRank(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetRegionDemandRank(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetOecdDemandRank(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetEuDemandRank(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLatestYear(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCoalDeadline(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCleanDeadline(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public EmberCountryOverviewVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		EmberCountryOverviewVo vo = new EmberCountryOverviewVo();

		vo.setCountryOrRegion(this.getResultSetCountryOrRegion(resultSet, EmberCountryOverviewVo.COLUMN_COUNTRY_OR_REGION));
		vo.setCountryCode(this.getResultSetCountryCode(resultSet, EmberCountryOverviewVo.COLUMN_COUNTRY_CODE));
		vo.setYear(this.getResultSetYear(resultSet, EmberCountryOverviewVo.COLUMN_YEAR));
		vo.setDemandTwh(this.getResultSetDemandTwh(resultSet, EmberCountryOverviewVo.COLUMN_DEMAND_TWH));
		vo.setDemandMwhPerCapita(this.getResultSetDemandMwhPerCapita(resultSet, EmberCountryOverviewVo.COLUMN_DEMAND_MWH_PER_CAPITA));
		vo.setEmissionsIntensityGco2PerKwh(this.getResultSetEmissionsIntensityGco2PerKwh(resultSet, EmberCountryOverviewVo.COLUMN_EMISSIONS_INTENSITY_GCO2_PER_KWH));
		vo.setContinent(this.getResultSetContinent(resultSet, EmberCountryOverviewVo.COLUMN_CONTINENT));
		vo.setEmberRegion(this.getResultSetEmberRegion(resultSet, EmberCountryOverviewVo.COLUMN_EMBER_REGION));
		vo.setEuFlag(this.getResultSetEuFlag(resultSet, EmberCountryOverviewVo.COLUMN_EU_FLAG));
		vo.setG20Flag(this.getResultSetG20Flag(resultSet, EmberCountryOverviewVo.COLUMN_G20_FLAG));
		vo.setG7Flag(this.getResultSetG7Flag(resultSet, EmberCountryOverviewVo.COLUMN_G7_FLAG));
		vo.setOecdFlag(this.getResultSetOecdFlag(resultSet, EmberCountryOverviewVo.COLUMN_OECD_FLAG));
		vo.setWorldDemandRank(this.getResultSetWorldDemandRank(resultSet, EmberCountryOverviewVo.COLUMN_WORLD_DEMAND_RANK));
		vo.setRegionDemandRank(this.getResultSetRegionDemandRank(resultSet, EmberCountryOverviewVo.COLUMN_REGION_DEMAND_RANK));
		vo.setOecdDemandRank(this.getResultSetOecdDemandRank(resultSet, EmberCountryOverviewVo.COLUMN_OECD_DEMAND_RANK));
		vo.setEuDemandRank(this.getResultSetEuDemandRank(resultSet, EmberCountryOverviewVo.COLUMN_EU_DEMAND_RANK));
		vo.setLatestYear(this.getResultSetLatestYear(resultSet, EmberCountryOverviewVo.COLUMN_LATEST_YEAR));
		vo.setCoalDeadline(this.getResultSetCoalDeadline(resultSet, EmberCountryOverviewVo.COLUMN_COAL_DEADLINE));
		vo.setCleanDeadline(this.getResultSetCleanDeadline(resultSet, EmberCountryOverviewVo.COLUMN_CLEAN_DEADLINE));

		return vo;
	}
}


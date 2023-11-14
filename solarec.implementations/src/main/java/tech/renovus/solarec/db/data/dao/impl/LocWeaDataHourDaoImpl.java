package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocWeaDataHourDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocWeaDataHourDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocWeaDataHourRowWrapper;
import tech.renovus.solarec.db.data.vo.LocWeaDataHourVo;

@Repository
public class LocWeaDataHourDaoImpl extends BaseLocWeaDataHourDao implements LocWeaDataHourDao {
	
	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_FOR_PERIOD		= "select a.* from loc_wea_data_hour a where a.cli_id = :cli_id and a.loc_id = :loc_id and :from <= loc_wea_data_hour and loc_wea_data_hour < :to \r\n"
			+ "and a.loc_wea_data_retrieve = (select max(l.loc_wea_data_retrieve) from loc_wea_data_hour l where l.cli_id = :cli_id and l.loc_id = :loc_id and :from <= l.loc_wea_data_hour and l.loc_wea_data_hour < :to )\r\n"
			+ "order by loc_wea_data_hour asc";
	private final String SQL_SELECT_ALL_FOR_TYPE_FOR_PERIOD		= "select a.* from loc_wea_data_hour a where a.cli_id = :cli_id and a.loc_id = :loc_id and a.loc_wea_data_id = :loc_wea_data_id and :from <= loc_wea_data_hour and loc_wea_data_hour < :to \r\n"
			+ "and a.loc_wea_data_retrieve = (select max(l.loc_wea_data_retrieve) from loc_wea_data_hour l where l.cli_id = :cli_id and l.loc_id = :loc_id and :from <= l.loc_wea_data_hour and l.loc_wea_data_hour < :to )\r\n"
			+ "order by loc_wea_data_hour asc";
	
	//--- Constructors --------------------------
	@Autowired public LocWeaDataHourDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<LocWeaDataHourVo> getForPeriod(Integer cliId, Integer locId, Date from, Date to) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_PERIOD,
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("loc_id", locId)
					.addValue("from", from)
					.addValue("to", to),
				LocWeaDataHourRowWrapper.getInstance()
			);
	}  
	
	@Override
	public Collection<LocWeaDataHourVo> getForPeriod(Integer cliId, Integer locId, Integer locWeaDataId, Date from, Date to) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_TYPE_FOR_PERIOD,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("loc_id", locId)
				.addValue("loc_wea_data_id", locWeaDataId)
				.addValue("from", from)
				.addValue("to", to),
				LocWeaDataHourRowWrapper.getInstance()
				);
	}  
}

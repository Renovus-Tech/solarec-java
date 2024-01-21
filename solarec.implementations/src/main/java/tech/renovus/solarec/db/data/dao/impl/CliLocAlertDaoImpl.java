package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.CliLocAlertWithOtherRowWrapper;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

@Repository
public class CliLocAlertDaoImpl extends BaseCliLocAlertDao implements CliLocAlertDao {

	//--- Private properties --------------------
	private final static String SQL_FIND_ALL_FOR_DATA_DEFINITION = "select * from vw_cli_loc_alert_full where cli_id = :cliId and loc_id = :locId and cli_loc_alert_added >= :from and cli_loc_alert_added < :to";

	//--- Constructors --------------------------
	@Autowired public CliLocAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<CliLocAlertVo> retrieveFor(Integer cliId, Integer locId, Date from, Date to) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_DATA_DEFINITION, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId)
					.addValue("locId", locId)
					.addValue("from", from)
					.addValue("to", to),
					CliLocAlertWithOtherRowWrapper.getInstance()
			);
	}

}
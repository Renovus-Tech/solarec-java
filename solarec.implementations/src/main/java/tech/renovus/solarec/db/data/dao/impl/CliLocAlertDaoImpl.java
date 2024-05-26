package tech.renovus.solarec.db.data.dao.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

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
	private final static String SQL_FIND_ALL_FOR_DATA_DEFINITION 	= "select * from vw_cli_loc_alert_full where cli_id = :cliId and loc_id = :locId and cli_loc_alert_added >= :from and cli_loc_alert_added < :to";
	private final static String SQL_UPDATE_FLAGS					= "UPDATE cli_loc_alert SET cli_loc_alert_flags = :cli_loc_alert_flags WHERE loc_id = :loc_id AND cli_loc_alert_id_auto = :cli_loc_alert_id_auto AND cli_id = :cli_id";
	private static final String SQL_FIND_ALL_FOR_EMAIL_ALERT		= "SELECT * FROM vw_cli_loc_alert_to_send_by_email";

	//--- Constructors --------------------------
	@Autowired public CliLocAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<CliLocAlertVo> retrieveFor(Integer cliId, Integer locId, Date from, Date to) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(to);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		to = cal.getTime();
		
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

	@Override
	public void updateFlags(CliLocAlertVo vo) {
		this.jdbc.update(
				SQL_UPDATE_FLAGS,
				new MapSqlParameterSource()
					.addValue("cli_loc_alert_flags", vo.getCliLocAlertFlags())
					.addValue("loc_id", vo.getLocId())
					.addValue("cli_loc_alert_id_auto", vo.getCliLocAlertId())
					.addValue("cli_id", vo.getCliId())
			);
	}
	
	@Override public Collection<CliLocAlertVo> retrieveForEmailAlert() {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_EMAIL_ALERT, 
				CliLocAlertWithOtherRowWrapper.getInstance()
			);
	}
}
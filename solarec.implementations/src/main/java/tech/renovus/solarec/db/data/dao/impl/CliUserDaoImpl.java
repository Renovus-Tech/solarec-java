package tech.renovus.solarec.db.data.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.CliUserDao;
import tech.renovus.solarec.db.data.dao.base.BaseCliUserDao;

@Repository
public class CliUserDaoImpl extends BaseCliUserDao implements CliUserDao {
	
	//--- Private properties --------------------
	private static final String SQL_UPDATE_ACCESS_DATE			= "UPDATE cli_user SET cli_user_date_access= :cli_user_date_access WHERE cli_id = :cli_id AND usr_id = :usr_id";

	
	//--- Constructors --------------------------
	@Autowired public CliUserDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public void setAccessDate(Integer cliId, Integer usrId, Date date) {
		this.jdbc.update(
			SQL_UPDATE_ACCESS_DATE, 
			new MapSqlParameterSource()
				.addValue("cli_user_date_access", date)
				.addValue("cli_id", cliId)
				.addValue("usr_id", usrId)
		);
	} 

}

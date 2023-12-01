package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;

@Repository
public class CliLocAlertDaoImpl extends BaseCliLocAlertDao implements CliLocAlertDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public CliLocAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
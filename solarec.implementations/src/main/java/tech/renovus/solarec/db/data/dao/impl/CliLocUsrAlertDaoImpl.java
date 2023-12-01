package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliLocUsrAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocUsrAlertDao;

@Repository
public class CliLocUsrAlertDaoImpl extends BaseCliLocUsrAlertDao implements CliLocUsrAlertDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public CliLocUsrAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
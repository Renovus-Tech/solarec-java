package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliGenUsrAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenUsrAlertDao;

@Repository
public class CliGenUsrAlertDaoImpl extends BaseCliGenUsrAlertDao implements CliGenUsrAlertDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public CliGenUsrAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
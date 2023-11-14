package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.UsrProfileDao;
import tech.renovus.solarec.db.data.dao.base.BaseUsrProfileDao;

@Repository
public class UsrProfileDaoImpl extends BaseUsrProfileDao implements UsrProfileDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public UsrProfileDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}

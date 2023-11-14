package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.ProfileDao;
import tech.renovus.solarec.db.data.dao.base.BaseProfileDao;

@Repository
public class ProfileDaoImpl extends BaseProfileDao implements ProfileDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public ProfileDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

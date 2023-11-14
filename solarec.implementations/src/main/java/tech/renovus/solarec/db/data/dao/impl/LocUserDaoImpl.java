package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.LocUserDao;
import tech.renovus.solarec.db.data.dao.base.BaseLocUserDao;

@Repository
public class LocUserDaoImpl extends BaseLocUserDao implements LocUserDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public LocUserDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StatDefSourceDao;
import tech.renovus.solarec.db.data.dao.base.BaseStatDefSourceDao;

@Repository
public class StatDefSourceDaoImpl extends BaseStatDefSourceDao implements StatDefSourceDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StatDefSourceDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

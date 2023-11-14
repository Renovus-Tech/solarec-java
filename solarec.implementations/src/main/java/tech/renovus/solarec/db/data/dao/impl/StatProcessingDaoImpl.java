package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StatProcessingDao;
import tech.renovus.solarec.db.data.dao.base.BaseStatProcessingDao;

@Repository
public class StatProcessingDaoImpl extends BaseStatProcessingDao implements StatProcessingDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StatProcessingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StatDefinitionDao;
import tech.renovus.solarec.db.data.dao.base.BaseStatDefinitionDao;

@Repository
public class StatDefinitionDaoImpl extends BaseStatDefinitionDao implements StatDefinitionDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StatDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

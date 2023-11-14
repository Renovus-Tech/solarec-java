package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseAlertDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.AlertDefinitionDao;

@Repository
public class AlertDefinitionDaoImpl extends BaseAlertDefinitionDao implements AlertDefinitionDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public AlertDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

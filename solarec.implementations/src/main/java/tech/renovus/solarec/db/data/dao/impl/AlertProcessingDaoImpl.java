package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseAlertProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.AlertProcessingDao;

@Repository
public class AlertProcessingDaoImpl extends BaseAlertProcessingDao implements AlertProcessingDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public AlertProcessingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

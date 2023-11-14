package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.DataProAlertProcessingDao;
import tech.renovus.solarec.db.data.dao.base.BaseDataProAlertProcessingDao;

@Repository
public class DataProAlertProcessingDaoImpl extends BaseDataProAlertProcessingDao implements DataProAlertProcessingDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public DataProAlertProcessingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

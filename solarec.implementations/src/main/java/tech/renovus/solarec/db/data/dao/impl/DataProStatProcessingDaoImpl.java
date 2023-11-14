package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.DataProStatProcessingDao;
import tech.renovus.solarec.db.data.dao.base.BaseDataProStatProcessingDao;

@Repository
public class DataProStatProcessingDaoImpl extends BaseDataProStatProcessingDao implements DataProStatProcessingDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public DataProStatProcessingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

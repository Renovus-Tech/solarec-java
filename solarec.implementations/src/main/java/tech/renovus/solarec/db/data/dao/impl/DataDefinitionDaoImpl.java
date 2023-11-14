package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.base.BaseDataDefinitionDao;

@Repository
public class DataDefinitionDaoImpl extends BaseDataDefinitionDao implements DataDefinitionDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public DataDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

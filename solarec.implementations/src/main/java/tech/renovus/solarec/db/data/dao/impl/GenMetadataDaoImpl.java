package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.GenMetadataDao;
import tech.renovus.solarec.db.data.dao.base.BaseGenMetadataDao;

@Repository
public class GenMetadataDaoImpl extends BaseGenMetadataDao implements GenMetadataDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public GenMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

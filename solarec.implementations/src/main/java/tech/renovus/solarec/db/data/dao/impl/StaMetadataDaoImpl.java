package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StaMetadataDao;
import tech.renovus.solarec.db.data.dao.base.BaseStaMetadataDao;

@Repository
public class StaMetadataDaoImpl extends BaseStaMetadataDao implements StaMetadataDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StaMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

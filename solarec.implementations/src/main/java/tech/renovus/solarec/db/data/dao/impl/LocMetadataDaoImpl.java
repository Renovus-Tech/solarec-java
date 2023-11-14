package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.LocMetadataDao;
import tech.renovus.solarec.db.data.dao.base.BaseLocMetadataDao;

@Repository
public class LocMetadataDaoImpl extends BaseLocMetadataDao implements LocMetadataDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public LocMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

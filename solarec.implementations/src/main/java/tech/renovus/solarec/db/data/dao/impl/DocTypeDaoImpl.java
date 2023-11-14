package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.DocTypeDao;
import tech.renovus.solarec.db.data.dao.base.BaseDocTypeDao;

@Repository
public class DocTypeDaoImpl extends BaseDocTypeDao implements DocTypeDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public DocTypeDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

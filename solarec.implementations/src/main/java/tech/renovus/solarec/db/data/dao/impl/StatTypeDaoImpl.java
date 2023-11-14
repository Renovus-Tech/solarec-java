package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StatTypeDao;
import tech.renovus.solarec.db.data.dao.base.BaseStatTypeDao;

@Repository
public class StatTypeDaoImpl extends BaseStatTypeDao implements StatTypeDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StatTypeDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

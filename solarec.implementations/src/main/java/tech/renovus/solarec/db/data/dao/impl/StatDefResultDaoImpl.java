package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StatDefResultDao;
import tech.renovus.solarec.db.data.dao.base.BaseStatDefResultDao;

@Repository
public class StatDefResultDaoImpl extends BaseStatDefResultDao implements StatDefResultDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StatDefResultDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

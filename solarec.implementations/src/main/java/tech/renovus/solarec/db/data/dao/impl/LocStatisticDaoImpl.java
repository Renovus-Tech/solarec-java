package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.LocStatisticDao;
import tech.renovus.solarec.db.data.dao.base.BaseLocStatisticDao;

@Repository
public class LocStatisticDaoImpl extends BaseLocStatisticDao implements LocStatisticDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public LocStatisticDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseChartDao;
import tech.renovus.solarec.db.data.dao.interfaces.ChartDao;

@Repository
public class ChartDaoImpl extends BaseChartDao implements ChartDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public ChartDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

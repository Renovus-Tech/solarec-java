package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.ReportDao;
import tech.renovus.solarec.db.data.dao.base.BaseReportDao;

@Repository
public class ReportDaoImpl extends BaseReportDao implements ReportDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public ReportDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------

}

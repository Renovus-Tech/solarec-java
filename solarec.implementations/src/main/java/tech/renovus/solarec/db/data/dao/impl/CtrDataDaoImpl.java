package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCtrDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.CtrDataDao;

@Repository
public class CtrDataDaoImpl extends BaseCtrDataDao implements CtrDataDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public CtrDataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
		
		this.SQL_INSERT += this.SQL_ON_CONFLICT_PK_UPDATE;
	}
	
	//--- Overridden methods --------------------

}
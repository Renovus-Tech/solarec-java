package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseSdgDao;
import tech.renovus.solarec.db.data.dao.interfaces.SdgDao;

@Repository
public class SdgDaoImpl extends BaseSdgDao implements SdgDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public SdgDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseFrequencyDao;
import tech.renovus.solarec.db.data.dao.interfaces.FrequencyDao;

@Repository
public class FrequencyDaoImpl extends BaseFrequencyDao implements FrequencyDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public FrequencyDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
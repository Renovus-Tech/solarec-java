package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDefParameterDao;

@Repository
public class GenDataDefParameterDaoImpl extends BaseGenDataDefParameterDao implements GenDataDefParameterDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public GenDataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
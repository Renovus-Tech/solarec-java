package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocTypeDao;

@Repository
public class LocTypeDaoImpl extends BaseLocTypeDao implements LocTypeDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public LocTypeDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
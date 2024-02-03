package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDefParameterDao;

@Repository
public class LocDataDefParameterDaoImpl extends BaseLocDataDefParameterDao implements LocDataDefParameterDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public LocDataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
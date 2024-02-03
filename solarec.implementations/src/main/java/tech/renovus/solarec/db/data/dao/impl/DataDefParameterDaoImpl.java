package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefParameterDao;

@Repository
public class DataDefParameterDaoImpl extends BaseDataDefParameterDao implements DataDefParameterDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public DataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
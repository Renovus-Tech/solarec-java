package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataCategoryDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataCategoryDao;

@Repository
public class DataCategoryDaoImpl extends BaseDataCategoryDao implements DataCategoryDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public DataCategoryDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.wrapper.CountryRowWrapper;
import tech.renovus.solarec.vo.db.data.CountryVo;

@Repository
public class CountryDaoImpl extends BaseCountryDao implements CountryDao {
	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_IN_USER = "SELECT * FROM country WHERE ctr_id_auto IN (SELECT ctr_id FROM Location)"; 

	//--- Constructors --------------------------
	@Autowired public CountryDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<CountryVo> getCountriesInUse() {
		return this.jdbc.query(
				SQL_SELECT_ALL_IN_USER, 
				CountryRowWrapper.getInstance()
			);
	}

}
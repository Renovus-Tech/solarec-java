package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	private final String SQL_SELECT_BY_CODE_2 = "SELECT * FROM country WHERE ctr_code_2 = :ctr_code_2"; 
	private final String SQL_SELECT_BY_CODE_3 = "SELECT * FROM country WHERE ctr_code_3 = :ctr_code_3"; 

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

	@Override
	public CountryVo findByCode2(String code2) {
		try {
			return this.jdbc.queryForObject(
					SQL_SELECT_BY_CODE_2, 
					new MapSqlParameterSource()
							.addValue(CountryVo.COLUMN_CTR_CODE_2, code2),
					CountryRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public CountryVo findByCode3(String code3) {
		try {
			return this.jdbc.queryForObject(
					SQL_SELECT_BY_CODE_3, 
					new MapSqlParameterSource()
							.addValue(CountryVo.COLUMN_CTR_CODE_3, code3),
					CountryRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
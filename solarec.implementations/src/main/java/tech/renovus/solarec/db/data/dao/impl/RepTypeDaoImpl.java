package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseRepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.RepTypeDao;
import tech.renovus.solarec.db.data.dao.wrapper.RepTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.RepTypeVo;

@Repository
public class RepTypeDaoImpl extends BaseRepTypeDao implements RepTypeDao {
	
	//--- Private properties --------------------
	private static final String SQL_FIND_ALL_ACTIVE = "SELECT * FROM rep_type WHERE rep_flags like '1%' ORDER BY rep_order";
	private static final String SQL_FIND_BY_NAME	= "SELECT * FROM rep_type WHERE rep_type_name = :rep_type_name";
	
	//--- Constructors --------------------------
	@Autowired public RepTypeDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<RepTypeVo> getlAllActive() {
		return this.jdbc.query(
				SQL_FIND_ALL_ACTIVE,
				new MapSqlParameterSource(),
				RepTypeRowWrapper.getInstance()
			);
	}

	@Override public RepTypeVo findByName(String repTypeName) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_BY_NAME, 
					new MapSqlParameterSource()
						.addValue("rep_type_name", repTypeName),
					RepTypeRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}

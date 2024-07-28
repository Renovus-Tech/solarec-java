package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StatDefinitionDao;
import tech.renovus.solarec.db.data.dao.wrapper.StatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.db.data.dao.base.BaseStatDefinitionDao;

@Repository
public class StatDefinitionDaoImpl extends BaseStatDefinitionDao implements StatDefinitionDao {
	
	//--- Private properties --------------------
	private static final String SQL_SELECT_BY_NAME		= "SELECT * FROM stat_definition WHERE stat_def_name = :stat_def_name";
	
	//--- Constructors --------------------------
	@Autowired public StatDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	public StatDefinitionVo findVo(String statDefName) {
		try {
			return this.jdbc.queryForObject(
					SQL_SELECT_BY_NAME, 
					new MapSqlParameterSource().addValue(StatDefinitionVo.COLUMN_STAT_DEF_NAME, statDefName),
					StatDefinitionRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}

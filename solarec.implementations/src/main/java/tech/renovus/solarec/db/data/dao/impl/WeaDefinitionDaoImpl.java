package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseWeaDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.WeaDefinitionDao;
import tech.renovus.solarec.db.data.dao.wrapper.WeaDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.WeaDefinitionVo;

@Repository
public class WeaDefinitionDaoImpl extends BaseWeaDefinitionDao implements WeaDefinitionDao {
	
	//--- Private properties --------------------
	private final static String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM wea_definition WHERE cli_id = :cliId ";

	//--- Constructors --------------------------
	@Autowired public WeaDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<WeaDefinitionVo> findAll(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId),
				WeaDefinitionRowWrapper.getInstance()
			);
	}
}

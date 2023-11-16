package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseWeaCallDao;
import tech.renovus.solarec.db.data.dao.interfaces.WeaCallDao;
import tech.renovus.solarec.db.data.dao.wrapper.WeaCallRowWrapper;
import tech.renovus.solarec.vo.db.data.WeaCallVo;

@Repository
public class WeaCallDaoImpl extends BaseWeaCallDao implements WeaCallDao {
	
	//--- Private properties --------------------
	private final static String SQL_GET_HISTORY						= "SELECT * FROM wea_call WHERE cli_id = :cliId AND wea_id = :weaId ORDER BY wea_call_date DESC OFFSET 0 LIMIT :amount";
	
	//--- Constructors --------------------------
	@Autowired public WeaCallDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public WeaCallVo getLast(Integer cliId, Integer weaId) {
		try {
			return this.jdbc.queryForObject(
					SQL_GET_HISTORY, 
					new MapSqlParameterSource()
						.addValue("cliId", cliId)
						.addValue("weaId", weaId)
						.addValue("amount", Integer.valueOf(1)),
					WeaCallRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Collection<WeaCallVo> getHistory(Integer cliId, Integer weaId, Integer amount) {
		return this.jdbc.query(
				SQL_GET_HISTORY, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId)
					.addValue("weaId", weaId)
					.addValue("amount", amount),
				WeaCallRowWrapper.getInstance()
			);
	}
}

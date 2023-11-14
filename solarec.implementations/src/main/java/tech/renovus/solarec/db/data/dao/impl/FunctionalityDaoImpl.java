package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseFunctionalityDao;
import tech.renovus.solarec.db.data.dao.interfaces.FunctionalityDao;
import tech.renovus.solarec.db.data.dao.wrapper.FunctionalityRowWrapper;
import tech.renovus.solarec.db.data.vo.FunctionalityVo;

@Repository
public class FunctionalityDaoImpl extends BaseFunctionalityDao implements FunctionalityDao {
	
	//--- Private properties --------------------
	private final static String SQL_FIND_FOR_USER_CLIENT		= "SELECT * FROM vw_usr_cli_functionalities WHERE usr_id = :usrId AND cli_id = :cliId ORDER BY fnc_order";
	
	//--- Constructors --------------------------
	@Autowired public FunctionalityDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<FunctionalityVo> findFor(Integer usrId, Integer cliId) {
		return this.jdbc.query(
				SQL_FIND_FOR_USER_CLIENT, 
				new MapSqlParameterSource()
					.addValue("usrId", usrId)
					.addValue("cliId", cliId),
				FunctionalityRowWrapper.getInstance()
			);
	} 
}

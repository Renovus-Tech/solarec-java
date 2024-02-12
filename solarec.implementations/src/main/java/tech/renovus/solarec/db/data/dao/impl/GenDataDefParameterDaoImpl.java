package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.GenDataDefParameterWithParametersRowWrapper;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;

@Repository
public class GenDataDefParameterDaoImpl extends BaseGenDataDefParameterDao<GenDataDefParameterVo> implements GenDataDefParameterDao {
	
	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM vw_gen_data_def_parameter WHERE cli_id = :cli_id AND gen_id = :gen_id";

	//--- Constructors --------------------------
	@Autowired public GenDataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<GenDataDefParameterVo> getAllFor(Integer cliId, Integer genId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT,
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("gen_id", genId),
				GenDataDefParameterWithParametersRowWrapper.getInstance()
			);
	}

}
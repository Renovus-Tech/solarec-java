package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.CliDataDefParameterWithParametersRowWrapper;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;

@Repository
public class CliDataDefParameterDaoImpl extends BaseCliDataDefParameterDao<CliDataDefParameterVo> implements CliDataDefParameterDao {
	
	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM vw_cli_data_def_parameter WHERE cli_id = :cli_id";
	
	//--- Constructors --------------------------
	@Autowired public CliDataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<CliDataDefParameterVo> getAllFor(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT,
				new MapSqlParameterSource()
					.addValue("cli_id", cliId),
				CliDataDefParameterWithParametersRowWrapper.getInstance()
			);
	}

}
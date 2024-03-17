package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliMetadataDao;
import tech.renovus.solarec.db.data.dao.wrapper.CliMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.CliMetadataVo;

@Repository
public class CliMetadataDaoImpl extends BaseCliMetadataDao implements CliMetadataDao {
	
	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_FOR		= "SELECT * FROM cli_metadata where cli_id = :cli_id";

	//--- Constructors --------------------------
	@Autowired public CliMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<CliMetadataVo> getAllFor(Integer cliId) {
		return this.jdbc.query(
			SQL_SELECT_ALL_FOR,
			new MapSqlParameterSource()
				.addValue("cli_id", cliId),
			CliMetadataRowWrapper.getInstance()
		);
	}
}
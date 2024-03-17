package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenMetadataDao;
import tech.renovus.solarec.db.data.dao.wrapper.GenMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.GenMetadataVo;

@Repository
public class GenMetadataDaoImpl extends BaseGenMetadataDao implements GenMetadataDao {
	
	//--- Private properties --------------------
	protected final String SQL_SELECT_ALL_FOR		= "SELECT * FROM gen_metadata WHERE cli_id = :cli_id AND gen_id = :gen_id";
	
	//--- Constructors --------------------------
	@Autowired public GenMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<GenMetadataVo> getAllFor(Integer cliId, Integer genId) {
		return this.jdbc.query(
			SQL_SELECT_ALL_FOR,
			new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("gen_id", genId),
			GenMetadataRowWrapper.getInstance()
		);
	}
}

package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseStaMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaMetadataDao;
import tech.renovus.solarec.db.data.dao.wrapper.StaMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.StaMetadataVo;

@Repository
public class StaMetadataDaoImpl extends BaseStaMetadataDao implements StaMetadataDao {
	
	//--- Private properties --------------------
	protected final String SQL_SELECT_ALL_FOR		= "SELECT * FROM sta_metadata WHERE cli_id = :cli_id AND sta_id = :sta_id";
	
	//--- Constructors --------------------------
	@Autowired public StaMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<StaMetadataVo> findAllFor(Integer cliId, Integer staId) {
		return this.jdbc.query(
			SQL_SELECT_ALL_FOR,
			new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("sta_id", staId),
			StaMetadataRowWrapper.getInstance()
		);
	}
}

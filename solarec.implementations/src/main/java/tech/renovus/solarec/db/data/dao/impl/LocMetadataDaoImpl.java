package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocMetadataDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.LocMetadataVo;

@Repository
public class LocMetadataDaoImpl extends BaseLocMetadataDao implements LocMetadataDao {
	
	//--- Private properties --------------------
	protected final String SQL_SELECT_ALL_FOR		= "SELECT * FROM loc_metadata WHERE cli_id = :cli_id AND loc_id = :loc_id";
	
	//--- Constructors --------------------------
	@Autowired public LocMetadataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<LocMetadataVo> getAllFor(Integer cliId, Integer locId) {
		return this.jdbc.query(
			SQL_SELECT_ALL_FOR,
			new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("loc_id", locId),
			LocMetadataRowWrapper.getInstance()
		);
	}
}

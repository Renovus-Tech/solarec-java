package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocSdgDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocSdgDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.LocSdgWithSdgRowWrapper;
import tech.renovus.solarec.vo.db.data.LocSdgVo;

@Repository
public class LocSdgDaoImpl extends BaseLocSdgDao implements LocSdgDao {
	//--- Private properties --------------------
	protected final String SQL_GET_ALL_FOR_LOCATION_WITH_SDG	= "SELECT * FROM loc_sdg ls JOIN sdg s ON ls.sdg_id = s.sdg_id_auto WHERE ls.cli_id = :cli_id AND ls.loc_id = :loc_id";
	
	//--- Constructors --------------------------
	@Autowired public LocSdgDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<LocSdgVo> getAllForLocation(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_GET_ALL_FOR_LOCATION_WITH_SDG, 
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("loc_id", locId),
				LocSdgWithSdgRowWrapper.getInstance());
	}
}
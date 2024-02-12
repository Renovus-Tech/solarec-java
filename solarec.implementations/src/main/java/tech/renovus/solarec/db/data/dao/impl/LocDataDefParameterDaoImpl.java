package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.LocDataDefParameterWithParametersRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;

@Repository
public class LocDataDefParameterDaoImpl extends BaseLocDataDefParameterDao<LocDataDefParameterVo> implements LocDataDefParameterDao {

	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM vw_loc_data_def_parameter WHERE cli_id = :cli_id AND loc_id = :loc_id";

	//--- Constructors --------------------------
	@Autowired public LocDataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<LocDataDefParameterVo> getAlLFor(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT,
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("loc_id", locId),
				LocDataDefParameterWithParametersRowWrapper.getInstance()
			);
	}

}
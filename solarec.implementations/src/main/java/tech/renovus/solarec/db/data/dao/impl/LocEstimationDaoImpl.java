package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocEstimationDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocEstimationDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocEstimationRowWrapper;
import tech.renovus.solarec.vo.db.data.LocEstimationVo;

@Repository
public class LocEstimationDaoImpl extends BaseLocEstimationDao implements LocEstimationDao {
	
	//--- Private properties --------------------
	private final static String SQL_SELECT_ALL_FOR_LOCATION			= "SELECT * FROM loc_estimation WHERE cli_id = :cliId AND loc_id = :locId ORDER BY loc_est_order";
	private final static String SQL_DELETE_ALL_FOR_LOCATION			= "DELETE FROM loc_estimation WHERE cli_id = :cliId AND loc_id = :locId";
	
	//--- Constructors --------------------------
	@Autowired public LocEstimationDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<LocEstimationVo> findAll(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_LOCATION, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId)
					.addValue("locId", locId),
				LocEstimationRowWrapper.getInstance()
			);
	}

	@Override public void deleteAllFor(Integer cliId, Integer locId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_LOCATION, new MapSqlParameterSource().addValue("cliId", cliId).addValue("locId", locId));
	}

}

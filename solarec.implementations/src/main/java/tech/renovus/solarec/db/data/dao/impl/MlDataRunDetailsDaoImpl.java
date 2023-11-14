package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseMlDataRunDetailsDao;
import tech.renovus.solarec.db.data.dao.interfaces.MlDataRunDetailsDao;
import tech.renovus.solarec.db.data.dao.wrapper.MlDataRunDetailsRowWrapper;
import tech.renovus.solarec.db.data.vo.MlDataRunDetailsVo;

@Repository
public class MlDataRunDetailsDaoImpl extends BaseMlDataRunDetailsDao implements MlDataRunDetailsDao {
	
	//--- Private properties --------------------
	private static final String SQL_SELECT_ALL_FOR_DATE = "SELECT * FROM ml_data_run_details WHERE cli_id = :cli_id AND data_gen_id in (:gen_ids) AND :date_from <= data_date and data_date <= :date_to ORDER BY data_date DESC";
	
	//--- Constructors --------------------------
	@Autowired public MlDataRunDetailsDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<MlDataRunDetailsVo> retrieveVo(Integer cliId, List<Integer> genIds, Date from, Date to) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_DATE, 
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("gen_ids", genIds)
					.addValue("date_from", from)
					.addValue("date_to", to),
				MlDataRunDetailsRowWrapper.getInstance()
			);
	}  
}

package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProcessingDao;
import tech.renovus.solarec.db.data.dao.wrapper.DataProcessingRowWrapper;
import tech.renovus.solarec.db.data.vo.DataProcessingVo;

@Repository
public class DataProcessingDaoImpl extends BaseDataProcessingDao implements DataProcessingDao {
	
	//--- Private properties --------------------
	private final static String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM data_processing WHERE cli_id = :cliId order by data_pro_date_start desc";
	private static final String SQL_GET_MIN_DATE				= "SELECT MIN(data_date) FROM (SELECT MIN(data_date) AS data_date FROM loc_data WHERE cli_id = :cli_id AND data_pro_id = :data_pro_id union all SELECT MIN(data_date) as data_date FROM gen_data WHERE cli_id = :cli_id AND data_pro_id = :data_pro_id union all SELECT MIN(data_date) as data_date FROM sta_data WHERE cli_id = :cli_id AND data_pro_id = :data_pro_id) a";
	private static final String SQL_GET_MAX_DATE				= "SELECT MAX(data_date) FROM (SELECT MAX(data_date) AS data_date FROM loc_data WHERE cli_id = :cli_id AND data_pro_id = :data_pro_id union all SELECT MAX(data_date) as data_date FROM gen_data WHERE cli_id = :cli_id AND data_pro_id = :data_pro_id union all SELECT MAX(data_date) as data_date FROM sta_data WHERE cli_id = :cli_id AND data_pro_id = :data_pro_id) a";
	
	//--- Constructors --------------------------
	@Autowired public DataProcessingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<DataProcessingVo> finaAll(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId),
				DataProcessingRowWrapper.getInstance()
			);
	}
	
	@Override public Date getDate(Integer cliId, Integer dataProId, boolean fromDate) {
		String sql = fromDate ? SQL_GET_MIN_DATE : SQL_GET_MAX_DATE;
		return this.jdbc.queryForObject(
				sql, 
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("data_pro_id", dataProId),
				(rs, rowNum) -> rs.getTimestamp(1)
			);
	}
}

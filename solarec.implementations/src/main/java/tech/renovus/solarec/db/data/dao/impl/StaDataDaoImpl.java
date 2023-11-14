package tech.renovus.solarec.db.data.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseStaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.wrapper.StaDataRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.custom.GeneratorMaxDataDate;
import tech.renovus.solarec.db.data.vo.StaDataVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class StaDataDaoImpl extends BaseStaDataDao implements StaDataDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_STATION			= "DELETE FROM sta_data WHERE cli_id = :cli_id AND sta_id = :sta_id";
	private final String GET_FOR_CLI_STA_DATE_CODES						= "select * from sta_data g WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date ";
	private final String GET_FOR_CLI_STA_DATE_PERIOD_CODES				= "select * from sta_data WHERE cli_id = :cli_id AND sta_id = :sta_id AND :data_date_min <= data_date and data_date < :data_date_max AND data_type_id in ";
	private final static String SQL_GET_MAX_DATA_DATE_FOR_CLIENT		= "select max(data_date) from sta_data d where d.cli_id = :cliId and data_date <= :staDataDateMax " ;
	
	//--- Constructors --------------------------
	@Autowired public StaDataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
		
		this.SQL_INSERT += this.SQL_ON_CONFLICT_PK_UPDATE;
	}
	
	//--- Overridden methods --------------------
//	@Override public void insert(Collection<StaDataVo> vos) {
//		if (CollectionUtil.isEmpty(vos)) return;
//		for (StaDataVo vo : vos) if (vo != null) this.insert(vo);
//	}  

	@Override public void insert(Collection<StaDataVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		List<MapSqlParameterSource> params = new ArrayList<MapSqlParameterSource>();
		for (StaDataVo vo : vos)
			params.add(this.createInsertMapSqlParameterSource(vo));
		this.jdbc.batchUpdate(SQL_INSERT, params.toArray(new MapSqlParameterSource[0]));
	}
	
	@Override public void deleteAllForStation(Integer cliId, Integer staId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_STATION,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("sta_id", staId)
			);
	}
	
	@Override
	public Date getMaxDataDate(Integer cliId, Date currentDate, int... codes) {
		MapSqlParameterSource binding = new MapSqlParameterSource()
											.addValue("cliId", cliId)
											.addValue("staDataDateMax", currentDate);

		StringBuilder sql = new StringBuilder();
		sql.append(SQL_GET_MAX_DATA_DATE_FOR_CLIENT);

		if (codes != null && codes.length > 0) {
			sql.append(" AND data_type_id in (");
			for (int i = 0; i < codes.length; i++) {
				if (i > 0) sql.append(", ");
				sql.append(":code_" + i);
				binding.addValue("code_" + i, Integer.valueOf(codes[i]));
			}
			sql.append(")");
		}
		
		try {
			return this.jdbc.queryForObject(
					sql.toString(), 
					binding,
					GeneratorMaxDataDate.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public Collection<StaDataVo> getStaDataForDate(Integer cliId, Integer staId, Date staDataDateMax, int... codes) {
		StringBuilder sql = new StringBuilder();
		MapSqlParameterSource binding = 
			new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("sta_id", staId)
				.addValue("data_date", staDataDateMax);
		
		sql.append(GET_FOR_CLI_STA_DATE_CODES);
		
		if (codes != null && codes.length > 0) {
			sql.append(" AND data_type_id in (");
			for (int i = 0; i < codes.length; i++) { 
				if (i > 0) sql.append(", ");
				sql.append(":code_" + i);
				binding.addValue("code_" + i, Integer.valueOf(codes[i]));
			}
			sql.append(")");
		}
		
		try {
			return this.jdbc.query(
					sql.toString(),
					binding,
					StaDataRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public Collection<StaDataVo> getStaDataForDatePeriod(Integer cliId, Integer staId, Date staDataDateMin, Date staDataDateMax, int... codes) {
		MapSqlParameterSource binding = 
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("sta_id", staId)
				.addValue("data_date_min", staDataDateMin)
				.addValue("data_date_max", staDataDateMax);
		
		StringBuilder sql = new StringBuilder();
		sql.append(GET_FOR_CLI_STA_DATE_PERIOD_CODES);
		
		sql.append("(");
		for (int i = 0; i < codes.length; i++) { 
			if (i > 0) sql.append(", ");
			sql.append(":code_" + i);
			binding.addValue("code_" + i, Integer.valueOf(codes[i]));
		}
		sql.append(")");
		
		try {
			return this.jdbc.query(
					sql.toString(),
					binding,
					StaDataRowWrapper.getInstance()
					);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}

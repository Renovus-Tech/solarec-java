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

import tech.renovus.solarec.db.data.dao.base.BaseGenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.wrapper.GenDataRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.custom.GeneratorMaxDataDate;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@Repository
public class GenDataDaoImpl extends BaseGenDataDao implements GenDataDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_GENERATOR					= "DELETE FROM gen_data WHERE cli_id = :cli_id AND gen_id = :gen_id";
	
	private final String SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS					= "select gd.cli_id, gd.gen_id, cast(null as timestamp) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, avg(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id";
	private final String SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_DAY		= "select gd.cli_id, gd.gen_id, date_trunc('day', gd.data_date) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, avg(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id, date_trunc('day', gd.data_date)";
	private final String SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_WEEK		= "select gd.cli_id, gd.gen_id, date_trunc('week', gd.data_date) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, avg(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id, date_trunc('week', gd.data_date)";
	private final String SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_MONTH	= "select gd.cli_id, gd.gen_id, date_trunc('month', gd.data_date) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, avg(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id, date_trunc('month', gd.data_date)";
 	
	
	private final String SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS					= "select gd.cli_id, gd.gen_id, cast(null as timestamp) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, count(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and gd.data_value = :data_value and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id";
	private final String SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_DAY	= "select gd.cli_id, gd.gen_id, date_trunc('day', gd.data_date) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, count(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and gd.data_value = :data_value and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id, date_trunc('day', gd.data_date)";
	private final String SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_WEEK	= "select gd.cli_id, gd.gen_id, date_trunc('week', gd.data_date) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, count(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and gd.data_value = :data_value and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id, date_trunc('week', gd.data_date)";
	private final String SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_MONTH	= "select gd.cli_id, gd.gen_id, date_trunc('month', gd.data_date) as data_date, gd.data_type_id, cast(null as int) as data_pro_id, count(gd.data_value) as data_value, cast(null as timestamp) as data_date_added from generator g left join gen_data gd on g.cli_id = gd.cli_id and g.gen_id_auto = gd.gen_id where g.cli_id = :cli_id and g.loc_id = :loc_id and gd.data_type_id = :data_type_id and gd.data_value = :data_value and :date_from <= gd.data_date and gd.data_date < :date_to group by gd.cli_id, gd.gen_id, gd.data_type_id, date_trunc('month', gd.data_date)";
	
	private final String GET_FOR_CLI_GEN_DATE_CODES						= "select * from gen_data g WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date ";
	private final String GET_FOR_CLI_GEN_DATE_PERIOD_CODES				= "select * from gen_data WHERE cli_id = :cli_id AND gen_id = :gen_id AND :data_date_min <= data_date and data_date < :data_date_max AND data_type_id in ";
	private final String GET_FOR_CLI_LOC_NO_CERT_PROV_DATA				= "select * from gen_data WHERE cli_id = :cli_id AND gen_id in (SELECT gen_id_auto FROM generator g WHERE g.cli_id = :cli_id ADN g.loc_id = :loc_id) AND gen_data_cert_prov_data is null AND data_type_id in ";
	
	private final static String SQL_GET_MAX_DATA_DATE_FOR_CLIENT		= "select max(data_date) from gen_data d where d.cli_id = :cli_id and data_date <= :data_date " ;
	
	//--- Constructors --------------------------
	@Autowired public GenDataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
		
		this.SQL_INSERT += this.SQL_ON_CONFLICT_PK_UPDATE;
	}

	
	//--- Overridden methods --------------------
	@Override public void insert(Collection<GenDataVo> vos) {
		if (CollectionUtil.isEmpty(vos)) {
			return;
		}
		List<MapSqlParameterSource> params = new ArrayList<MapSqlParameterSource>();
		for (GenDataVo vo : vos) {
			params.add(this.createInsertMapSqlParameterSource(vo));
		}
		this.jdbc.batchUpdate(SQL_INSERT, params.toArray(new MapSqlParameterSource[0]));
	}
	
	@Override public void deleteAllForGenerator(Integer cliId, Integer genId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_GENERATOR,
				new MapSqlParameterSource()
				.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
				.addValue(GenDataVo.COLUMN_GEN_ID, genId)
			);
	}

	@Override public Collection<GenDataVo> getGeneratorDataAvg(Integer cliId, Integer locId, Integer dataTypeId, Date from, Date to) { return this.getGeneratorDataAvg(cliId, locId, dataTypeId, from, to, null); }
	
	@Override public Collection<GenDataVo> getGeneratorDataAvg(Integer cliId, Integer locId, Integer dataTypeId, Date from, Date to, String groupBy) {
		String 											sql = SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS;
		if (ChartFilter.GROUP_BY_DAY.equals(groupBy)) {
			sql = SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_DAY;
		} else if (ChartFilter.GROUP_BY_WEEK.equals(groupBy)) {
			sql = SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_WEEK;
		} else if (ChartFilter.GROUP_BY_MONTH.equals(groupBy)) {
			sql = SQL_AVG_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_MONTH;
		}
		
		try {
			return this.jdbc.query(
					sql,
					new MapSqlParameterSource()
					.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
					.addValue(GeneratorVo.COLUMN_LOC_ID, locId)
					.addValue(GenDataVo.COLUMN_DATA_TYPE_ID, dataTypeId)
					.addValue("date_from", from)
					.addValue("date_to", to),
					GenDataRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public Collection<GenDataVo> getGeneratorDataSumValueEquals(Integer cliId, Integer locId, Integer dataTypeId, Double dataValue, Date from, Date to) { return this.getGeneratorDataSumValueEquals(cliId, locId, dataTypeId, dataValue, from, to, null); }
	
	@Override public Collection<GenDataVo> getGeneratorDataSumValueEquals(Integer cliId, Integer locId, Integer dataTypeId, Double dataValue, Date from, Date to, String groupBy) {
		String 											sql = SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS;
		if (ChartFilter.GROUP_BY_DAY.equals(groupBy)) {
			sql = SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_DAY;
		} else if (ChartFilter.GROUP_BY_WEEK.equals(groupBy)) {
			sql = SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_WEEK;
		} else if (ChartFilter.GROUP_BY_MONTH.equals(groupBy)) {
			sql = SQL_COUNT_VALUE_FOR_CLIENT_LOCATION_GENERATORS_GROUP_BY_MONTH;
		}
		
		try {
			return this.jdbc.query(
					sql,
					new MapSqlParameterSource()
					.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
					.addValue(GeneratorVo.COLUMN_LOC_ID, locId)
					.addValue(GenDataVo.COLUMN_DATA_TYPE_ID, dataTypeId)
					.addValue(GenDataVo.COLUMN_DATA_VALUE, dataValue)
					.addValue("date_from", from)
					.addValue("date_to", to),
					GenDataRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public Collection<GenDataVo> getGenDataForDate(Integer cliId, Integer genId, Date genDataDateMax, int... codes) {
		StringBuilder sql = new StringBuilder();
		MapSqlParameterSource binding = 
			new MapSqlParameterSource()
				.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
				.addValue(GenDataVo.COLUMN_GEN_ID, genId)
				.addValue(GenDataVo.COLUMN_DATA_DATE, genDataDateMax);
		
		sql.append(GET_FOR_CLI_GEN_DATE_CODES);
		
		if (codes != null && codes.length > 0) {
			sql.append(" AND data_type_id in (");
			for (int i = 0; i < codes.length; i++) { 
				if (i > 0) {
					sql.append(", ");
				}
				sql.append(":code_" + i);
				binding.addValue("code_" + i, Integer.valueOf(codes[i]));
			}
			sql.append(")");
		}
		
		try {
			return this.jdbc.query(
					sql.toString(),
					binding,
					GenDataRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override public Collection<GenDataVo> getGenDataForDatePeriod(Integer cliId, Integer genId, Date genDataDateMin, Date genDataDateMax, int... codes) {
		MapSqlParameterSource binding = new MapSqlParameterSource()
											.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
											.addValue(GenDataVo.COLUMN_GEN_ID, genId)
											.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MIN, genDataDateMin)
											.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MAX, genDataDateMax);
		
		StringBuilder sql = new StringBuilder();
		sql.append(GET_FOR_CLI_GEN_DATE_PERIOD_CODES);
		
		sql.append("(");
		for (int i = 0; i < codes.length; i++) { 
			if (i > 0) {
				sql.append(", ");
			}
			sql.append(":code_" + i);
			binding.addValue("code_" + i, Integer.valueOf(codes[i]));
		}
		sql.append(")");
		
		try {
			return this.jdbc.query(
					sql.toString(),
					binding,
					GenDataRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Date getMaxDataDate(Integer cliId, Date currentDate, int... codes) {
		MapSqlParameterSource binding = new MapSqlParameterSource()
											.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
											.addValue(GenDataVo.COLUMN_DATA_DATE, currentDate);
		
		StringBuilder sql = new StringBuilder();
		sql.append(SQL_GET_MAX_DATA_DATE_FOR_CLIENT);
		
		if (codes != null && codes.length > 0) {
			sql.append(" AND data_type_id in (");
			for (int i = 0; i < codes.length; i++) { 
				if (i > 0) {
					sql.append(", ");
				}
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


	@Override
	public Collection<GenDataVo> getAllWithoutCertProvData(Integer cliId, Integer locId, int... codes) {
		MapSqlParameterSource binding = new MapSqlParameterSource()
				.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
				.addValue(GeneratorVo.COLUMN_LOC_ID, locId);

		StringBuilder sql = new StringBuilder();
		sql.append(GET_FOR_CLI_LOC_NO_CERT_PROV_DATA);

		sql.append("(");
		for (int i = 0; i < codes.length; i++) {
			if (i > 0) {
				sql.append(", ");
			}
			sql.append(":code_" + i);
			binding.addValue("code_" + i, Integer.valueOf(codes[i]));
		}
		sql.append(")");

		try {
			return this.jdbc.query(sql.toString(), binding, GenDataRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}

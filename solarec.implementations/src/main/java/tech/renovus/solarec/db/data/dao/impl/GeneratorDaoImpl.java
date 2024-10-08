package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.wrapper.GeneratorRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.custom.GeneratorMaxDataDate;
import tech.renovus.solarec.vo.db.data.GeneratorVo;

@Repository
public class GeneratorDaoImpl extends BaseGeneratorDao implements GeneratorDao {
	
	//--- Private constants ---------------------
	private final static String SQL_SELECT_ALL_FOR_CLIENT						= "SELECT * FROM generator WHERE cli_id = :cli_id ORDER BY gen_code";
	private final static String SQL_SELECT_ALL_FOR_LOCATION						= "SELECT * FROM generator WHERE cli_id = :cli_id AND loc_id = :loc_id ORDER BY gen_code";
	private final static String SQL_FIND_BY_SERIAL_NUM							= "SELECT * FROM generator WHERE cli_id = :cli_id AND gen_serial_num = :gen_serial_num";

	private final static String SQL_UPDATE_DATA_DATE_MAX 						= "update generator set gen_data_date_max = (select max(data_date) from gen_data where gen_data.cli_id = generator.cli_id and gen_data.gen_id = generator.gen_id_auto)";
	private final static String SQL_UPDATE_DATA_DATE_MIN 						= "update generator set gen_data_date_min = (select min(data_date) from gen_data where gen_data.cli_id = generator.cli_id and gen_data.gen_id = generator.gen_id_auto)";
	
	private final static String SQL_GET_MAX_DATA_DATE_FOR_CLIENT				= "SELECT max(gen_data_date_max) FROM generator WHERE cli_id = :cli_id AND gen_data_date_max <= :gen_data_date_max" ;
	private final static String SQL_SELECT_ALL_WITHD_DATA_DEFINITION_INVERTER	= "SELECT g.* FROM generator g, data_definition dd WHERE g.data_def_id = dd.data_def_id_auto AND dd.data_def_flags like '__1%'";

	//--- Constructors --------------------------
	@Autowired public GeneratorDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}
	
	//--- Overridden methods --------------------
	@Override public Collection<GeneratorVo> findAll(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT, 
				new MapSqlParameterSource()
					.addValue(GeneratorVo.COLUMN_CLI_ID, cliId),
				GeneratorRowWrapper.getInstance()
			);
	}

	@Override public Collection<GeneratorVo> findAll(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_LOCATION, 
				new MapSqlParameterSource()
					.addValue(GeneratorVo.COLUMN_CLI_ID, cliId)
					.addValue(GeneratorVo.COLUMN_LOC_ID, locId),
				GeneratorRowWrapper.getInstance()
			);
	}
	
	@Override public GeneratorVo findVoBySerialNumber(Integer cliId, String serailNum) {
		try {
			return this.jdbc.queryForObject(SQL_FIND_BY_SERIAL_NUM, 
					new MapSqlParameterSource()
					.addValue(GeneratorVo.COLUMN_CLI_ID, cliId)
					.addValue(GeneratorVo.COLUMN_GEN_SERIAL_NUM, serailNum),
					GeneratorRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override public void updateDataDateMaxMin() {
		this.jdbc.update( SQL_UPDATE_DATA_DATE_MAX, new MapSqlParameterSource() );
		this.jdbc.update( SQL_UPDATE_DATA_DATE_MIN, new MapSqlParameterSource() );
	}

	@Override public Date getMaxDataDate(Integer cliId, Date beforeDate) {
		try { 
			return this.jdbc.queryForObject(
					SQL_GET_MAX_DATA_DATE_FOR_CLIENT, 
					new MapSqlParameterSource()
					.addValue(GeneratorVo.COLUMN_CLI_ID, cliId)
					.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MAX, beforeDate), 
					GeneratorMaxDataDate.getInstance()
					);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override public Collection<GeneratorVo> getAllGeneratorsWithDataDefinitionInverter() {
		return this.jdbc.query(
				SQL_SELECT_ALL_WITHD_DATA_DEFINITION_INVERTER, 
				GeneratorRowWrapper.getInstance()
			);
	}
	
}

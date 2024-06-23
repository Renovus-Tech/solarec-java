package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GeneratorRowWrapper;
import tech.renovus.solarec.vo.db.data.GeneratorVo;

public abstract class BaseGeneratorDao <T extends GeneratorVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM generator";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM generator WHERE gen_id_auto = :gen_id_auto AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO generator (data_def_id, gen_rate_power, gen_coord_lat, gen_coord_lng, gen_data_date_max, gen_data_date_min, cli_id, loc_id, gen_cert_prov_data, gen_name, gen_description, gen_brand, gen_model, gen_serial_num, gen_flags, gen_code) VALUES (:data_def_id, :gen_rate_power, :gen_coord_lat, :gen_coord_lng, :gen_data_date_max, :gen_data_date_min, :cli_id, :loc_id, :gen_cert_prov_data, :gen_name, :gen_description, :gen_brand, :gen_model, :gen_serial_num, :gen_flags, :gen_code)";
	protected String SQL_UPDATE					= "UPDATE generator SET data_def_id = :data_def_id, gen_rate_power = :gen_rate_power, gen_coord_lat = :gen_coord_lat, gen_coord_lng = :gen_coord_lng, gen_data_date_max = :gen_data_date_max, gen_data_date_min = :gen_data_date_min, loc_id = :loc_id, gen_cert_prov_data = :gen_cert_prov_data, gen_name = :gen_name, gen_description = :gen_description, gen_brand = :gen_brand, gen_model = :gen_model, gen_serial_num = :gen_serial_num, gen_flags = :gen_flags, gen_code = :gen_code WHERE gen_id_auto = :gen_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM generator WHERE gen_id_auto = :gen_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (gen_id_auto, cli_id) DO UPDATE SET data_def_id = EXCLUDED.data_def_id, gen_rate_power = EXCLUDED.gen_rate_power, gen_coord_lat = EXCLUDED.gen_coord_lat, gen_coord_lng = EXCLUDED.gen_coord_lng, gen_data_date_max = EXCLUDED.gen_data_date_max, gen_data_date_min = EXCLUDED.gen_data_date_min, loc_id = EXCLUDED.loc_id, gen_cert_prov_data = EXCLUDED.gen_cert_prov_data, gen_name = EXCLUDED.gen_name, gen_description = EXCLUDED.gen_description, gen_brand = EXCLUDED.gen_brand, gen_model = EXCLUDED.gen_model, gen_serial_num = EXCLUDED.gen_serial_num, gen_flags = EXCLUDED.gen_flags, gen_code = EXCLUDED.gen_code";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"gen_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseGeneratorDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GeneratorVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GeneratorVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(GeneratorVo.COLUMN_GEN_RATE_POWER, vo.getGenRatePower())
			.addValue(GeneratorVo.COLUMN_GEN_COORD_LAT, vo.getGenCoordLat())
			.addValue(GeneratorVo.COLUMN_GEN_COORD_LNG, vo.getGenCoordLng())
			.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MAX, vo.getGenDataDateMax())
			.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MIN, vo.getGenDataDateMin())
			.addValue(GeneratorVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GeneratorVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(GeneratorVo.COLUMN_GEN_CERT_PROV_DATA, vo.getGenCertProvData())
			.addValue(GeneratorVo.COLUMN_GEN_NAME, vo.getGenName())
			.addValue(GeneratorVo.COLUMN_GEN_DESCRIPTION, vo.getGenDescription())
			.addValue(GeneratorVo.COLUMN_GEN_BRAND, vo.getGenBrand())
			.addValue(GeneratorVo.COLUMN_GEN_MODEL, vo.getGenModel())
			.addValue(GeneratorVo.COLUMN_GEN_SERIAL_NUM, vo.getGenSerialNum())
			.addValue(GeneratorVo.COLUMN_GEN_FLAGS, vo.getGenFlags())
			.addValue(GeneratorVo.COLUMN_GEN_CODE, vo.getGenCode());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GeneratorVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(GeneratorVo.COLUMN_GEN_RATE_POWER, vo.getGenRatePower())
			.addValue(GeneratorVo.COLUMN_GEN_COORD_LAT, vo.getGenCoordLat())
			.addValue(GeneratorVo.COLUMN_GEN_COORD_LNG, vo.getGenCoordLng())
			.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MAX, vo.getGenDataDateMax())
			.addValue(GeneratorVo.COLUMN_GEN_DATA_DATE_MIN, vo.getGenDataDateMin())
			.addValue(GeneratorVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(GeneratorVo.COLUMN_GEN_CERT_PROV_DATA, vo.getGenCertProvData())
			.addValue(GeneratorVo.COLUMN_GEN_NAME, vo.getGenName())
			.addValue(GeneratorVo.COLUMN_GEN_DESCRIPTION, vo.getGenDescription())
			.addValue(GeneratorVo.COLUMN_GEN_BRAND, vo.getGenBrand())
			.addValue(GeneratorVo.COLUMN_GEN_MODEL, vo.getGenModel())
			.addValue(GeneratorVo.COLUMN_GEN_SERIAL_NUM, vo.getGenSerialNum())
			.addValue(GeneratorVo.COLUMN_GEN_FLAGS, vo.getGenFlags())
			.addValue(GeneratorVo.COLUMN_GEN_CODE, vo.getGenCode())
			.addValue(GeneratorVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GeneratorVo.COLUMN_CLI_ID, vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getGenId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer genId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue(GeneratorVo.COLUMN_GEN_ID, genId)
			.addValue(GeneratorVo.COLUMN_CLI_ID, cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GeneratorRowWrapper.getInstance()); }
	public GeneratorVo findVo(Integer genId, Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(genId, cliId), GeneratorRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setGenId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getGenId(), vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
			default: 
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}

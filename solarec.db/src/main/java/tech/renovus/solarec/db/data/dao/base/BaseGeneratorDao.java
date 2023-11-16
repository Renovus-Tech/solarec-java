package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GeneratorRowWrapper;
import tech.renovus.solarec.vo.db.data.GeneratorVo;

public abstract class BaseGeneratorDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM generator";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM generator WHERE cli_id = :cli_id AND gen_id_auto = :gen_id_auto";
	protected String SQL_INSERT					= "INSERT INTO generator (cli_id,data_def_id,loc_id,gen_name,gen_description,gen_coord_lat,gen_coord_lng,gen_brand,gen_model,gen_serial_num,gen_rate_power,gen_flags,gen_code,gen_data_date_max,gen_data_date_min) VALUES (:cli_id,:data_def_id,:loc_id,:gen_name,:gen_description,:gen_coord_lat,:gen_coord_lng,:gen_brand,:gen_model,:gen_serial_num,:gen_rate_power,:gen_flags,:gen_code,:gen_data_date_max,:gen_data_date_min)";
	protected String SQL_UPDATE					= "UPDATE generator SET data_def_id = :data_def_id,loc_id = :loc_id,gen_name = :gen_name,gen_description = :gen_description,gen_coord_lat = :gen_coord_lat,gen_coord_lng = :gen_coord_lng,gen_brand = :gen_brand,gen_model = :gen_model,gen_serial_num = :gen_serial_num,gen_rate_power = :gen_rate_power,gen_flags = :gen_flags,gen_code = :gen_code,gen_data_date_max = :gen_data_date_max,gen_data_date_min = :gen_data_date_min WHERE cli_id = :cli_id AND gen_id_auto = :gen_id_auto";
	protected String SQL_DELETE					= "DELETE FROM generator WHERE cli_id = :cli_id AND gen_id_auto = :gen_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id_auto) DO UPDATE SET data_def_id = EXCLUDED.data_def_id, loc_id = EXCLUDED.loc_id, gen_name = EXCLUDED.gen_name, gen_description = EXCLUDED.gen_description, gen_coord_lat = EXCLUDED.gen_coord_lat, gen_coord_lng = EXCLUDED.gen_coord_lng, gen_brand = EXCLUDED.gen_brand, gen_model = EXCLUDED.gen_model, gen_serial_num = EXCLUDED.gen_serial_num, gen_rate_power = EXCLUDED.gen_rate_power, gen_flags = EXCLUDED.gen_flags, gen_code = EXCLUDED.gen_code, gen_data_date_max = EXCLUDED.gen_data_date_max, gen_data_date_min = EXCLUDED.gen_data_date_min";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"gen_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGeneratorDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(GeneratorVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_id", vo.getLocId())
			.addValue("gen_name", vo.getGenName())
			.addValue("gen_description", vo.getGenDescription())
			.addValue("gen_coord_lat", vo.getGenCoordLat())
			.addValue("gen_coord_lng", vo.getGenCoordLng())
			.addValue("gen_brand", vo.getGenBrand())
			.addValue("gen_model", vo.getGenModel())
			.addValue("gen_serial_num", vo.getGenSerialNum())
			.addValue("gen_rate_power", vo.getGenRatePower())
			.addValue("gen_flags", vo.getGenFlags())
			.addValue("gen_code", vo.getGenCode())
			.addValue("gen_data_date_max", vo.getGenDataDateMax())
			.addValue("gen_data_date_min", vo.getGenDataDateMin());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GeneratorVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_id", vo.getLocId())
			.addValue("gen_name", vo.getGenName())
			.addValue("gen_description", vo.getGenDescription())
			.addValue("gen_coord_lat", vo.getGenCoordLat())
			.addValue("gen_coord_lng", vo.getGenCoordLng())
			.addValue("gen_brand", vo.getGenBrand())
			.addValue("gen_model", vo.getGenModel())
			.addValue("gen_serial_num", vo.getGenSerialNum())
			.addValue("gen_rate_power", vo.getGenRatePower())
			.addValue("gen_flags", vo.getGenFlags())
			.addValue("gen_code", vo.getGenCode())
			.addValue("gen_data_date_max", vo.getGenDataDateMax())
			.addValue("gen_data_date_min", vo.getGenDataDateMin())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id_auto", vo.getGenId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GeneratorVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id_auto", genId);
	}

	//--- Public methods ------------------------
	public Collection<GeneratorVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GeneratorRowWrapper.getInstance()); }
	public GeneratorVo findVo(Integer cliId, Integer genId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId), GeneratorRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GeneratorVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setGenId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(GeneratorVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GeneratorVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GeneratorVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GeneratorVo.SYNC_INSERT: this.insert(vo); break;
			case GeneratorVo.SYNC_UPDATE: this.update(vo); break;
			case GeneratorVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GeneratorVo> vos) {
		if (vos == null) return;
		for (GeneratorVo vo : vos) this.synchronize(vo);
}


}


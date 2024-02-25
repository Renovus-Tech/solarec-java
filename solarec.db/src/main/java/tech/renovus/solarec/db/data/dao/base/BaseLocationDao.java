package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocationRowWrapper;
import tech.renovus.solarec.vo.db.data.LocationVo;

public abstract class BaseLocationDao <T extends LocationVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM location";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM location WHERE cli_id = :cli_id AND loc_id_auto = :loc_id_auto";
	protected String SQL_INSERT					= "INSERT INTO location (cli_id, data_def_id, loc_coord_lat, loc_coord_lng, loc_output_capacity, loc_output_total_capacity, loc_reference_density, loc_data_date_max, loc_data_date_min, loc_demo_date, loc_country_alpha_2, loc_name, loc_address, loc_state, loc_country, loc_flags, loc_code, loc_type, loc_gmt) VALUES (:cli_id, :data_def_id, :loc_coord_lat, :loc_coord_lng, :loc_output_capacity, :loc_output_total_capacity, :loc_reference_density, :loc_data_date_max, :loc_data_date_min, :loc_demo_date, :loc_country_alpha_2, :loc_name, :loc_address, :loc_state, :loc_country, :loc_flags, :loc_code, :loc_type, :loc_gmt)";
	protected String SQL_UPDATE					= "UPDATE location SET data_def_id = :data_def_id, loc_coord_lat = :loc_coord_lat, loc_coord_lng = :loc_coord_lng, loc_output_capacity = :loc_output_capacity, loc_output_total_capacity = :loc_output_total_capacity, loc_reference_density = :loc_reference_density, loc_data_date_max = :loc_data_date_max, loc_data_date_min = :loc_data_date_min, loc_demo_date = :loc_demo_date, loc_country_alpha_2 = :loc_country_alpha_2, loc_name = :loc_name, loc_address = :loc_address, loc_state = :loc_state, loc_country = :loc_country, loc_flags = :loc_flags, loc_code = :loc_code, loc_type = :loc_type, loc_gmt = :loc_gmt WHERE cli_id = :cli_id AND loc_id_auto = :loc_id_auto";
	protected String SQL_DELETE					= "DELETE FROM location WHERE cli_id = :cli_id AND loc_id_auto = :loc_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id_auto) DO UPDATE SET data_def_id = EXCLUDED.data_def_id, loc_coord_lat = EXCLUDED.loc_coord_lat, loc_coord_lng = EXCLUDED.loc_coord_lng, loc_output_capacity = EXCLUDED.loc_output_capacity, loc_output_total_capacity = EXCLUDED.loc_output_total_capacity, loc_reference_density = EXCLUDED.loc_reference_density, loc_data_date_max = EXCLUDED.loc_data_date_max, loc_data_date_min = EXCLUDED.loc_data_date_min, loc_demo_date = EXCLUDED.loc_demo_date, loc_country_alpha_2 = EXCLUDED.loc_country_alpha_2, loc_name = EXCLUDED.loc_name, loc_address = EXCLUDED.loc_address, loc_state = EXCLUDED.loc_state, loc_country = EXCLUDED.loc_country, loc_flags = EXCLUDED.loc_flags, loc_code = EXCLUDED.loc_code, loc_type = EXCLUDED.loc_type, loc_gmt = EXCLUDED.loc_gmt";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"loc_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id_auto", vo.getLocId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_coord_lat", vo.getLocCoordLat())
			.addValue("loc_coord_lng", vo.getLocCoordLng())
			.addValue("loc_output_capacity", vo.getLocOutputCapacity())
			.addValue("loc_output_total_capacity", vo.getLocOutputTotalCapacity())
			.addValue("loc_reference_density", vo.getLocReferenceDensity())
			.addValue("loc_data_date_max", vo.getLocDataDateMax())
			.addValue("loc_data_date_min", vo.getLocDataDateMin())
			.addValue("loc_demo_date", vo.getLocDemoDate())
			.addValue("loc_country_alpha_2", vo.getLocCountryAlpha2())
			.addValue("loc_name", vo.getLocName())
			.addValue("loc_address", vo.getLocAddress())
			.addValue("loc_state", vo.getLocState())
			.addValue("loc_country", vo.getLocCountry())
			.addValue("loc_flags", vo.getLocFlags())
			.addValue("loc_code", vo.getLocCode())
			.addValue("loc_type", vo.getLocType())
			.addValue("loc_gmt", vo.getLocGmt());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_coord_lat", vo.getLocCoordLat())
			.addValue("loc_coord_lng", vo.getLocCoordLng())
			.addValue("loc_output_capacity", vo.getLocOutputCapacity())
			.addValue("loc_output_total_capacity", vo.getLocOutputTotalCapacity())
			.addValue("loc_reference_density", vo.getLocReferenceDensity())
			.addValue("loc_data_date_max", vo.getLocDataDateMax())
			.addValue("loc_data_date_min", vo.getLocDataDateMin())
			.addValue("loc_demo_date", vo.getLocDemoDate())
			.addValue("loc_country_alpha_2", vo.getLocCountryAlpha2())
			.addValue("loc_name", vo.getLocName())
			.addValue("loc_address", vo.getLocAddress())
			.addValue("loc_state", vo.getLocState())
			.addValue("loc_country", vo.getLocCountry())
			.addValue("loc_flags", vo.getLocFlags())
			.addValue("loc_code", vo.getLocCode())
			.addValue("loc_type", vo.getLocType())
			.addValue("loc_gmt", vo.getLocGmt())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id_auto", vo.getLocId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id_auto", locId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocationRowWrapper.getInstance()); }
	public LocationVo findVo(Integer cliId, Integer locId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId), LocationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setLocId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}

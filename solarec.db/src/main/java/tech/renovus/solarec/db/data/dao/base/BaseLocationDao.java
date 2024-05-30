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
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM location WHERE loc_id_auto = :loc_id_auto AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO location (ctr_id, data_def_id, loc_output_capacity, loc_output_total_capacity, loc_reference_density, loc_data_date_max, loc_data_date_min, loc_demo_date, cli_id, loc_coord_lat, loc_coord_lng, loc_name, loc_address, loc_state, loc_type, loc_flags, loc_code, loc_gmt) VALUES (:ctr_id, :data_def_id, :loc_output_capacity, :loc_output_total_capacity, :loc_reference_density, :loc_data_date_max, :loc_data_date_min, :loc_demo_date, :cli_id, :loc_coord_lat, :loc_coord_lng, :loc_name, :loc_address, :loc_state, :loc_type, :loc_flags, :loc_code, :loc_gmt)";
	protected String SQL_UPDATE					= "UPDATE location SET ctr_id = :ctr_id, data_def_id = :data_def_id, loc_output_capacity = :loc_output_capacity, loc_output_total_capacity = :loc_output_total_capacity, loc_reference_density = :loc_reference_density, loc_data_date_max = :loc_data_date_max, loc_data_date_min = :loc_data_date_min, loc_demo_date = :loc_demo_date, loc_coord_lat = :loc_coord_lat, loc_coord_lng = :loc_coord_lng, loc_name = :loc_name, loc_address = :loc_address, loc_state = :loc_state, loc_type = :loc_type, loc_flags = :loc_flags, loc_code = :loc_code, loc_gmt = :loc_gmt WHERE loc_id_auto = :loc_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM location WHERE loc_id_auto = :loc_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (loc_id_auto, cli_id) DO UPDATE SET ctr_id = EXCLUDED.ctr_id, data_def_id = EXCLUDED.data_def_id, loc_output_capacity = EXCLUDED.loc_output_capacity, loc_output_total_capacity = EXCLUDED.loc_output_total_capacity, loc_reference_density = EXCLUDED.loc_reference_density, loc_data_date_max = EXCLUDED.loc_data_date_max, loc_data_date_min = EXCLUDED.loc_data_date_min, loc_demo_date = EXCLUDED.loc_demo_date, loc_coord_lat = EXCLUDED.loc_coord_lat, loc_coord_lng = EXCLUDED.loc_coord_lng, loc_name = EXCLUDED.loc_name, loc_address = EXCLUDED.loc_address, loc_state = EXCLUDED.loc_state, loc_type = EXCLUDED.loc_type, loc_flags = EXCLUDED.loc_flags, loc_code = EXCLUDED.loc_code, loc_gmt = EXCLUDED.loc_gmt";

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
			.addValue("ctr_id", vo.getCtrId())
			.addValue("loc_id_auto", vo.getLocId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_output_capacity", vo.getLocOutputCapacity())
			.addValue("loc_output_total_capacity", vo.getLocOutputTotalCapacity())
			.addValue("loc_reference_density", vo.getLocReferenceDensity())
			.addValue("loc_data_date_max", vo.getLocDataDateMax())
			.addValue("loc_data_date_min", vo.getLocDataDateMin())
			.addValue("loc_demo_date", vo.getLocDemoDate())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_coord_lat", vo.getLocCoordLat())
			.addValue("loc_coord_lng", vo.getLocCoordLng())
			.addValue("loc_name", vo.getLocName())
			.addValue("loc_address", vo.getLocAddress())
			.addValue("loc_state", vo.getLocState())
			.addValue("loc_type", vo.getLocType())
			.addValue("loc_flags", vo.getLocFlags())
			.addValue("loc_code", vo.getLocCode())
			.addValue("loc_gmt", vo.getLocGmt());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("ctr_id", vo.getCtrId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_output_capacity", vo.getLocOutputCapacity())
			.addValue("loc_output_total_capacity", vo.getLocOutputTotalCapacity())
			.addValue("loc_reference_density", vo.getLocReferenceDensity())
			.addValue("loc_data_date_max", vo.getLocDataDateMax())
			.addValue("loc_data_date_min", vo.getLocDataDateMin())
			.addValue("loc_demo_date", vo.getLocDemoDate())
			.addValue("loc_coord_lat", vo.getLocCoordLat())
			.addValue("loc_coord_lng", vo.getLocCoordLng())
			.addValue("loc_name", vo.getLocName())
			.addValue("loc_address", vo.getLocAddress())
			.addValue("loc_state", vo.getLocState())
			.addValue("loc_type", vo.getLocType())
			.addValue("loc_flags", vo.getLocFlags())
			.addValue("loc_code", vo.getLocCode())
			.addValue("loc_gmt", vo.getLocGmt())
			.addValue("loc_id_auto", vo.getLocId())
			.addValue("cli_id", vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getLocId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer locId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("loc_id_auto", locId)
			.addValue("cli_id", cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocationRowWrapper.getInstance()); }
	public LocationVo findVo(Integer locId, Integer cliId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(locId, cliId), LocationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setLocId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getLocId(), vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

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
	protected String SQL_INSERT					= "INSERT INTO location (loc_type_id, data_def_id, loc_output_total_capacity, loc_reference_density, loc_data_date_max, loc_data_date_min, loc_demo_date, ctr_id, cli_id, loc_coord_lat, loc_coord_lng, loc_output_capacity, loc_name, loc_address, loc_state, loc_type, loc_flags, loc_code, loc_gmt) VALUES (:loc_type_id, :data_def_id, :loc_output_total_capacity, :loc_reference_density, :loc_data_date_max, :loc_data_date_min, :loc_demo_date, :ctr_id, :cli_id, :loc_coord_lat, :loc_coord_lng, :loc_output_capacity, :loc_name, :loc_address, :loc_state, :loc_type, :loc_flags, :loc_code, :loc_gmt)";
	protected String SQL_UPDATE					= "UPDATE location SET loc_type_id = :loc_type_id, data_def_id = :data_def_id, loc_output_total_capacity = :loc_output_total_capacity, loc_reference_density = :loc_reference_density, loc_data_date_max = :loc_data_date_max, loc_data_date_min = :loc_data_date_min, loc_demo_date = :loc_demo_date, ctr_id = :ctr_id, loc_coord_lat = :loc_coord_lat, loc_coord_lng = :loc_coord_lng, loc_output_capacity = :loc_output_capacity, loc_name = :loc_name, loc_address = :loc_address, loc_state = :loc_state, loc_type = :loc_type, loc_flags = :loc_flags, loc_code = :loc_code, loc_gmt = :loc_gmt WHERE loc_id_auto = :loc_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM location WHERE loc_id_auto = :loc_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (loc_id_auto, cli_id) DO UPDATE SET loc_type_id = EXCLUDED.loc_type_id, data_def_id = EXCLUDED.data_def_id, loc_output_total_capacity = EXCLUDED.loc_output_total_capacity, loc_reference_density = EXCLUDED.loc_reference_density, loc_data_date_max = EXCLUDED.loc_data_date_max, loc_data_date_min = EXCLUDED.loc_data_date_min, loc_demo_date = EXCLUDED.loc_demo_date, ctr_id = EXCLUDED.ctr_id, loc_coord_lat = EXCLUDED.loc_coord_lat, loc_coord_lng = EXCLUDED.loc_coord_lng, loc_output_capacity = EXCLUDED.loc_output_capacity, loc_name = EXCLUDED.loc_name, loc_address = EXCLUDED.loc_address, loc_state = EXCLUDED.loc_state, loc_type = EXCLUDED.loc_type, loc_flags = EXCLUDED.loc_flags, loc_code = EXCLUDED.loc_code, loc_gmt = EXCLUDED.loc_gmt";

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
			.addValue(LocationVo.COLUMN_LOC_TYPE_ID, vo.getLocTypeId())
			.addValue(LocationVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocationVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(LocationVo.COLUMN_LOC_OUTPUT_TOTAL_CAPACITY, vo.getLocOutputTotalCapacity())
			.addValue(LocationVo.COLUMN_LOC_REFERENCE_DENSITY, vo.getLocReferenceDensity())
			.addValue(LocationVo.COLUMN_LOC_DATA_DATE_MAX, vo.getLocDataDateMax())
			.addValue(LocationVo.COLUMN_LOC_DATA_DATE_MIN, vo.getLocDataDateMin())
			.addValue(LocationVo.COLUMN_LOC_DEMO_DATE, vo.getLocDemoDate())
			.addValue(LocationVo.COLUMN_CTR_ID, vo.getCtrId())
			.addValue(LocationVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocationVo.COLUMN_LOC_COORD_LAT, vo.getLocCoordLat())
			.addValue(LocationVo.COLUMN_LOC_COORD_LNG, vo.getLocCoordLng())
			.addValue(LocationVo.COLUMN_LOC_OUTPUT_CAPACITY, vo.getLocOutputCapacity())
			.addValue(LocationVo.COLUMN_LOC_NAME, vo.getLocName())
			.addValue(LocationVo.COLUMN_LOC_ADDRESS, vo.getLocAddress())
			.addValue(LocationVo.COLUMN_LOC_STATE, vo.getLocState())
			.addValue(LocationVo.COLUMN_LOC_TYPE, vo.getLocType())
			.addValue(LocationVo.COLUMN_LOC_FLAGS, vo.getLocFlags())
			.addValue(LocationVo.COLUMN_LOC_CODE, vo.getLocCode())
			.addValue(LocationVo.COLUMN_LOC_GMT, vo.getLocGmt());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocationVo.COLUMN_LOC_TYPE_ID, vo.getLocTypeId())
			.addValue(LocationVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(LocationVo.COLUMN_LOC_OUTPUT_TOTAL_CAPACITY, vo.getLocOutputTotalCapacity())
			.addValue(LocationVo.COLUMN_LOC_REFERENCE_DENSITY, vo.getLocReferenceDensity())
			.addValue(LocationVo.COLUMN_LOC_DATA_DATE_MAX, vo.getLocDataDateMax())
			.addValue(LocationVo.COLUMN_LOC_DATA_DATE_MIN, vo.getLocDataDateMin())
			.addValue(LocationVo.COLUMN_LOC_DEMO_DATE, vo.getLocDemoDate())
			.addValue(LocationVo.COLUMN_CTR_ID, vo.getCtrId())
			.addValue(LocationVo.COLUMN_LOC_COORD_LAT, vo.getLocCoordLat())
			.addValue(LocationVo.COLUMN_LOC_COORD_LNG, vo.getLocCoordLng())
			.addValue(LocationVo.COLUMN_LOC_OUTPUT_CAPACITY, vo.getLocOutputCapacity())
			.addValue(LocationVo.COLUMN_LOC_NAME, vo.getLocName())
			.addValue(LocationVo.COLUMN_LOC_ADDRESS, vo.getLocAddress())
			.addValue(LocationVo.COLUMN_LOC_STATE, vo.getLocState())
			.addValue(LocationVo.COLUMN_LOC_TYPE, vo.getLocType())
			.addValue(LocationVo.COLUMN_LOC_FLAGS, vo.getLocFlags())
			.addValue(LocationVo.COLUMN_LOC_CODE, vo.getLocCode())
			.addValue(LocationVo.COLUMN_LOC_GMT, vo.getLocGmt())
			.addValue(LocationVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocationVo.COLUMN_CLI_ID, vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getLocId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer locId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue(LocationVo.COLUMN_LOC_ID, locId)
			.addValue(LocationVo.COLUMN_CLI_ID, cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocationRowWrapper.getInstance()); }
	public LocationVo findVo(Integer locId, Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(locId, cliId), LocationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setLocId(Integer.valueOf(key.intValue()));
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

package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StationRowWrapper;
import tech.renovus.solarec.vo.db.data.StationVo;

public abstract class BaseStationDao <T extends StationVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM station";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM station WHERE sta_id_auto = :sta_id_auto AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO station (sta_data_date_min, data_def_id, loc_id, sta_coord_lat, sta_coord_lng, sta_data_date_max, cli_id, sta_name, sta_description, sta_flags, sta_code) VALUES (:sta_data_date_min, :data_def_id, :loc_id, :sta_coord_lat, :sta_coord_lng, :sta_data_date_max, :cli_id, :sta_name, :sta_description, :sta_flags, :sta_code)";
	protected String SQL_UPDATE					= "UPDATE station SET sta_data_date_min = :sta_data_date_min, data_def_id = :data_def_id, loc_id = :loc_id, sta_coord_lat = :sta_coord_lat, sta_coord_lng = :sta_coord_lng, sta_data_date_max = :sta_data_date_max, sta_name = :sta_name, sta_description = :sta_description, sta_flags = :sta_flags, sta_code = :sta_code WHERE sta_id_auto = :sta_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM station WHERE sta_id_auto = :sta_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (sta_id_auto, cli_id) DO UPDATE SET sta_data_date_min = EXCLUDED.sta_data_date_min, data_def_id = EXCLUDED.data_def_id, loc_id = EXCLUDED.loc_id, sta_coord_lat = EXCLUDED.sta_coord_lat, sta_coord_lng = EXCLUDED.sta_coord_lng, sta_data_date_max = EXCLUDED.sta_data_date_max, sta_name = EXCLUDED.sta_name, sta_description = EXCLUDED.sta_description, sta_flags = EXCLUDED.sta_flags, sta_code = EXCLUDED.sta_code";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"sta_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("sta_data_date_min", vo.getStaDataDateMin())
			.addValue("sta_id_auto", vo.getStaId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_id", vo.getLocId())
			.addValue("sta_coord_lat", vo.getStaCoordLat())
			.addValue("sta_coord_lng", vo.getStaCoordLng())
			.addValue("sta_data_date_max", vo.getStaDataDateMax())
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_name", vo.getStaName())
			.addValue("sta_description", vo.getStaDescription())
			.addValue("sta_flags", vo.getStaFlags())
			.addValue("sta_code", vo.getStaCode());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("sta_data_date_min", vo.getStaDataDateMin())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_id", vo.getLocId())
			.addValue("sta_coord_lat", vo.getStaCoordLat())
			.addValue("sta_coord_lng", vo.getStaCoordLng())
			.addValue("sta_data_date_max", vo.getStaDataDateMax())
			.addValue("sta_name", vo.getStaName())
			.addValue("sta_description", vo.getStaDescription())
			.addValue("sta_flags", vo.getStaFlags())
			.addValue("sta_code", vo.getStaCode())
			.addValue("sta_id_auto", vo.getStaId())
			.addValue("cli_id", vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getStaId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer staId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("sta_id_auto", staId)
			.addValue("cli_id", cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StationRowWrapper.getInstance()); }
	public StationVo findVo(Integer staId, Integer cliId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(staId, cliId), StationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setStaId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getStaId(), vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

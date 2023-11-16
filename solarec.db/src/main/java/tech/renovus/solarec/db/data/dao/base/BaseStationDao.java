package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StationRowWrapper;
import tech.renovus.solarec.vo.db.data.StationVo;

public abstract class BaseStationDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM station";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM station WHERE cli_id = :cli_id AND sta_id_auto = :sta_id_auto";
	protected String SQL_INSERT					= "INSERT INTO station (cli_id,data_def_id,loc_id,sta_name,sta_description,sta_coord_lat,sta_coord_lng,sta_flags,sta_code,sta_data_date_max,sta_data_date_min) VALUES (:cli_id,:data_def_id,:loc_id,:sta_name,:sta_description,:sta_coord_lat,:sta_coord_lng,:sta_flags,:sta_code,:sta_data_date_max,:sta_data_date_min)";
	protected String SQL_UPDATE					= "UPDATE station SET data_def_id = :data_def_id,loc_id = :loc_id,sta_name = :sta_name,sta_description = :sta_description,sta_coord_lat = :sta_coord_lat,sta_coord_lng = :sta_coord_lng,sta_flags = :sta_flags,sta_code = :sta_code,sta_data_date_max = :sta_data_date_max,sta_data_date_min = :sta_data_date_min WHERE cli_id = :cli_id AND sta_id_auto = :sta_id_auto";
	protected String SQL_DELETE					= "DELETE FROM station WHERE cli_id = :cli_id AND sta_id_auto = :sta_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id_auto) DO UPDATE SET data_def_id = EXCLUDED.data_def_id, loc_id = EXCLUDED.loc_id, sta_name = EXCLUDED.sta_name, sta_description = EXCLUDED.sta_description, sta_coord_lat = EXCLUDED.sta_coord_lat, sta_coord_lng = EXCLUDED.sta_coord_lng, sta_flags = EXCLUDED.sta_flags, sta_code = EXCLUDED.sta_code, sta_data_date_max = EXCLUDED.sta_data_date_max, sta_data_date_min = EXCLUDED.sta_data_date_min";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"sta_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StationVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_id", vo.getLocId())
			.addValue("sta_name", vo.getStaName())
			.addValue("sta_description", vo.getStaDescription())
			.addValue("sta_coord_lat", vo.getStaCoordLat())
			.addValue("sta_coord_lng", vo.getStaCoordLng())
			.addValue("sta_flags", vo.getStaFlags())
			.addValue("sta_code", vo.getStaCode())
			.addValue("sta_data_date_max", vo.getStaDataDateMax())
			.addValue("sta_data_date_min", vo.getStaDataDateMin());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StationVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("loc_id", vo.getLocId())
			.addValue("sta_name", vo.getStaName())
			.addValue("sta_description", vo.getStaDescription())
			.addValue("sta_coord_lat", vo.getStaCoordLat())
			.addValue("sta_coord_lng", vo.getStaCoordLng())
			.addValue("sta_flags", vo.getStaFlags())
			.addValue("sta_code", vo.getStaCode())
			.addValue("sta_data_date_max", vo.getStaDataDateMax())
			.addValue("sta_data_date_min", vo.getStaDataDateMin())
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id_auto", vo.getStaId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StationVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("sta_id_auto", staId);
	}

	//--- Public methods ------------------------
	public Collection<StationVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StationRowWrapper.getInstance()); }
	public StationVo findVo(Integer cliId, Integer staId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId), StationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StationVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setStaId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(StationVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StationVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StationVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StationVo.SYNC_INSERT: this.insert(vo); break;
			case StationVo.SYNC_UPDATE: this.update(vo); break;
			case StationVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StationVo> vos) {
		if (vos == null) return;
		for (StationVo vo : vos) this.synchronize(vo);
}


}


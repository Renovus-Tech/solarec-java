package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocWeatherDataRowWrapper;
import tech.renovus.solarec.db.data.vo.LocWeatherDataVo;

public abstract class BaseLocWeatherDataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_weather_data";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_weather_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id_auto = :loc_wea_data_id_auto";
	protected String SQL_INSERT					= "INSERT INTO loc_weather_data (cli_id,loc_id,loc_wea_data_date,loc_wea_data_resonse_status,loc_wea_data_response) VALUES (:cli_id,:loc_id,:loc_wea_data_date,:loc_wea_data_resonse_status,:loc_wea_data_response)";
	protected String SQL_UPDATE					= "UPDATE loc_weather_data SET loc_wea_data_date = :loc_wea_data_date,loc_wea_data_resonse_status = :loc_wea_data_resonse_status,loc_wea_data_response = :loc_wea_data_response WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id_auto = :loc_wea_data_id_auto";
	protected String SQL_DELETE					= "DELETE FROM loc_weather_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id_auto = :loc_wea_data_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, loc_wea_data_id_auto) DO UPDATE SET loc_wea_data_date = EXCLUDED.loc_wea_data_date, loc_wea_data_resonse_status = EXCLUDED.loc_wea_data_resonse_status, loc_wea_data_response = EXCLUDED.loc_wea_data_response";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"loc_wea_data_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocWeatherDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocWeatherDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_wea_data_date", vo.getLocWeaDataDate())
			.addValue("loc_wea_data_resonse_status", vo.getLocWeaDataResonseStatus())
			.addValue("loc_wea_data_response", vo.getLocWeaDataResponse());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocWeatherDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("loc_wea_data_date", vo.getLocWeaDataDate())
			.addValue("loc_wea_data_resonse_status", vo.getLocWeaDataResonseStatus())
			.addValue("loc_wea_data_response", vo.getLocWeaDataResponse())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_wea_data_id_auto", vo.getLocWeaDataId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocWeatherDataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getLocWeaDataId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer locWeaDataId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("loc_wea_data_id_auto", locWeaDataId);
	}

	//--- Public methods ------------------------
	public Collection<LocWeatherDataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocWeatherDataRowWrapper.getInstance()); }
	public LocWeatherDataVo findVo(Integer cliId, Integer locId, Integer locWeaDataId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, locWeaDataId), LocWeatherDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocWeatherDataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setLocWeaDataId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(LocWeatherDataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocWeatherDataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocWeatherDataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocWeatherDataVo.SYNC_INSERT: this.insert(vo); break;
			case LocWeatherDataVo.SYNC_UPDATE: this.update(vo); break;
			case LocWeatherDataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocWeatherDataVo> vos) {
		if (vos == null) return;
		for (LocWeatherDataVo vo : vos) this.synchronize(vo);
}


}


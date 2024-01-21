package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocWeatherDataRowWrapper;
import tech.renovus.solarec.vo.db.data.LocWeatherDataVo;

public abstract class BaseLocWeatherDataDao <T extends LocWeatherDataVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_weather_data";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_weather_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id_auto = :loc_wea_data_id_auto";
	protected String SQL_INSERT					= "INSERT INTO loc_weather_data (cli_id, loc_id, loc_wea_data_date, loc_wea_data_resonse_status, loc_wea_data_response) VALUES (:cli_id, :loc_id, :loc_wea_data_date, :loc_wea_data_resonse_status, :loc_wea_data_response)";
	protected String SQL_UPDATE					= "UPDATE loc_weather_data SET loc_wea_data_date = :loc_wea_data_date, loc_wea_data_resonse_status = :loc_wea_data_resonse_status, loc_wea_data_response = :loc_wea_data_response WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id_auto = :loc_wea_data_id_auto";
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
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_wea_data_id_auto", vo.getLocWeaDataId())
			.addValue("loc_wea_data_date", vo.getLocWeaDataDate())
			.addValue("loc_wea_data_resonse_status", vo.getLocWeaDataResonseStatus())
			.addValue("loc_wea_data_response", vo.getLocWeaDataResponse());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("loc_wea_data_date", vo.getLocWeaDataDate())
			.addValue("loc_wea_data_resonse_status", vo.getLocWeaDataResonseStatus())
			.addValue("loc_wea_data_response", vo.getLocWeaDataResponse())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_wea_data_id_auto", vo.getLocWeaDataId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getLocWeaDataId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer locWeaDataId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("loc_wea_data_id_auto", locWeaDataId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocWeatherDataRowWrapper.getInstance()); }
	public LocWeatherDataVo findVo(Integer cliId, Integer locId, Integer locWeaDataId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, locWeaDataId), LocWeatherDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setLocWeaDataId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
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

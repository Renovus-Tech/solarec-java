package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.WeaDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.WeaDefinitionVo;

public abstract class BaseWeaDefinitionDao <T extends WeaDefinitionVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM wea_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM wea_definition WHERE cli_id = :cli_id AND wea_id_auto = :wea_id_auto";
	protected String SQL_INSERT					= "INSERT INTO wea_definition (cli_id, wea_coord_lng, wea_check_type, wea_check_frequency, wea_coord_lat, wea_name, wea_description, wea_flags) VALUES (:cli_id, :wea_coord_lng, :wea_check_type, :wea_check_frequency, :wea_coord_lat, :wea_name, :wea_description, :wea_flags)";
	protected String SQL_UPDATE					= "UPDATE wea_definition SET wea_coord_lng = :wea_coord_lng, wea_check_type = :wea_check_type, wea_check_frequency = :wea_check_frequency, wea_coord_lat = :wea_coord_lat, wea_name = :wea_name, wea_description = :wea_description, wea_flags = :wea_flags WHERE cli_id = :cli_id AND wea_id_auto = :wea_id_auto";
	protected String SQL_DELETE					= "DELETE FROM wea_definition WHERE cli_id = :cli_id AND wea_id_auto = :wea_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, wea_id_auto) DO UPDATE SET wea_coord_lng = EXCLUDED.wea_coord_lng, wea_check_type = EXCLUDED.wea_check_type, wea_check_frequency = EXCLUDED.wea_check_frequency, wea_coord_lat = EXCLUDED.wea_coord_lat, wea_name = EXCLUDED.wea_name, wea_description = EXCLUDED.wea_description, wea_flags = EXCLUDED.wea_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"wea_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseWeaDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(WeaDefinitionVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(WeaDefinitionVo.COLUMN_WEA_ID, vo.getWeaId())
			.addValue(WeaDefinitionVo.COLUMN_WEA_COORD_LNG, vo.getWeaCoordLng())
			.addValue(WeaDefinitionVo.COLUMN_WEA_CHECK_TYPE, vo.getWeaCheckType())
			.addValue(WeaDefinitionVo.COLUMN_WEA_CHECK_FREQUENCY, vo.getWeaCheckFrequency())
			.addValue(WeaDefinitionVo.COLUMN_WEA_COORD_LAT, vo.getWeaCoordLat())
			.addValue(WeaDefinitionVo.COLUMN_WEA_NAME, vo.getWeaName())
			.addValue(WeaDefinitionVo.COLUMN_WEA_DESCRIPTION, vo.getWeaDescription())
			.addValue(WeaDefinitionVo.COLUMN_WEA_FLAGS, vo.getWeaFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(WeaDefinitionVo.COLUMN_WEA_COORD_LNG, vo.getWeaCoordLng())
			.addValue(WeaDefinitionVo.COLUMN_WEA_CHECK_TYPE, vo.getWeaCheckType())
			.addValue(WeaDefinitionVo.COLUMN_WEA_CHECK_FREQUENCY, vo.getWeaCheckFrequency())
			.addValue(WeaDefinitionVo.COLUMN_WEA_COORD_LAT, vo.getWeaCoordLat())
			.addValue(WeaDefinitionVo.COLUMN_WEA_NAME, vo.getWeaName())
			.addValue(WeaDefinitionVo.COLUMN_WEA_DESCRIPTION, vo.getWeaDescription())
			.addValue(WeaDefinitionVo.COLUMN_WEA_FLAGS, vo.getWeaFlags())
			.addValue(WeaDefinitionVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(WeaDefinitionVo.COLUMN_WEA_ID, vo.getWeaId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getWeaId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer weaId) {
		return new MapSqlParameterSource()
			.addValue(WeaDefinitionVo.COLUMN_CLI_ID, cliId)
			.addValue(WeaDefinitionVo.COLUMN_WEA_ID, weaId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, WeaDefinitionRowWrapper.getInstance()); }
	public WeaDefinitionVo findVo(Integer cliId, Integer weaId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, weaId), WeaDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setWeaId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getWeaId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

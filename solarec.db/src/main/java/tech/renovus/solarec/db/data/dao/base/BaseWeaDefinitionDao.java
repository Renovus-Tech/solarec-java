package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.WeaDefinitionRowWrapper;
import tech.renovus.solarec.db.data.vo.WeaDefinitionVo;

public abstract class BaseWeaDefinitionDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM wea_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM wea_definition WHERE cli_id = :cli_id AND wea_id_auto = :wea_id_auto";
	protected String SQL_INSERT					= "INSERT INTO wea_definition (cli_id,wea_name,wea_description,wea_coord_lat,wea_coord_lng,wea_check_type,wea_check_frequency,wea_flags) VALUES (:cli_id,:wea_name,:wea_description,:wea_coord_lat,:wea_coord_lng,:wea_check_type,:wea_check_frequency,:wea_flags)";
	protected String SQL_UPDATE					= "UPDATE wea_definition SET wea_name = :wea_name,wea_description = :wea_description,wea_coord_lat = :wea_coord_lat,wea_coord_lng = :wea_coord_lng,wea_check_type = :wea_check_type,wea_check_frequency = :wea_check_frequency,wea_flags = :wea_flags WHERE cli_id = :cli_id AND wea_id_auto = :wea_id_auto";
	protected String SQL_DELETE					= "DELETE FROM wea_definition WHERE cli_id = :cli_id AND wea_id_auto = :wea_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, wea_id_auto) DO UPDATE SET wea_name = EXCLUDED.wea_name, wea_description = EXCLUDED.wea_description, wea_coord_lat = EXCLUDED.wea_coord_lat, wea_coord_lng = EXCLUDED.wea_coord_lng, wea_check_type = EXCLUDED.wea_check_type, wea_check_frequency = EXCLUDED.wea_check_frequency, wea_flags = EXCLUDED.wea_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"wea_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseWeaDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(WeaDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("wea_name", vo.getWeaName())
			.addValue("wea_description", vo.getWeaDescription())
			.addValue("wea_coord_lat", vo.getWeaCoordLat())
			.addValue("wea_coord_lng", vo.getWeaCoordLng())
			.addValue("wea_check_type", vo.getWeaCheckType())
			.addValue("wea_check_frequency", vo.getWeaCheckFrequency())
			.addValue("wea_flags", vo.getWeaFlags());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(WeaDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("wea_name", vo.getWeaName())
			.addValue("wea_description", vo.getWeaDescription())
			.addValue("wea_coord_lat", vo.getWeaCoordLat())
			.addValue("wea_coord_lng", vo.getWeaCoordLng())
			.addValue("wea_check_type", vo.getWeaCheckType())
			.addValue("wea_check_frequency", vo.getWeaCheckFrequency())
			.addValue("wea_flags", vo.getWeaFlags())
			.addValue("cli_id", vo.getCliId())
			.addValue("wea_id_auto", vo.getWeaId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(WeaDefinitionVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getWeaId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer weaId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("wea_id_auto", weaId);
	}

	//--- Public methods ------------------------
	public Collection<WeaDefinitionVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, WeaDefinitionRowWrapper.getInstance()); }
	public WeaDefinitionVo findVo(Integer cliId, Integer weaId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, weaId), WeaDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(WeaDefinitionVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setWeaId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(WeaDefinitionVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(WeaDefinitionVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(WeaDefinitionVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case WeaDefinitionVo.SYNC_INSERT: this.insert(vo); break;
			case WeaDefinitionVo.SYNC_UPDATE: this.update(vo); break;
			case WeaDefinitionVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<WeaDefinitionVo> vos) {
		if (vos == null) return;
		for (WeaDefinitionVo vo : vos) this.synchronize(vo);
}


}


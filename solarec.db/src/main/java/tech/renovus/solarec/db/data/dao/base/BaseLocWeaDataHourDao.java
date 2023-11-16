package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocWeaDataHourRowWrapper;
import tech.renovus.solarec.vo.db.data.LocWeaDataHourVo;

public abstract class BaseLocWeaDataHourDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_wea_data_hour";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_wea_data_hour WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id = :loc_wea_data_id AND loc_wea_data_hour = :loc_wea_data_hour";
	protected String SQL_INSERT					= "INSERT INTO loc_wea_data_hour (cli_id,loc_id,loc_wea_data_id,loc_wea_data_hour,loc_wea_data_response,loc_wea_data_retrieve) VALUES (:cli_id,:loc_id,:loc_wea_data_id,:loc_wea_data_hour,:loc_wea_data_response,:loc_wea_data_retrieve)";
	protected String SQL_UPDATE					= "UPDATE loc_wea_data_hour SET loc_wea_data_response = :loc_wea_data_response,loc_wea_data_retrieve = :loc_wea_data_retrieve WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id = :loc_wea_data_id AND loc_wea_data_hour = :loc_wea_data_hour";
	protected String SQL_DELETE					= "DELETE FROM loc_wea_data_hour WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id = :loc_wea_data_id AND loc_wea_data_hour = :loc_wea_data_hour";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, loc_wea_data_id, loc_wea_data_hour) DO UPDATE SET loc_wea_data_response = EXCLUDED.loc_wea_data_response, loc_wea_data_retrieve = EXCLUDED.loc_wea_data_retrieve";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocWeaDataHourDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocWeaDataHourVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_wea_data_id", vo.getLocWeaDataId())
			.addValue("loc_wea_data_hour", vo.getLocWeaDataHour())
			.addValue("loc_wea_data_response", vo.getLocWeaDataResponse())
			.addValue("loc_wea_data_retrieve", vo.getLocWeaDataRetrieve());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocWeaDataHourVo vo) {
		return new MapSqlParameterSource()
			.addValue("loc_wea_data_response", vo.getLocWeaDataResponse())
			.addValue("loc_wea_data_retrieve", vo.getLocWeaDataRetrieve())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_wea_data_id", vo.getLocWeaDataId())
			.addValue("loc_wea_data_hour", vo.getLocWeaDataHour());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocWeaDataHourVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getLocWeaDataId(), vo.getLocWeaDataHour());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("loc_wea_data_id", locWeaDataId)
			.addValue("loc_wea_data_hour", locWeaDataHour);
	}

	//--- Public methods ------------------------
	public Collection<LocWeaDataHourVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocWeaDataHourRowWrapper.getInstance()); }
	public LocWeaDataHourVo findVo(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, locWeaDataId, locWeaDataHour), LocWeaDataHourRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocWeaDataHourVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocWeaDataHourVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocWeaDataHourVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocWeaDataHourVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocWeaDataHourVo.SYNC_INSERT: this.insert(vo); break;
			case LocWeaDataHourVo.SYNC_UPDATE: this.update(vo); break;
			case LocWeaDataHourVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocWeaDataHourVo> vos) {
		if (vos == null) return;
		for (LocWeaDataHourVo vo : vos) this.synchronize(vo);
}


}


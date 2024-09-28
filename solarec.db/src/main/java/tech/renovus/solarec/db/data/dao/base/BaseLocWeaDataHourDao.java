package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocWeaDataHourRowWrapper;
import tech.renovus.solarec.vo.db.data.LocWeaDataHourVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseLocWeaDataHourDao <T extends LocWeaDataHourVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM loc_wea_data_hour";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_wea_data_hour WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id = :loc_wea_data_id AND loc_wea_data_hour = :loc_wea_data_hour";
	protected String SQL_INSERT					= "INSERT INTO loc_wea_data_hour (cli_id, loc_id, loc_wea_data_id, loc_wea_data_hour, loc_wea_data_retrieve, loc_wea_data_response) VALUES (:cli_id, :loc_id, :loc_wea_data_id, :loc_wea_data_hour, :loc_wea_data_retrieve, :loc_wea_data_response)";
	protected String SQL_UPDATE					= "UPDATE loc_wea_data_hour SET loc_wea_data_retrieve = :loc_wea_data_retrieve, loc_wea_data_response = :loc_wea_data_response WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id = :loc_wea_data_id AND loc_wea_data_hour = :loc_wea_data_hour";
	protected String SQL_DELETE					= "DELETE FROM loc_wea_data_hour WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_wea_data_id = :loc_wea_data_id AND loc_wea_data_hour = :loc_wea_data_hour";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, loc_wea_data_id, loc_wea_data_hour) DO UPDATE SET loc_wea_data_retrieve = EXCLUDED.loc_wea_data_retrieve, loc_wea_data_response = EXCLUDED.loc_wea_data_response";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseLocWeaDataHourDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocWeaDataHourVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_ID, vo.getLocWeaDataId())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_HOUR, vo.getLocWeaDataHour())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_RETRIEVE, vo.getLocWeaDataRetrieve())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_RESPONSE, vo.getLocWeaDataResponse());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_RETRIEVE, vo.getLocWeaDataRetrieve())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_RESPONSE, vo.getLocWeaDataResponse())
			.addValue(LocWeaDataHourVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_ID, vo.getLocWeaDataId())
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_HOUR, vo.getLocWeaDataHour());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getLocWeaDataId(), vo.getLocWeaDataHour());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour) {
		return new MapSqlParameterSource()
			.addValue(LocWeaDataHourVo.COLUMN_CLI_ID, cliId)
			.addValue(LocWeaDataHourVo.COLUMN_LOC_ID, locId)
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_ID, locWeaDataId)
			.addValue(LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_HOUR, locWeaDataHour);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocWeaDataHourRowWrapper.getInstance()); }
	public LocWeaDataHourVo findVo(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, locWeaDataId, locWeaDataHour), LocWeaDataHourRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getLocWeaDataId(), vo.getLocWeaDataHour()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
			default: 
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}

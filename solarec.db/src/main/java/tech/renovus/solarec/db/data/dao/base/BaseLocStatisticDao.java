package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocStatisticRowWrapper;
import tech.renovus.solarec.vo.db.data.LocStatisticVo;

public abstract class BaseLocStatisticDao <T extends LocStatisticVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM loc_statistic";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_statistic WHERE cli_id = :cli_id AND loc_id = :loc_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_statistic (cli_id, loc_id, stat_date, stat_type_id, stat_pro_id, stat_value, stat_date_added) VALUES (:cli_id, :loc_id, :stat_date, :stat_type_id, :stat_pro_id, :stat_value, :stat_date_added)";
	protected String SQL_UPDATE					= "UPDATE loc_statistic SET stat_pro_id = :stat_pro_id, stat_value = :stat_value, stat_date_added = :stat_date_added WHERE cli_id = :cli_id AND loc_id = :loc_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_statistic WHERE cli_id = :cli_id AND loc_id = :loc_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, stat_date, stat_type_id) DO UPDATE SET stat_pro_id = EXCLUDED.stat_pro_id, stat_value = EXCLUDED.stat_value, stat_date_added = EXCLUDED.stat_date_added";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseLocStatisticDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocStatisticVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocStatisticVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocStatisticVo.COLUMN_STAT_DATE, vo.getStatDate())
			.addValue(LocStatisticVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId())
			.addValue(LocStatisticVo.COLUMN_STAT_PRO_ID, vo.getStatProId())
			.addValue(LocStatisticVo.COLUMN_STAT_VALUE, vo.getStatValue())
			.addValue(LocStatisticVo.COLUMN_STAT_DATE_ADDED, vo.getStatDateAdded());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocStatisticVo.COLUMN_STAT_PRO_ID, vo.getStatProId())
			.addValue(LocStatisticVo.COLUMN_STAT_VALUE, vo.getStatValue())
			.addValue(LocStatisticVo.COLUMN_STAT_DATE_ADDED, vo.getStatDateAdded())
			.addValue(LocStatisticVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocStatisticVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocStatisticVo.COLUMN_STAT_DATE, vo.getStatDate())
			.addValue(LocStatisticVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getStatDate(), vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue(LocStatisticVo.COLUMN_CLI_ID, cliId)
			.addValue(LocStatisticVo.COLUMN_LOC_ID, locId)
			.addValue(LocStatisticVo.COLUMN_STAT_DATE, statDate)
			.addValue(LocStatisticVo.COLUMN_STAT_TYPE_ID, statTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocStatisticRowWrapper.getInstance()); }
	public LocStatisticVo findVo(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, statDate, statTypeId), LocStatisticRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getStatDate(), vo.getStatTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

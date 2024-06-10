package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaStatisticRowWrapper;
import tech.renovus.solarec.vo.db.data.StaStatisticVo;

public abstract class BaseStaStatisticDao <T extends StaStatisticVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM sta_statistic";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_statistic WHERE cli_id = :cli_id AND sta_id = :sta_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO sta_statistic (cli_id, sta_id, stat_date, stat_type_id, stat_pro_id, stat_value, stat_date_added) VALUES (:cli_id, :sta_id, :stat_date, :stat_type_id, :stat_pro_id, :stat_value, :stat_date_added)";
	protected String SQL_UPDATE					= "UPDATE sta_statistic SET stat_pro_id = :stat_pro_id, stat_value = :stat_value, stat_date_added = :stat_date_added WHERE cli_id = :cli_id AND sta_id = :sta_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM sta_statistic WHERE cli_id = :cli_id AND sta_id = :sta_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, stat_date, stat_type_id) DO UPDATE SET stat_pro_id = EXCLUDED.stat_pro_id, stat_value = EXCLUDED.stat_value, stat_date_added = EXCLUDED.stat_date_added";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStaStatisticDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaStatisticVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaStatisticVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaStatisticVo.COLUMN_STAT_DATE, vo.getStatDate())
			.addValue(StaStatisticVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId())
			.addValue(StaStatisticVo.COLUMN_STAT_PRO_ID, vo.getStatProId())
			.addValue(StaStatisticVo.COLUMN_STAT_VALUE, vo.getStatValue())
			.addValue(StaStatisticVo.COLUMN_STAT_DATE_ADDED, vo.getStatDateAdded());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaStatisticVo.COLUMN_STAT_PRO_ID, vo.getStatProId())
			.addValue(StaStatisticVo.COLUMN_STAT_VALUE, vo.getStatValue())
			.addValue(StaStatisticVo.COLUMN_STAT_DATE_ADDED, vo.getStatDateAdded())
			.addValue(StaStatisticVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaStatisticVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaStatisticVo.COLUMN_STAT_DATE, vo.getStatDate())
			.addValue(StaStatisticVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getStatDate(), vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, java.util.Date statDate, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue(StaStatisticVo.COLUMN_CLI_ID, cliId)
			.addValue(StaStatisticVo.COLUMN_STA_ID, staId)
			.addValue(StaStatisticVo.COLUMN_STAT_DATE, statDate)
			.addValue(StaStatisticVo.COLUMN_STAT_TYPE_ID, statTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StaStatisticRowWrapper.getInstance()); }
	public StaStatisticVo findVo(Integer cliId, Integer staId, java.util.Date statDate, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, statDate, statTypeId), StaStatisticRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getStaId(), vo.getStatDate(), vo.getStatTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

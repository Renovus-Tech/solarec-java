package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenStatisticRowWrapper;
import tech.renovus.solarec.vo.db.data.GenStatisticVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseGenStatisticDao <T extends GenStatisticVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM gen_statistic";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_statistic WHERE cli_id = :cli_id AND gen_id = :gen_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO gen_statistic (cli_id, gen_id, stat_date, stat_type_id, stat_pro_id, stat_value, stat_date_added) VALUES (:cli_id, :gen_id, :stat_date, :stat_type_id, :stat_pro_id, :stat_value, :stat_date_added)";
	protected String SQL_UPDATE					= "UPDATE gen_statistic SET stat_pro_id = :stat_pro_id, stat_value = :stat_value, stat_date_added = :stat_date_added WHERE cli_id = :cli_id AND gen_id = :gen_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM gen_statistic WHERE cli_id = :cli_id AND gen_id = :gen_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, stat_date, stat_type_id) DO UPDATE SET stat_pro_id = EXCLUDED.stat_pro_id, stat_value = EXCLUDED.stat_value, stat_date_added = EXCLUDED.stat_date_added";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseGenStatisticDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenStatisticVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenStatisticVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenStatisticVo.COLUMN_STAT_DATE, vo.getStatDate())
			.addValue(GenStatisticVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId())
			.addValue(GenStatisticVo.COLUMN_STAT_PRO_ID, vo.getStatProId())
			.addValue(GenStatisticVo.COLUMN_STAT_VALUE, vo.getStatValue())
			.addValue(GenStatisticVo.COLUMN_STAT_DATE_ADDED, vo.getStatDateAdded());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenStatisticVo.COLUMN_STAT_PRO_ID, vo.getStatProId())
			.addValue(GenStatisticVo.COLUMN_STAT_VALUE, vo.getStatValue())
			.addValue(GenStatisticVo.COLUMN_STAT_DATE_ADDED, vo.getStatDateAdded())
			.addValue(GenStatisticVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenStatisticVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenStatisticVo.COLUMN_STAT_DATE, vo.getStatDate())
			.addValue(GenStatisticVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getStatDate(), vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue(GenStatisticVo.COLUMN_CLI_ID, cliId)
			.addValue(GenStatisticVo.COLUMN_GEN_ID, genId)
			.addValue(GenStatisticVo.COLUMN_STAT_DATE, statDate)
			.addValue(GenStatisticVo.COLUMN_STAT_TYPE_ID, statTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenStatisticRowWrapper.getInstance()); }
	public GenStatisticVo findVo(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, statDate, statTypeId), GenStatisticRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getStatDate(), vo.getStatTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

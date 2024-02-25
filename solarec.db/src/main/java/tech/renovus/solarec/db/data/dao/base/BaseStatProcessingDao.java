package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatProcessingRowWrapper;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;

public abstract class BaseStatProcessingDao <T extends StatProcessingVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_processing WHERE stat_pro_id_auto = :stat_pro_id_auto";
	protected String SQL_INSERT					= "INSERT INTO stat_processing (stat_pro_type, stat_def_id, cli_id, stat_pro_date_start, stat_pro_date_end, stat_pro_result, stat_pro_file_log) VALUES (:stat_pro_type, :stat_def_id, :cli_id, :stat_pro_date_start, :stat_pro_date_end, :stat_pro_result, :stat_pro_file_log)";
	protected String SQL_UPDATE					= "UPDATE stat_processing SET stat_pro_type = :stat_pro_type, stat_def_id = :stat_def_id, cli_id = :cli_id, stat_pro_date_start = :stat_pro_date_start, stat_pro_date_end = :stat_pro_date_end, stat_pro_result = :stat_pro_result, stat_pro_file_log = :stat_pro_file_log WHERE stat_pro_id_auto = :stat_pro_id_auto";
	protected String SQL_DELETE					= "DELETE FROM stat_processing WHERE stat_pro_id_auto = :stat_pro_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_pro_id_auto) DO UPDATE SET stat_pro_type = EXCLUDED.stat_pro_type, stat_def_id = EXCLUDED.stat_def_id, cli_id = EXCLUDED.cli_id, stat_pro_date_start = EXCLUDED.stat_pro_date_start, stat_pro_date_end = EXCLUDED.stat_pro_date_end, stat_pro_result = EXCLUDED.stat_pro_result, stat_pro_file_log = EXCLUDED.stat_pro_file_log";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"stat_pro_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("stat_pro_type", vo.getStatProType())
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("cli_id", vo.getCliId())
			.addValue("stat_pro_date_start", vo.getStatProDateStart())
			.addValue("stat_pro_date_end", vo.getStatProDateEnd())
			.addValue("stat_pro_result", vo.getStatProResult())
			.addValue("stat_pro_id_auto", vo.getStatProId())
			.addValue("stat_pro_file_log", vo.getStatProFileLog());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("stat_pro_type", vo.getStatProType())
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("cli_id", vo.getCliId())
			.addValue("stat_pro_date_start", vo.getStatProDateStart())
			.addValue("stat_pro_date_end", vo.getStatProDateEnd())
			.addValue("stat_pro_result", vo.getStatProResult())
			.addValue("stat_pro_file_log", vo.getStatProFileLog())
			.addValue("stat_pro_id_auto", vo.getStatProId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getStatProId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer statProId) {
		return new MapSqlParameterSource()
			.addValue("stat_pro_id_auto", statProId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StatProcessingRowWrapper.getInstance()); }
	public StatProcessingVo findVo(Integer statProId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statProId), StatProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setStatProId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getStatProId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

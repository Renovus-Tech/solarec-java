package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatProcessingRowWrapper;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;

public abstract class BaseStatProcessingDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_processing WHERE stat_pro_id_auto = :stat_pro_id_auto";
	protected String SQL_INSERT					= "INSERT INTO stat_processing (stat_def_id,cli_id,stat_pro_date_start,stat_pro_date_end,stat_pro_result,stat_pro_file_log,stat_pro_type) VALUES (:stat_def_id,:cli_id,:stat_pro_date_start,:stat_pro_date_end,:stat_pro_result,:stat_pro_file_log,:stat_pro_type)";
	protected String SQL_UPDATE					= "UPDATE stat_processing SET stat_def_id = :stat_def_id,cli_id = :cli_id,stat_pro_date_start = :stat_pro_date_start,stat_pro_date_end = :stat_pro_date_end,stat_pro_result = :stat_pro_result,stat_pro_file_log = :stat_pro_file_log,stat_pro_type = :stat_pro_type WHERE stat_pro_id_auto = :stat_pro_id_auto";
	protected String SQL_DELETE					= "DELETE FROM stat_processing WHERE stat_pro_id_auto = :stat_pro_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_pro_id_auto) DO UPDATE SET stat_def_id = EXCLUDED.stat_def_id, cli_id = EXCLUDED.cli_id, stat_pro_date_start = EXCLUDED.stat_pro_date_start, stat_pro_date_end = EXCLUDED.stat_pro_date_end, stat_pro_result = EXCLUDED.stat_pro_result, stat_pro_file_log = EXCLUDED.stat_pro_file_log, stat_pro_type = EXCLUDED.stat_pro_type";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"stat_pro_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StatProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("cli_id", vo.getCliId())
			.addValue("stat_pro_date_start", vo.getStatProDateStart())
			.addValue("stat_pro_date_end", vo.getStatProDateEnd())
			.addValue("stat_pro_result", vo.getStatProResult())
			.addValue("stat_pro_file_log", vo.getStatProFileLog())
			.addValue("stat_pro_type", vo.getStatProType());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StatProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("cli_id", vo.getCliId())
			.addValue("stat_pro_date_start", vo.getStatProDateStart())
			.addValue("stat_pro_date_end", vo.getStatProDateEnd())
			.addValue("stat_pro_result", vo.getStatProResult())
			.addValue("stat_pro_file_log", vo.getStatProFileLog())
			.addValue("stat_pro_type", vo.getStatProType())
			.addValue("stat_pro_id_auto", vo.getStatProId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StatProcessingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getStatProId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer statProId) {
		return new MapSqlParameterSource()
			.addValue("stat_pro_id_auto", statProId);
	}

	//--- Public methods ------------------------
	public Collection<StatProcessingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StatProcessingRowWrapper.getInstance()); }
	public StatProcessingVo findVo(Integer statProId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statProId), StatProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StatProcessingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setStatProId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(StatProcessingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StatProcessingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StatProcessingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StatProcessingVo.SYNC_INSERT: this.insert(vo); break;
			case StatProcessingVo.SYNC_UPDATE: this.update(vo); break;
			case StatProcessingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StatProcessingVo> vos) {
		if (vos == null) return;
		for (StatProcessingVo vo : vos) this.synchronize(vo);
}


}


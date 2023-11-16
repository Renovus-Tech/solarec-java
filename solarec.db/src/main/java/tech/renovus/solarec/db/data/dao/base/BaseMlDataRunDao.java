package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.MlDataRunRowWrapper;
import tech.renovus.solarec.vo.db.data.MlDataRunVo;

public abstract class BaseMlDataRunDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM ml_data_run";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM ml_data_run WHERE run_id_auto = :run_id_auto";
	protected String SQL_INSERT					= "INSERT INTO ml_data_run (cli_id,data_date,total_time_ms,model_version,total_evaluated_rows) VALUES (:cli_id,:data_date,:total_time_ms,:model_version,:total_evaluated_rows)";
	protected String SQL_UPDATE					= "UPDATE ml_data_run SET cli_id = :cli_id,data_date = :data_date,total_time_ms = :total_time_ms,model_version = :model_version,total_evaluated_rows = :total_evaluated_rows WHERE run_id_auto = :run_id_auto";
	protected String SQL_DELETE					= "DELETE FROM ml_data_run WHERE run_id_auto = :run_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (run_id_auto) DO UPDATE SET cli_id = EXCLUDED.cli_id, data_date = EXCLUDED.data_date, total_time_ms = EXCLUDED.total_time_ms, model_version = EXCLUDED.model_version, total_evaluated_rows = EXCLUDED.total_evaluated_rows";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"run_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseMlDataRunDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(MlDataRunVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("data_date", vo.getDataDate())
			.addValue("total_time_ms", vo.getTotalTimeMs())
			.addValue("model_version", vo.getModelVersion())
			.addValue("total_evaluated_rows", vo.getTotalEvaluatedRows());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(MlDataRunVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("data_date", vo.getDataDate())
			.addValue("total_time_ms", vo.getTotalTimeMs())
			.addValue("model_version", vo.getModelVersion())
			.addValue("total_evaluated_rows", vo.getTotalEvaluatedRows())
			.addValue("run_id_auto", vo.getRunId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(MlDataRunVo vo) {
		return this.createPkMapSqlParameterSource(vo.getRunId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer runId) {
		return new MapSqlParameterSource()
			.addValue("run_id_auto", runId);
	}

	//--- Public methods ------------------------
	public Collection<MlDataRunVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, MlDataRunRowWrapper.getInstance()); }
	public MlDataRunVo findVo(Integer runId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(runId), MlDataRunRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(MlDataRunVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setRunId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(MlDataRunVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(MlDataRunVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(MlDataRunVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case MlDataRunVo.SYNC_INSERT: this.insert(vo); break;
			case MlDataRunVo.SYNC_UPDATE: this.update(vo); break;
			case MlDataRunVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<MlDataRunVo> vos) {
		if (vos == null) return;
		for (MlDataRunVo vo : vos) this.synchronize(vo);
}


}


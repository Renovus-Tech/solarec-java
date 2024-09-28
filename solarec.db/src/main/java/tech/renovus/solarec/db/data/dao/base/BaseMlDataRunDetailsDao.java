package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.MlDataRunDetailsRowWrapper;
import tech.renovus.solarec.vo.db.data.MlDataRunDetailsVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseMlDataRunDetailsDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM ml_data_run_details";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM ml_data_run_details WHERE run_det_id_auto = :run_det_id_auto";
	protected String SQL_INSERT					= "INSERT INTO ml_data_run_details (cli_id,run_id,data_gen_id,data_date,prediction) VALUES (:cli_id,:run_id,:data_gen_id,:data_date,:prediction)";
	protected String SQL_UPDATE					= "UPDATE ml_data_run_details SET cli_id = :cli_id,run_id = :run_id,data_gen_id = :data_gen_id,data_date = :data_date,prediction = :prediction WHERE run_det_id_auto = :run_det_id_auto";
	protected String SQL_DELETE					= "DELETE FROM ml_data_run_details WHERE run_det_id_auto = :run_det_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (run_det_id_auto) DO UPDATE SET cli_id = EXCLUDED.cli_id, run_id = EXCLUDED.run_id, data_gen_id = EXCLUDED.data_gen_id, data_date = EXCLUDED.data_date, prediction = EXCLUDED.prediction";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"run_det_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseMlDataRunDetailsDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(MlDataRunDetailsVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("run_id", vo.getRunId())
			.addValue("data_gen_id", vo.getDataGenId())
			.addValue("data_date", vo.getDataDate())
			.addValue("prediction", vo.getPrediction());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(MlDataRunDetailsVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("run_id", vo.getRunId())
			.addValue("data_gen_id", vo.getDataGenId())
			.addValue("data_date", vo.getDataDate())
			.addValue("prediction", vo.getPrediction())
			.addValue("run_det_id_auto", vo.getRunDetId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(MlDataRunDetailsVo vo) {
		return this.createPkMapSqlParameterSource(vo.getRunDetId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer runDetId) {
		return new MapSqlParameterSource()
			.addValue("run_det_id_auto", runDetId);
	}

	//--- Public methods ------------------------
	public Collection<MlDataRunDetailsVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, MlDataRunDetailsRowWrapper.getInstance()); }
	public MlDataRunDetailsVo findVo(Integer runDetId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(runDetId), MlDataRunDetailsRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(MlDataRunDetailsVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setRunDetId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(MlDataRunDetailsVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(MlDataRunDetailsVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(MlDataRunDetailsVo vo) {
		if (vo == null) {
			return;
		}
		switch (vo.getSyncType()) {
			case MlDataRunDetailsVo.SYNC_INSERT: this.insert(vo); break;
			case MlDataRunDetailsVo.SYNC_UPDATE: this.update(vo); break;
			case MlDataRunDetailsVo.SYNC_DELETE: this.delete(vo); break;
			default:
		}
	}
	public void synchronize(Collection<MlDataRunDetailsVo> vos) {
		if (vos == null) {
			return;
		}
		for (MlDataRunDetailsVo vo : vos) {
			this.synchronize(vo);
		}
}


}


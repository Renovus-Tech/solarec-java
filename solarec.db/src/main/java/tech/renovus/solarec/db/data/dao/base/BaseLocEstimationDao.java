package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocEstimationRowWrapper;
import tech.renovus.solarec.db.data.vo.LocEstimationVo;

public abstract class BaseLocEstimationDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_estimation";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_estimation WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_est_id_auto = :loc_est_id_auto";
	protected String SQL_INSERT					= "INSERT INTO loc_estimation (cli_id,loc_id,loc_est_order,loc_est_title,loc_est_01,loc_est_02,loc_est_03,loc_est_04,loc_est_05,loc_est_06,loc_est_07,loc_est_08,loc_est_09,loc_est_10,loc_est_11,loc_est_12) VALUES (:cli_id,:loc_id,:loc_est_order,:loc_est_title,:loc_est_01,:loc_est_02,:loc_est_03,:loc_est_04,:loc_est_05,:loc_est_06,:loc_est_07,:loc_est_08,:loc_est_09,:loc_est_10,:loc_est_11,:loc_est_12)";
	protected String SQL_UPDATE					= "UPDATE loc_estimation SET loc_est_order = :loc_est_order,loc_est_title = :loc_est_title,loc_est_01 = :loc_est_01,loc_est_02 = :loc_est_02,loc_est_03 = :loc_est_03,loc_est_04 = :loc_est_04,loc_est_05 = :loc_est_05,loc_est_06 = :loc_est_06,loc_est_07 = :loc_est_07,loc_est_08 = :loc_est_08,loc_est_09 = :loc_est_09,loc_est_10 = :loc_est_10,loc_est_11 = :loc_est_11,loc_est_12 = :loc_est_12 WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_est_id_auto = :loc_est_id_auto";
	protected String SQL_DELETE					= "DELETE FROM loc_estimation WHERE cli_id = :cli_id AND loc_id = :loc_id AND loc_est_id_auto = :loc_est_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, loc_est_id_auto) DO UPDATE SET loc_est_order = EXCLUDED.loc_est_order, loc_est_title = EXCLUDED.loc_est_title, loc_est_01 = EXCLUDED.loc_est_01, loc_est_02 = EXCLUDED.loc_est_02, loc_est_03 = EXCLUDED.loc_est_03, loc_est_04 = EXCLUDED.loc_est_04, loc_est_05 = EXCLUDED.loc_est_05, loc_est_06 = EXCLUDED.loc_est_06, loc_est_07 = EXCLUDED.loc_est_07, loc_est_08 = EXCLUDED.loc_est_08, loc_est_09 = EXCLUDED.loc_est_09, loc_est_10 = EXCLUDED.loc_est_10, loc_est_11 = EXCLUDED.loc_est_11, loc_est_12 = EXCLUDED.loc_est_12";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"loc_est_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocEstimationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocEstimationVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_est_order", vo.getLocEstOrder())
			.addValue("loc_est_title", vo.getLocEstTitle())
			.addValue("loc_est_01", vo.getLocEst01())
			.addValue("loc_est_02", vo.getLocEst02())
			.addValue("loc_est_03", vo.getLocEst03())
			.addValue("loc_est_04", vo.getLocEst04())
			.addValue("loc_est_05", vo.getLocEst05())
			.addValue("loc_est_06", vo.getLocEst06())
			.addValue("loc_est_07", vo.getLocEst07())
			.addValue("loc_est_08", vo.getLocEst08())
			.addValue("loc_est_09", vo.getLocEst09())
			.addValue("loc_est_10", vo.getLocEst10())
			.addValue("loc_est_11", vo.getLocEst11())
			.addValue("loc_est_12", vo.getLocEst12());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocEstimationVo vo) {
		return new MapSqlParameterSource()
			.addValue("loc_est_order", vo.getLocEstOrder())
			.addValue("loc_est_title", vo.getLocEstTitle())
			.addValue("loc_est_01", vo.getLocEst01())
			.addValue("loc_est_02", vo.getLocEst02())
			.addValue("loc_est_03", vo.getLocEst03())
			.addValue("loc_est_04", vo.getLocEst04())
			.addValue("loc_est_05", vo.getLocEst05())
			.addValue("loc_est_06", vo.getLocEst06())
			.addValue("loc_est_07", vo.getLocEst07())
			.addValue("loc_est_08", vo.getLocEst08())
			.addValue("loc_est_09", vo.getLocEst09())
			.addValue("loc_est_10", vo.getLocEst10())
			.addValue("loc_est_11", vo.getLocEst11())
			.addValue("loc_est_12", vo.getLocEst12())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_est_id_auto", vo.getLocEstId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocEstimationVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getLocEstId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer locEstId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("loc_est_id_auto", locEstId);
	}

	//--- Public methods ------------------------
	public Collection<LocEstimationVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocEstimationRowWrapper.getInstance()); }
	public LocEstimationVo findVo(Integer cliId, Integer locId, Integer locEstId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, locEstId), LocEstimationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocEstimationVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setLocEstId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(LocEstimationVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocEstimationVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocEstimationVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocEstimationVo.SYNC_INSERT: this.insert(vo); break;
			case LocEstimationVo.SYNC_UPDATE: this.update(vo); break;
			case LocEstimationVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocEstimationVo> vos) {
		if (vos == null) return;
		for (LocEstimationVo vo : vos) this.synchronize(vo);
}


}


package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocEstimationRowWrapper;
import tech.renovus.solarec.vo.db.data.LocEstimationVo;

public abstract class BaseLocEstimationDao <T extends LocEstimationVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_estimation";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_estimation WHERE loc_id = :loc_id AND loc_est_id_auto = :loc_est_id_auto AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO loc_estimation (loc_est_12, loc_id, loc_est_order, cli_id, loc_est_01, loc_est_02, loc_est_03, loc_est_04, loc_est_05, loc_est_06, loc_est_07, loc_est_08, loc_est_09, loc_est_10, loc_est_11, loc_est_title) VALUES (:loc_est_12, :loc_id, :loc_est_order, :cli_id, :loc_est_01, :loc_est_02, :loc_est_03, :loc_est_04, :loc_est_05, :loc_est_06, :loc_est_07, :loc_est_08, :loc_est_09, :loc_est_10, :loc_est_11, :loc_est_title)";
	protected String SQL_UPDATE					= "UPDATE loc_estimation SET loc_est_12 = :loc_est_12, loc_est_order = :loc_est_order, loc_est_01 = :loc_est_01, loc_est_02 = :loc_est_02, loc_est_03 = :loc_est_03, loc_est_04 = :loc_est_04, loc_est_05 = :loc_est_05, loc_est_06 = :loc_est_06, loc_est_07 = :loc_est_07, loc_est_08 = :loc_est_08, loc_est_09 = :loc_est_09, loc_est_10 = :loc_est_10, loc_est_11 = :loc_est_11, loc_est_title = :loc_est_title WHERE loc_id = :loc_id AND loc_est_id_auto = :loc_est_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM loc_estimation WHERE loc_id = :loc_id AND loc_est_id_auto = :loc_est_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (loc_id, loc_est_id_auto, cli_id) DO UPDATE SET loc_est_12 = EXCLUDED.loc_est_12, loc_est_order = EXCLUDED.loc_est_order, loc_est_01 = EXCLUDED.loc_est_01, loc_est_02 = EXCLUDED.loc_est_02, loc_est_03 = EXCLUDED.loc_est_03, loc_est_04 = EXCLUDED.loc_est_04, loc_est_05 = EXCLUDED.loc_est_05, loc_est_06 = EXCLUDED.loc_est_06, loc_est_07 = EXCLUDED.loc_est_07, loc_est_08 = EXCLUDED.loc_est_08, loc_est_09 = EXCLUDED.loc_est_09, loc_est_10 = EXCLUDED.loc_est_10, loc_est_11 = EXCLUDED.loc_est_11, loc_est_title = EXCLUDED.loc_est_title";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"loc_est_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocEstimationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("loc_est_12", vo.getLocEst12())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_est_id_auto", vo.getLocEstId())
			.addValue("loc_est_order", vo.getLocEstOrder())
			.addValue("cli_id", vo.getCliId())
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
			.addValue("loc_est_title", vo.getLocEstTitle());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("loc_est_12", vo.getLocEst12())
			.addValue("loc_est_order", vo.getLocEstOrder())
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
			.addValue("loc_est_title", vo.getLocEstTitle())
			.addValue("loc_id", vo.getLocId())
			.addValue("loc_est_id_auto", vo.getLocEstId())
			.addValue("cli_id", vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getLocId(), vo.getLocEstId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer locId, Integer locEstId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("loc_id", locId)
			.addValue("loc_est_id_auto", locEstId)
			.addValue("cli_id", cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocEstimationRowWrapper.getInstance()); }
	public LocEstimationVo findVo(Integer locId, Integer locEstId, Integer cliId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(locId, locEstId, cliId), LocEstimationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setLocEstId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getLocId(), vo.getLocEstId(), vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

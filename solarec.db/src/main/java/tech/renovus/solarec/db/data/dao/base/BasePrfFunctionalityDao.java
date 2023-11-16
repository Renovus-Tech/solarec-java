package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.PrfFunctionalityRowWrapper;
import tech.renovus.solarec.vo.db.data.PrfFunctionalityVo;

public abstract class BasePrfFunctionalityDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM prf_functionality";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM prf_functionality WHERE prf_id = :prf_id AND fnc_id = :fnc_id";
	protected String SQL_INSERT					= "INSERT INTO prf_functionality (prf_id,fnc_id) VALUES (:prf_id,:fnc_id)";
	protected String SQL_UPDATE					= "UPDATE prf_functionality SET  WHERE prf_id = :prf_id AND fnc_id = :fnc_id";
	protected String SQL_DELETE					= "DELETE FROM prf_functionality WHERE prf_id = :prf_id AND fnc_id = :fnc_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (prf_id, fnc_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BasePrfFunctionalityDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(PrfFunctionalityVo vo) {
		return new MapSqlParameterSource()
			.addValue("prf_id", vo.getPrfId())
			.addValue("fnc_id", vo.getFncId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(PrfFunctionalityVo vo) {
		return new MapSqlParameterSource()
			.addValue("prf_id", vo.getPrfId())
			.addValue("fnc_id", vo.getFncId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(PrfFunctionalityVo vo) {
		return this.createPkMapSqlParameterSource(vo.getPrfId(), vo.getFncId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer prfId, Integer fncId) {
		return new MapSqlParameterSource()
			.addValue("prf_id", prfId)
			.addValue("fnc_id", fncId);
	}

	//--- Public methods ------------------------
	public Collection<PrfFunctionalityVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, PrfFunctionalityRowWrapper.getInstance()); }
	public PrfFunctionalityVo findVo(Integer prfId, Integer fncId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(prfId, fncId), PrfFunctionalityRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(PrfFunctionalityVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(PrfFunctionalityVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(PrfFunctionalityVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(PrfFunctionalityVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case PrfFunctionalityVo.SYNC_INSERT: this.insert(vo); break;
			case PrfFunctionalityVo.SYNC_UPDATE: this.update(vo); break;
			case PrfFunctionalityVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<PrfFunctionalityVo> vos) {
		if (vos == null) return;
		for (PrfFunctionalityVo vo : vos) this.synchronize(vo);
}


}


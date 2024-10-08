package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.PrfFunctionalityRowWrapper;
import tech.renovus.solarec.vo.db.data.PrfFunctionalityVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BasePrfFunctionalityDao <T extends PrfFunctionalityVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM prf_functionality";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM prf_functionality WHERE prf_id = :prf_id AND fnc_id = :fnc_id";
	protected String SQL_INSERT					= "INSERT INTO prf_functionality (prf_id, fnc_id) VALUES (:prf_id, :fnc_id)";
	protected String SQL_UPDATE					= "UPDATE prf_functionality SET  WHERE prf_id = :prf_id AND fnc_id = :fnc_id";
	protected String SQL_DELETE					= "DELETE FROM prf_functionality WHERE prf_id = :prf_id AND fnc_id = :fnc_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (prf_id, fnc_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BasePrfFunctionalityDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(PrfFunctionalityVo.COLUMN_PRF_ID, vo.getPrfId())
			.addValue(PrfFunctionalityVo.COLUMN_FNC_ID, vo.getFncId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(PrfFunctionalityVo.COLUMN_PRF_ID, vo.getPrfId())
			.addValue(PrfFunctionalityVo.COLUMN_FNC_ID, vo.getFncId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getPrfId(), vo.getFncId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer prfId, Integer fncId) {
		return new MapSqlParameterSource()
			.addValue(PrfFunctionalityVo.COLUMN_PRF_ID, prfId)
			.addValue(PrfFunctionalityVo.COLUMN_FNC_ID, fncId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, PrfFunctionalityRowWrapper.getInstance()); }
	public PrfFunctionalityVo findVo(Integer prfId, Integer fncId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(prfId, fncId), PrfFunctionalityRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getPrfId(), vo.getFncId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

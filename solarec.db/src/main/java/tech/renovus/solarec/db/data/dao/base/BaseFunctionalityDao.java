package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.FunctionalityRowWrapper;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;

public abstract class BaseFunctionalityDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM functionality";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM functionality WHERE fnc_id_auto = :fnc_id_auto";
	protected String SQL_INSERT					= "INSERT INTO functionality (fnc_name,fnc_title,fnc_description,fnc_flags,fnc_url,fnc_order) VALUES (:fnc_name,:fnc_title,:fnc_description,:fnc_flags,:fnc_url,:fnc_order)";
	protected String SQL_UPDATE					= "UPDATE functionality SET fnc_name = :fnc_name,fnc_title = :fnc_title,fnc_description = :fnc_description,fnc_flags = :fnc_flags,fnc_url = :fnc_url,fnc_order = :fnc_order WHERE fnc_id_auto = :fnc_id_auto";
	protected String SQL_DELETE					= "DELETE FROM functionality WHERE fnc_id_auto = :fnc_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (fnc_id_auto) DO UPDATE SET fnc_name = EXCLUDED.fnc_name, fnc_title = EXCLUDED.fnc_title, fnc_description = EXCLUDED.fnc_description, fnc_flags = EXCLUDED.fnc_flags, fnc_url = EXCLUDED.fnc_url, fnc_order = EXCLUDED.fnc_order";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"fnc_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseFunctionalityDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(FunctionalityVo vo) {
		return new MapSqlParameterSource()
			.addValue("fnc_name", vo.getFncName())
			.addValue("fnc_title", vo.getFncTitle())
			.addValue("fnc_description", vo.getFncDescription())
			.addValue("fnc_flags", vo.getFncFlags())
			.addValue("fnc_url", vo.getFncUrl())
			.addValue("fnc_order", vo.getFncOrder());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(FunctionalityVo vo) {
		return new MapSqlParameterSource()
			.addValue("fnc_name", vo.getFncName())
			.addValue("fnc_title", vo.getFncTitle())
			.addValue("fnc_description", vo.getFncDescription())
			.addValue("fnc_flags", vo.getFncFlags())
			.addValue("fnc_url", vo.getFncUrl())
			.addValue("fnc_order", vo.getFncOrder())
			.addValue("fnc_id_auto", vo.getFncId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(FunctionalityVo vo) {
		return this.createPkMapSqlParameterSource(vo.getFncId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer fncId) {
		return new MapSqlParameterSource()
			.addValue("fnc_id_auto", fncId);
	}

	//--- Public methods ------------------------
	public Collection<FunctionalityVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, FunctionalityRowWrapper.getInstance()); }
	public FunctionalityVo findVo(Integer fncId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(fncId), FunctionalityRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(FunctionalityVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setFncId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(FunctionalityVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(FunctionalityVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(FunctionalityVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case FunctionalityVo.SYNC_INSERT: this.insert(vo); break;
			case FunctionalityVo.SYNC_UPDATE: this.update(vo); break;
			case FunctionalityVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<FunctionalityVo> vos) {
		if (vos == null) return;
		for (FunctionalityVo vo : vos) this.synchronize(vo);
}


}


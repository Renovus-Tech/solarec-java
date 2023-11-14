package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.RepTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.RepTypeVo;

public abstract class BaseRepTypeDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM rep_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM rep_type WHERE rep_type_id_auto = :rep_type_id_auto";
	protected String SQL_INSERT					= "INSERT INTO rep_type (rep_type_name,rep_type_title,rep_flags) VALUES (:rep_type_name,:rep_type_title,:rep_flags)";
	protected String SQL_UPDATE					= "UPDATE rep_type SET rep_type_name = :rep_type_name,rep_type_title = :rep_type_title,rep_flags = :rep_flags WHERE rep_type_id_auto = :rep_type_id_auto";
	protected String SQL_DELETE					= "DELETE FROM rep_type WHERE rep_type_id_auto = :rep_type_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (rep_type_id_auto) DO UPDATE SET rep_type_name = EXCLUDED.rep_type_name, rep_type_title = EXCLUDED.rep_type_title, rep_flags = EXCLUDED.rep_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"rep_type_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseRepTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(RepTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("rep_type_name", vo.getRepTypeName())
			.addValue("rep_type_title", vo.getRepTypeTitle())
			.addValue("rep_flags", vo.getRepFlags());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(RepTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("rep_type_name", vo.getRepTypeName())
			.addValue("rep_type_title", vo.getRepTypeTitle())
			.addValue("rep_flags", vo.getRepFlags())
			.addValue("rep_type_id_auto", vo.getRepTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(RepTypeVo vo) {
		return this.createPkMapSqlParameterSource(vo.getRepTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer repTypeId) {
		return new MapSqlParameterSource()
			.addValue("rep_type_id_auto", repTypeId);
	}

	//--- Public methods ------------------------
	public Collection<RepTypeVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, RepTypeRowWrapper.getInstance()); }
	public RepTypeVo findVo(Integer repTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(repTypeId), RepTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(RepTypeVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setRepTypeId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(RepTypeVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(RepTypeVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(RepTypeVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case RepTypeVo.SYNC_INSERT: this.insert(vo); break;
			case RepTypeVo.SYNC_UPDATE: this.update(vo); break;
			case RepTypeVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<RepTypeVo> vos) {
		if (vos == null) return;
		for (RepTypeVo vo : vos) this.synchronize(vo);
}


}


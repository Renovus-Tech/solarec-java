package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.RepTypeRowWrapper;
import tech.renovus.solarec.vo.db.data.RepTypeVo;

public abstract class BaseRepTypeDao <T extends RepTypeVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM rep_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM rep_type WHERE rep_type_id_auto = :rep_type_id_auto";
	protected String SQL_INSERT					= "INSERT INTO rep_type (rep_order, rep_type_name, rep_type_title, rep_flags, rep_executable) VALUES (:rep_order, :rep_type_name, :rep_type_title, :rep_flags, :rep_executable)";
	protected String SQL_UPDATE					= "UPDATE rep_type SET rep_order = :rep_order, rep_type_name = :rep_type_name, rep_type_title = :rep_type_title, rep_flags = :rep_flags, rep_executable = :rep_executable WHERE rep_type_id_auto = :rep_type_id_auto";
	protected String SQL_DELETE					= "DELETE FROM rep_type WHERE rep_type_id_auto = :rep_type_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (rep_type_id_auto) DO UPDATE SET rep_order = EXCLUDED.rep_order, rep_type_name = EXCLUDED.rep_type_name, rep_type_title = EXCLUDED.rep_type_title, rep_flags = EXCLUDED.rep_flags, rep_executable = EXCLUDED.rep_executable";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"rep_type_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseRepTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("rep_type_id_auto", vo.getRepTypeId())
			.addValue("rep_order", vo.getRepOrder())
			.addValue("rep_type_name", vo.getRepTypeName())
			.addValue("rep_type_title", vo.getRepTypeTitle())
			.addValue("rep_flags", vo.getRepFlags())
			.addValue("rep_executable", vo.getRepExecutable());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("rep_order", vo.getRepOrder())
			.addValue("rep_type_name", vo.getRepTypeName())
			.addValue("rep_type_title", vo.getRepTypeTitle())
			.addValue("rep_flags", vo.getRepFlags())
			.addValue("rep_executable", vo.getRepExecutable())
			.addValue("rep_type_id_auto", vo.getRepTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getRepTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer repTypeId) {
		return new MapSqlParameterSource()
			.addValue("rep_type_id_auto", repTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, RepTypeRowWrapper.getInstance()); }
	public RepTypeVo findVo(Integer repTypeId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(repTypeId), RepTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setRepTypeId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
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

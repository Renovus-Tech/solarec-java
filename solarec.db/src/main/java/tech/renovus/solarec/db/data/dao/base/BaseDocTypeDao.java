package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocTypeRowWrapper;
import tech.renovus.solarec.vo.db.data.DocTypeVo;

public abstract class BaseDocTypeDao <T extends DocTypeVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM doc_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_type WHERE doc_type_id_auto = :doc_type_id_auto";
	protected String SQL_INSERT					= "INSERT INTO doc_type (doc_type_name, doc_type_title, doc_type_flags) VALUES (:doc_type_name, :doc_type_title, :doc_type_flags)";
	protected String SQL_UPDATE					= "UPDATE doc_type SET doc_type_name = :doc_type_name, doc_type_title = :doc_type_title, doc_type_flags = :doc_type_flags WHERE doc_type_id_auto = :doc_type_id_auto";
	protected String SQL_DELETE					= "DELETE FROM doc_type WHERE doc_type_id_auto = :doc_type_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (doc_type_id_auto) DO UPDATE SET doc_type_name = EXCLUDED.doc_type_name, doc_type_title = EXCLUDED.doc_type_title, doc_type_flags = EXCLUDED.doc_type_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"doc_type_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDocTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("doc_type_id_auto", vo.getDocTypeId())
			.addValue("doc_type_name", vo.getDocTypeName())
			.addValue("doc_type_title", vo.getDocTypeTitle())
			.addValue("doc_type_flags", vo.getDocTypeFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("doc_type_name", vo.getDocTypeName())
			.addValue("doc_type_title", vo.getDocTypeTitle())
			.addValue("doc_type_flags", vo.getDocTypeFlags())
			.addValue("doc_type_id_auto", vo.getDocTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDocTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer docTypeId) {
		return new MapSqlParameterSource()
			.addValue("doc_type_id_auto", docTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DocTypeRowWrapper.getInstance()); }
	public DocTypeVo findVo(Integer docTypeId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(docTypeId), DocTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setDocTypeId(Integer.valueOf(holder.getKey().intValue()));
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

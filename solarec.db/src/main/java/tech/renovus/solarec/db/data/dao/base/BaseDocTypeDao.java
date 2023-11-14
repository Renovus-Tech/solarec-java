package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.DocTypeVo;

public abstract class BaseDocTypeDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM doc_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_type WHERE doc_type_id_auto = :doc_type_id_auto";
	protected String SQL_INSERT					= "INSERT INTO doc_type (doc_type_name,doc_type_title,doc_type_flags) VALUES (:doc_type_name,:doc_type_title,:doc_type_flags)";
	protected String SQL_UPDATE					= "UPDATE doc_type SET doc_type_name = :doc_type_name,doc_type_title = :doc_type_title,doc_type_flags = :doc_type_flags WHERE doc_type_id_auto = :doc_type_id_auto";
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
	private MapSqlParameterSource createInsertMapSqlParameterSource(DocTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("doc_type_name", vo.getDocTypeName())
			.addValue("doc_type_title", vo.getDocTypeTitle())
			.addValue("doc_type_flags", vo.getDocTypeFlags());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DocTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("doc_type_name", vo.getDocTypeName())
			.addValue("doc_type_title", vo.getDocTypeTitle())
			.addValue("doc_type_flags", vo.getDocTypeFlags())
			.addValue("doc_type_id_auto", vo.getDocTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DocTypeVo vo) {
		return this.createPkMapSqlParameterSource(vo.getDocTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer docTypeId) {
		return new MapSqlParameterSource()
			.addValue("doc_type_id_auto", docTypeId);
	}

	//--- Public methods ------------------------
	public Collection<DocTypeVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DocTypeRowWrapper.getInstance()); }
	public DocTypeVo findVo(Integer docTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(docTypeId), DocTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DocTypeVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setDocTypeId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(DocTypeVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DocTypeVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DocTypeVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DocTypeVo.SYNC_INSERT: this.insert(vo); break;
			case DocTypeVo.SYNC_UPDATE: this.update(vo); break;
			case DocTypeVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DocTypeVo> vos) {
		if (vos == null) return;
		for (DocTypeVo vo : vos) this.synchronize(vo);
}


}


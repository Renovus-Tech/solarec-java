package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocumentRowWrapper;
import tech.renovus.solarec.vo.db.data.DocumentVo;

public abstract class BaseDocumentDao <T extends DocumentVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM document";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM document WHERE cli_id = :cli_id AND doc_id_auto = :doc_id_auto";
	protected String SQL_INSERT					= "INSERT INTO document (cli_id, doc_type_id, doc_date_added, doc_date_from, doc_date_to, doc_file_size, doc_flags, doc_name, doc_observations, doc_file, doc_file_name, doc_file_content) VALUES (:cli_id, :doc_type_id, :doc_date_added, :doc_date_from, :doc_date_to, :doc_file_size, :doc_flags, :doc_name, :doc_observations, :doc_file, :doc_file_name, :doc_file_content)";
	protected String SQL_UPDATE					= "UPDATE document SET doc_type_id = :doc_type_id, doc_date_added = :doc_date_added, doc_date_from = :doc_date_from, doc_date_to = :doc_date_to, doc_file_size = :doc_file_size, doc_flags = :doc_flags, doc_name = :doc_name, doc_observations = :doc_observations, doc_file = :doc_file, doc_file_name = :doc_file_name, doc_file_content = :doc_file_content WHERE cli_id = :cli_id AND doc_id_auto = :doc_id_auto";
	protected String SQL_DELETE					= "DELETE FROM document WHERE cli_id = :cli_id AND doc_id_auto = :doc_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id_auto) DO UPDATE SET doc_type_id = EXCLUDED.doc_type_id, doc_date_added = EXCLUDED.doc_date_added, doc_date_from = EXCLUDED.doc_date_from, doc_date_to = EXCLUDED.doc_date_to, doc_file_size = EXCLUDED.doc_file_size, doc_flags = EXCLUDED.doc_flags, doc_name = EXCLUDED.doc_name, doc_observations = EXCLUDED.doc_observations, doc_file = EXCLUDED.doc_file, doc_file_name = EXCLUDED.doc_file_name, doc_file_content = EXCLUDED.doc_file_content";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"doc_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDocumentDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id_auto", vo.getDocId())
			.addValue("doc_type_id", vo.getDocTypeId())
			.addValue("doc_date_added", vo.getDocDateAdded())
			.addValue("doc_date_from", vo.getDocDateFrom())
			.addValue("doc_date_to", vo.getDocDateTo())
			.addValue("doc_file_size", vo.getDocFileSize())
			.addValue("doc_flags", vo.getDocFlags())
			.addValue("doc_name", vo.getDocName())
			.addValue("doc_observations", vo.getDocObservations())
			.addValue("doc_file", vo.getDocFile())
			.addValue("doc_file_name", vo.getDocFileName())
			.addValue("doc_file_content", vo.getDocFileContent());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("doc_type_id", vo.getDocTypeId())
			.addValue("doc_date_added", vo.getDocDateAdded())
			.addValue("doc_date_from", vo.getDocDateFrom())
			.addValue("doc_date_to", vo.getDocDateTo())
			.addValue("doc_file_size", vo.getDocFileSize())
			.addValue("doc_flags", vo.getDocFlags())
			.addValue("doc_name", vo.getDocName())
			.addValue("doc_observations", vo.getDocObservations())
			.addValue("doc_file", vo.getDocFile())
			.addValue("doc_file_name", vo.getDocFileName())
			.addValue("doc_file_content", vo.getDocFileContent())
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id_auto", vo.getDocId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("doc_id_auto", docId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DocumentRowWrapper.getInstance()); }
	public DocumentVo findVo(Integer cliId, Integer docId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId), DocumentRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setDocId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getDocId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

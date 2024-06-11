package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocGeneratorRowWrapper;
import tech.renovus.solarec.vo.db.data.DocGeneratorVo;

public abstract class BaseDocGeneratorDao <T extends DocGeneratorVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM doc_generator";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_generator WHERE cli_id = :cli_id AND doc_id = :doc_id AND gen_id = :gen_id";
	protected String SQL_INSERT					= "INSERT INTO doc_generator (cli_id, doc_id, gen_id) VALUES (:cli_id, :doc_id, :gen_id)";
	protected String SQL_UPDATE					= "UPDATE doc_generator SET  WHERE cli_id = :cli_id AND doc_id = :doc_id AND gen_id = :gen_id";
	protected String SQL_DELETE					= "DELETE FROM doc_generator WHERE cli_id = :cli_id AND doc_id = :doc_id AND gen_id = :gen_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id, gen_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseDocGeneratorDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DocGeneratorVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DocGeneratorVo.COLUMN_DOC_ID, vo.getDocId())
			.addValue(DocGeneratorVo.COLUMN_GEN_ID, vo.getGenId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DocGeneratorVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DocGeneratorVo.COLUMN_DOC_ID, vo.getDocId())
			.addValue(DocGeneratorVo.COLUMN_GEN_ID, vo.getGenId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId(), vo.getGenId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId, Integer genId) {
		return new MapSqlParameterSource()
			.addValue(DocGeneratorVo.COLUMN_CLI_ID, cliId)
			.addValue(DocGeneratorVo.COLUMN_DOC_ID, docId)
			.addValue(DocGeneratorVo.COLUMN_GEN_ID, genId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DocGeneratorRowWrapper.getInstance()); }
	public DocGeneratorVo findVo(Integer cliId, Integer docId, Integer genId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId, genId), DocGeneratorRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getDocId(), vo.getGenId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

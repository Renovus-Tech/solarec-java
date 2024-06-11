package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocLocationRowWrapper;
import tech.renovus.solarec.vo.db.data.DocLocationVo;

public abstract class BaseDocLocationDao <T extends DocLocationVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM doc_location";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_location WHERE cli_id = :cli_id AND doc_id = :doc_id AND loc_id = :loc_id";
	protected String SQL_INSERT					= "INSERT INTO doc_location (cli_id, doc_id, loc_id) VALUES (:cli_id, :doc_id, :loc_id)";
	protected String SQL_UPDATE					= "UPDATE doc_location SET  WHERE cli_id = :cli_id AND doc_id = :doc_id AND loc_id = :loc_id";
	protected String SQL_DELETE					= "DELETE FROM doc_location WHERE cli_id = :cli_id AND doc_id = :doc_id AND loc_id = :loc_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id, loc_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseDocLocationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DocLocationVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DocLocationVo.COLUMN_DOC_ID, vo.getDocId())
			.addValue(DocLocationVo.COLUMN_LOC_ID, vo.getLocId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DocLocationVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DocLocationVo.COLUMN_DOC_ID, vo.getDocId())
			.addValue(DocLocationVo.COLUMN_LOC_ID, vo.getLocId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId(), vo.getLocId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId, Integer locId) {
		return new MapSqlParameterSource()
			.addValue(DocLocationVo.COLUMN_CLI_ID, cliId)
			.addValue(DocLocationVo.COLUMN_DOC_ID, docId)
			.addValue(DocLocationVo.COLUMN_LOC_ID, locId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DocLocationRowWrapper.getInstance()); }
	public DocLocationVo findVo(Integer cliId, Integer docId, Integer locId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId, locId), DocLocationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getDocId(), vo.getLocId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

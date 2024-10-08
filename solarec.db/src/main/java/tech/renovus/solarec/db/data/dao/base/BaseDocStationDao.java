package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocStationRowWrapper;
import tech.renovus.solarec.vo.db.data.DocStationVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseDocStationDao <T extends DocStationVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM doc_station";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_station WHERE cli_id = :cli_id AND doc_id = :doc_id AND sta_id = :sta_id";
	protected String SQL_INSERT					= "INSERT INTO doc_station (cli_id, doc_id, sta_id) VALUES (:cli_id, :doc_id, :sta_id)";
	protected String SQL_UPDATE					= "UPDATE doc_station SET  WHERE cli_id = :cli_id AND doc_id = :doc_id AND sta_id = :sta_id";
	protected String SQL_DELETE					= "DELETE FROM doc_station WHERE cli_id = :cli_id AND doc_id = :doc_id AND sta_id = :sta_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id, sta_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseDocStationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DocStationVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DocStationVo.COLUMN_DOC_ID, vo.getDocId())
			.addValue(DocStationVo.COLUMN_STA_ID, vo.getStaId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DocStationVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DocStationVo.COLUMN_DOC_ID, vo.getDocId())
			.addValue(DocStationVo.COLUMN_STA_ID, vo.getStaId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId(), vo.getStaId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId, Integer staId) {
		return new MapSqlParameterSource()
			.addValue(DocStationVo.COLUMN_CLI_ID, cliId)
			.addValue(DocStationVo.COLUMN_DOC_ID, docId)
			.addValue(DocStationVo.COLUMN_STA_ID, staId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DocStationRowWrapper.getInstance()); }
	public DocStationVo findVo(Integer cliId, Integer docId, Integer staId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId, staId), DocStationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getDocId(), vo.getStaId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

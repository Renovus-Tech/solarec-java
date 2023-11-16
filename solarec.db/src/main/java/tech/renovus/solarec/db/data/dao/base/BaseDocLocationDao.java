package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocLocationRowWrapper;
import tech.renovus.solarec.vo.db.data.DocLocationVo;

public abstract class BaseDocLocationDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM doc_location";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_location WHERE cli_id = :cli_id AND doc_id = :doc_id AND loc_id = :loc_id";
	protected String SQL_INSERT					= "INSERT INTO doc_location (cli_id,doc_id,loc_id) VALUES (:cli_id,:doc_id,:loc_id)";
	protected String SQL_UPDATE					= "UPDATE doc_location SET  WHERE cli_id = :cli_id AND doc_id = :doc_id AND loc_id = :loc_id";
	protected String SQL_DELETE					= "DELETE FROM doc_location WHERE cli_id = :cli_id AND doc_id = :doc_id AND loc_id = :loc_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id, loc_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDocLocationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DocLocationVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id", vo.getDocId())
			.addValue("loc_id", vo.getLocId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DocLocationVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id", vo.getDocId())
			.addValue("loc_id", vo.getLocId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DocLocationVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId(), vo.getLocId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId, Integer locId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("doc_id", docId)
			.addValue("loc_id", locId);
	}

	//--- Public methods ------------------------
	public Collection<DocLocationVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DocLocationRowWrapper.getInstance()); }
	public DocLocationVo findVo(Integer cliId, Integer docId, Integer locId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId, locId), DocLocationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DocLocationVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DocLocationVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DocLocationVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DocLocationVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DocLocationVo.SYNC_INSERT: this.insert(vo); break;
			case DocLocationVo.SYNC_UPDATE: this.update(vo); break;
			case DocLocationVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DocLocationVo> vos) {
		if (vos == null) return;
		for (DocLocationVo vo : vos) this.synchronize(vo);
}


}


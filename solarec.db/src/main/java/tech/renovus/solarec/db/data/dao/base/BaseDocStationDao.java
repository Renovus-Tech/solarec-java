package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocStationRowWrapper;
import tech.renovus.solarec.vo.db.data.DocStationVo;

public abstract class BaseDocStationDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM doc_station";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_station WHERE cli_id = :cli_id AND doc_id = :doc_id AND sta_id = :sta_id";
	protected String SQL_INSERT					= "INSERT INTO doc_station (cli_id,doc_id,sta_id) VALUES (:cli_id,:doc_id,:sta_id)";
	protected String SQL_UPDATE					= "UPDATE doc_station SET  WHERE cli_id = :cli_id AND doc_id = :doc_id AND sta_id = :sta_id";
	protected String SQL_DELETE					= "DELETE FROM doc_station WHERE cli_id = :cli_id AND doc_id = :doc_id AND sta_id = :sta_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id, sta_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDocStationDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DocStationVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id", vo.getDocId())
			.addValue("sta_id", vo.getStaId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DocStationVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id", vo.getDocId())
			.addValue("sta_id", vo.getStaId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DocStationVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId(), vo.getStaId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId, Integer staId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("doc_id", docId)
			.addValue("sta_id", staId);
	}

	//--- Public methods ------------------------
	public Collection<DocStationVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DocStationRowWrapper.getInstance()); }
	public DocStationVo findVo(Integer cliId, Integer docId, Integer staId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId, staId), DocStationRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DocStationVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DocStationVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DocStationVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DocStationVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DocStationVo.SYNC_INSERT: this.insert(vo); break;
			case DocStationVo.SYNC_UPDATE: this.update(vo); break;
			case DocStationVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DocStationVo> vos) {
		if (vos == null) return;
		for (DocStationVo vo : vos) this.synchronize(vo);
}


}


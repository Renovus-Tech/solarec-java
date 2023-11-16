package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DocGeneratorRowWrapper;
import tech.renovus.solarec.vo.db.data.DocGeneratorVo;

public abstract class BaseDocGeneratorDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM doc_generator";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM doc_generator WHERE cli_id = :cli_id AND doc_id = :doc_id AND gen_id = :gen_id";
	protected String SQL_INSERT					= "INSERT INTO doc_generator (cli_id,doc_id,gen_id) VALUES (:cli_id,:doc_id,:gen_id)";
	protected String SQL_UPDATE					= "UPDATE doc_generator SET  WHERE cli_id = :cli_id AND doc_id = :doc_id AND gen_id = :gen_id";
	protected String SQL_DELETE					= "DELETE FROM doc_generator WHERE cli_id = :cli_id AND doc_id = :doc_id AND gen_id = :gen_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, doc_id, gen_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDocGeneratorDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DocGeneratorVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id", vo.getDocId())
			.addValue("gen_id", vo.getGenId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DocGeneratorVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("doc_id", vo.getDocId())
			.addValue("gen_id", vo.getGenId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DocGeneratorVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDocId(), vo.getGenId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer docId, Integer genId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("doc_id", docId)
			.addValue("gen_id", genId);
	}

	//--- Public methods ------------------------
	public Collection<DocGeneratorVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DocGeneratorRowWrapper.getInstance()); }
	public DocGeneratorVo findVo(Integer cliId, Integer docId, Integer genId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, docId, genId), DocGeneratorRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DocGeneratorVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DocGeneratorVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DocGeneratorVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DocGeneratorVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DocGeneratorVo.SYNC_INSERT: this.insert(vo); break;
			case DocGeneratorVo.SYNC_UPDATE: this.update(vo); break;
			case DocGeneratorVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DocGeneratorVo> vos) {
		if (vos == null) return;
		for (DocGeneratorVo vo : vos) this.synchronize(vo);
}


}


package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.GenMetadataVo;

public abstract class BaseGenMetadataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_metadata";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_metadata WHERE cli_id = :cli_id AND gen_id = :gen_id AND metadata_code = :metadata_code";
	protected String SQL_INSERT					= "INSERT INTO gen_metadata (cli_id,gen_id,metadata_code,metadata_title,metadata_value,metadata_date_added) VALUES (:cli_id,:gen_id,:metadata_code,:metadata_title,:metadata_value,:metadata_date_added)";
	protected String SQL_UPDATE					= "UPDATE gen_metadata SET metadata_title = :metadata_title,metadata_value = :metadata_value,metadata_date_added = :metadata_date_added WHERE cli_id = :cli_id AND gen_id = :gen_id AND metadata_code = :metadata_code";
	protected String SQL_DELETE					= "DELETE FROM gen_metadata WHERE cli_id = :cli_id AND gen_id = :gen_id AND metadata_code = :metadata_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, metadata_code) DO UPDATE SET metadata_title = EXCLUDED.metadata_title, metadata_value = EXCLUDED.metadata_value, metadata_date_added = EXCLUDED.metadata_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenMetadataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(GenMetadataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("metadata_code", vo.getMetadataCode())
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("metadata_date_added", vo.getMetadataDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GenMetadataVo vo) {
		return new MapSqlParameterSource()
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("metadata_date_added", vo.getMetadataDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("metadata_code", vo.getMetadataCode());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GenMetadataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getMetadataCode());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, String metadataCode) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("metadata_code", metadataCode);
	}

	//--- Public methods ------------------------
	public Collection<GenMetadataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GenMetadataRowWrapper.getInstance()); }
	public GenMetadataVo findVo(Integer cliId, Integer genId, String metadataCode) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, metadataCode), GenMetadataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GenMetadataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(GenMetadataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GenMetadataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GenMetadataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GenMetadataVo.SYNC_INSERT: this.insert(vo); break;
			case GenMetadataVo.SYNC_UPDATE: this.update(vo); break;
			case GenMetadataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GenMetadataVo> vos) {
		if (vos == null) return;
		for (GenMetadataVo vo : vos) this.synchronize(vo);
}


}


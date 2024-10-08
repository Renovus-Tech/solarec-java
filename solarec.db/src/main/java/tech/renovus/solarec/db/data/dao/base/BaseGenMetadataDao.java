package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.GenMetadataVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseGenMetadataDao <T extends GenMetadataVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM gen_metadata";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_metadata WHERE cli_id = :cli_id AND gen_id = :gen_id AND metadata_code = :metadata_code";
	protected String SQL_INSERT					= "INSERT INTO gen_metadata (cli_id, gen_id, metadata_date_added, metadata_code, metadata_title, metadata_value) VALUES (:cli_id, :gen_id, :metadata_date_added, :metadata_code, :metadata_title, :metadata_value)";
	protected String SQL_UPDATE					= "UPDATE gen_metadata SET metadata_date_added = :metadata_date_added, metadata_title = :metadata_title, metadata_value = :metadata_value WHERE cli_id = :cli_id AND gen_id = :gen_id AND metadata_code = :metadata_code";
	protected String SQL_DELETE					= "DELETE FROM gen_metadata WHERE cli_id = :cli_id AND gen_id = :gen_id AND metadata_code = :metadata_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, metadata_code) DO UPDATE SET metadata_date_added = EXCLUDED.metadata_date_added, metadata_title = EXCLUDED.metadata_title, metadata_value = EXCLUDED.metadata_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseGenMetadataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenMetadataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenMetadataVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenMetadataVo.COLUMN_METADATA_DATE_ADDED, vo.getMetadataDateAdded())
			.addValue(GenMetadataVo.COLUMN_METADATA_CODE, vo.getMetadataCode())
			.addValue(GenMetadataVo.COLUMN_METADATA_TITLE, vo.getMetadataTitle())
			.addValue(GenMetadataVo.COLUMN_METADATA_VALUE, vo.getMetadataValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenMetadataVo.COLUMN_METADATA_DATE_ADDED, vo.getMetadataDateAdded())
			.addValue(GenMetadataVo.COLUMN_METADATA_TITLE, vo.getMetadataTitle())
			.addValue(GenMetadataVo.COLUMN_METADATA_VALUE, vo.getMetadataValue())
			.addValue(GenMetadataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenMetadataVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenMetadataVo.COLUMN_METADATA_CODE, vo.getMetadataCode());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getMetadataCode());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, String metadataCode) {
		return new MapSqlParameterSource()
			.addValue(GenMetadataVo.COLUMN_CLI_ID, cliId)
			.addValue(GenMetadataVo.COLUMN_GEN_ID, genId)
			.addValue(GenMetadataVo.COLUMN_METADATA_CODE, metadataCode);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenMetadataRowWrapper.getInstance()); }
	public GenMetadataVo findVo(Integer cliId, Integer genId, String metadataCode) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, metadataCode), GenMetadataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getMetadataCode()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

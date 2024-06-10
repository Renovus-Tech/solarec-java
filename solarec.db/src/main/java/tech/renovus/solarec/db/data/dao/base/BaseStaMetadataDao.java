package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.StaMetadataVo;

public abstract class BaseStaMetadataDao <T extends StaMetadataVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM sta_metadata";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_metadata WHERE cli_id = :cli_id AND sta_id = :sta_id AND metadata_name = :metadata_name";
	protected String SQL_INSERT					= "INSERT INTO sta_metadata (cli_id, sta_id, metadata_date_added, metadata_name, metadata_title, metadata_value) VALUES (:cli_id, :sta_id, :metadata_date_added, :metadata_name, :metadata_title, :metadata_value)";
	protected String SQL_UPDATE					= "UPDATE sta_metadata SET metadata_date_added = :metadata_date_added, metadata_title = :metadata_title, metadata_value = :metadata_value WHERE cli_id = :cli_id AND sta_id = :sta_id AND metadata_name = :metadata_name";
	protected String SQL_DELETE					= "DELETE FROM sta_metadata WHERE cli_id = :cli_id AND sta_id = :sta_id AND metadata_name = :metadata_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, metadata_name) DO UPDATE SET metadata_date_added = EXCLUDED.metadata_date_added, metadata_title = EXCLUDED.metadata_title, metadata_value = EXCLUDED.metadata_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStaMetadataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaMetadataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaMetadataVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaMetadataVo.COLUMN_METADATA_DATE_ADDED, vo.getMetadataDateAdded())
			.addValue(StaMetadataVo.COLUMN_METADATA_NAME, vo.getMetadataName())
			.addValue(StaMetadataVo.COLUMN_METADATA_TITLE, vo.getMetadataTitle())
			.addValue(StaMetadataVo.COLUMN_METADATA_VALUE, vo.getMetadataValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaMetadataVo.COLUMN_METADATA_DATE_ADDED, vo.getMetadataDateAdded())
			.addValue(StaMetadataVo.COLUMN_METADATA_TITLE, vo.getMetadataTitle())
			.addValue(StaMetadataVo.COLUMN_METADATA_VALUE, vo.getMetadataValue())
			.addValue(StaMetadataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaMetadataVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaMetadataVo.COLUMN_METADATA_NAME, vo.getMetadataName());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getMetadataName());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, String metadataName) {
		return new MapSqlParameterSource()
			.addValue(StaMetadataVo.COLUMN_CLI_ID, cliId)
			.addValue(StaMetadataVo.COLUMN_STA_ID, staId)
			.addValue(StaMetadataVo.COLUMN_METADATA_NAME, metadataName);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StaMetadataRowWrapper.getInstance()); }
	public StaMetadataVo findVo(Integer cliId, Integer staId, String metadataName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, metadataName), StaMetadataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getStaId(), vo.getMetadataName()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

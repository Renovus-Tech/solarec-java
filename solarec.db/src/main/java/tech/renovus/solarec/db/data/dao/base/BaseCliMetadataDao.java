package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.CliMetadataVo;

public abstract class BaseCliMetadataDao <T extends CliMetadataVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_metadata";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_metadata WHERE cli_id = :cli_id AND metadata_name = :metadata_name";
	protected String SQL_INSERT					= "INSERT INTO cli_metadata (cli_id, metadata_date_added, metadata_name, metadata_title, metadata_value) VALUES (:cli_id, :metadata_date_added, :metadata_name, :metadata_title, :metadata_value)";
	protected String SQL_UPDATE					= "UPDATE cli_metadata SET metadata_date_added = :metadata_date_added, metadata_title = :metadata_title, metadata_value = :metadata_value WHERE cli_id = :cli_id AND metadata_name = :metadata_name";
	protected String SQL_DELETE					= "DELETE FROM cli_metadata WHERE cli_id = :cli_id AND metadata_name = :metadata_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, metadata_name) DO UPDATE SET metadata_date_added = EXCLUDED.metadata_date_added, metadata_title = EXCLUDED.metadata_title, metadata_value = EXCLUDED.metadata_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliMetadataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("metadata_date_added", vo.getMetadataDateAdded())
			.addValue("metadata_name", vo.getMetadataName())
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("metadata_date_added", vo.getMetadataDateAdded())
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("cli_id", vo.getCliId())
			.addValue("metadata_name", vo.getMetadataName());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getMetadataName());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, String metadataName) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("metadata_name", metadataName);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliMetadataRowWrapper.getInstance()); }
	public CliMetadataVo findVo(Integer cliId, String metadataName) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, metadataName), CliMetadataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getMetadataName()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocMetadataRowWrapper;
import tech.renovus.solarec.db.data.vo.LocMetadataVo;

public abstract class BaseLocMetadataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_metadata";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_metadata WHERE cli_id = :cli_id AND loc_id = :loc_id AND metadata_name = :metadata_name";
	protected String SQL_INSERT					= "INSERT INTO loc_metadata (cli_id,loc_id,metadata_name,metadata_title,metadata_value,metadata_date_added) VALUES (:cli_id,:loc_id,:metadata_name,:metadata_title,:metadata_value,:metadata_date_added)";
	protected String SQL_UPDATE					= "UPDATE loc_metadata SET metadata_title = :metadata_title,metadata_value = :metadata_value,metadata_date_added = :metadata_date_added WHERE cli_id = :cli_id AND loc_id = :loc_id AND metadata_name = :metadata_name";
	protected String SQL_DELETE					= "DELETE FROM loc_metadata WHERE cli_id = :cli_id AND loc_id = :loc_id AND metadata_name = :metadata_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, metadata_name) DO UPDATE SET metadata_title = EXCLUDED.metadata_title, metadata_value = EXCLUDED.metadata_value, metadata_date_added = EXCLUDED.metadata_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocMetadataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocMetadataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("metadata_name", vo.getMetadataName())
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("metadata_date_added", vo.getMetadataDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocMetadataVo vo) {
		return new MapSqlParameterSource()
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("metadata_date_added", vo.getMetadataDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("metadata_name", vo.getMetadataName());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocMetadataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getMetadataName());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, String metadataName) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("metadata_name", metadataName);
	}

	//--- Public methods ------------------------
	public Collection<LocMetadataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocMetadataRowWrapper.getInstance()); }
	public LocMetadataVo findVo(Integer cliId, Integer locId, String metadataName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, metadataName), LocMetadataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocMetadataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocMetadataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocMetadataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocMetadataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocMetadataVo.SYNC_INSERT: this.insert(vo); break;
			case LocMetadataVo.SYNC_UPDATE: this.update(vo); break;
			case LocMetadataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocMetadataVo> vos) {
		if (vos == null) return;
		for (LocMetadataVo vo : vos) this.synchronize(vo);
}


}


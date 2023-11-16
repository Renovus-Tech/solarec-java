package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaMetadataRowWrapper;
import tech.renovus.solarec.vo.db.data.StaMetadataVo;

public abstract class BaseStaMetadataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM sta_metadata";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_metadata WHERE cli_id = :cli_id AND sta_id = :sta_id AND metadata_name = :metadata_name";
	protected String SQL_INSERT					= "INSERT INTO sta_metadata (cli_id,sta_id,metadata_name,metadata_title,metadata_value,metadata_date_added) VALUES (:cli_id,:sta_id,:metadata_name,:metadata_title,:metadata_value,:metadata_date_added)";
	protected String SQL_UPDATE					= "UPDATE sta_metadata SET metadata_title = :metadata_title,metadata_value = :metadata_value,metadata_date_added = :metadata_date_added WHERE cli_id = :cli_id AND sta_id = :sta_id AND metadata_name = :metadata_name";
	protected String SQL_DELETE					= "DELETE FROM sta_metadata WHERE cli_id = :cli_id AND sta_id = :sta_id AND metadata_name = :metadata_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, metadata_name) DO UPDATE SET metadata_title = EXCLUDED.metadata_title, metadata_value = EXCLUDED.metadata_value, metadata_date_added = EXCLUDED.metadata_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStaMetadataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StaMetadataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id", vo.getStaId())
			.addValue("metadata_name", vo.getMetadataName())
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("metadata_date_added", vo.getMetadataDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StaMetadataVo vo) {
		return new MapSqlParameterSource()
			.addValue("metadata_title", vo.getMetadataTitle())
			.addValue("metadata_value", vo.getMetadataValue())
			.addValue("metadata_date_added", vo.getMetadataDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id", vo.getStaId())
			.addValue("metadata_name", vo.getMetadataName());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StaMetadataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getMetadataName());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, String metadataName) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("sta_id", staId)
			.addValue("metadata_name", metadataName);
	}

	//--- Public methods ------------------------
	public Collection<StaMetadataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StaMetadataRowWrapper.getInstance()); }
	public StaMetadataVo findVo(Integer cliId, Integer staId, String metadataName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, metadataName), StaMetadataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StaMetadataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(StaMetadataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StaMetadataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StaMetadataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StaMetadataVo.SYNC_INSERT: this.insert(vo); break;
			case StaMetadataVo.SYNC_UPDATE: this.update(vo); break;
			case StaMetadataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StaMetadataVo> vos) {
		if (vos == null) return;
		for (StaMetadataVo vo : vos) this.synchronize(vo);
}


}


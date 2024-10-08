package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocSdgRowWrapper;
import tech.renovus.solarec.vo.db.data.LocSdgVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseLocSdgDao <T extends LocSdgVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM loc_sdg";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_sdg WHERE cli_id = :cli_id AND loc_id = :loc_id AND sdg_id = :sdg_id";
	protected String SQL_INSERT					= "INSERT INTO loc_sdg (cli_id, loc_id, sdg_id, loc_sdg_description) VALUES (:cli_id, :loc_id, :sdg_id, :loc_sdg_description)";
	protected String SQL_UPDATE					= "UPDATE loc_sdg SET loc_sdg_description = :loc_sdg_description WHERE cli_id = :cli_id AND loc_id = :loc_id AND sdg_id = :sdg_id";
	protected String SQL_DELETE					= "DELETE FROM loc_sdg WHERE cli_id = :cli_id AND loc_id = :loc_id AND sdg_id = :sdg_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, sdg_id) DO UPDATE SET loc_sdg_description = EXCLUDED.loc_sdg_description";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseLocSdgDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocSdgVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocSdgVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocSdgVo.COLUMN_SDG_ID, vo.getSdgId())
			.addValue(LocSdgVo.COLUMN_LOC_SDG_DESCRIPTION, vo.getLocSdgDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocSdgVo.COLUMN_LOC_SDG_DESCRIPTION, vo.getLocSdgDescription())
			.addValue(LocSdgVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocSdgVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocSdgVo.COLUMN_SDG_ID, vo.getSdgId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getSdgId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer sdgId) {
		return new MapSqlParameterSource()
			.addValue(LocSdgVo.COLUMN_CLI_ID, cliId)
			.addValue(LocSdgVo.COLUMN_LOC_ID, locId)
			.addValue(LocSdgVo.COLUMN_SDG_ID, sdgId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocSdgRowWrapper.getInstance()); }
	public LocSdgVo findVo(Integer cliId, Integer locId, Integer sdgId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, sdgId), LocSdgRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getSdgId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

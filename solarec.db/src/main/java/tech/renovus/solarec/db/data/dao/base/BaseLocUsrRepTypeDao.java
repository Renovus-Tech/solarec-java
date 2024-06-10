package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocUsrRepTypeRowWrapper;
import tech.renovus.solarec.vo.db.data.LocUsrRepTypeVo;

public abstract class BaseLocUsrRepTypeDao <T extends LocUsrRepTypeVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_usr_rep_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_usr_rep_type WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND rep_type_id = :rep_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_usr_rep_type (cli_id, loc_id, usr_id, rep_type_id, loc_usr_rep_type_flags) VALUES (:cli_id, :loc_id, :usr_id, :rep_type_id, :loc_usr_rep_type_flags)";
	protected String SQL_UPDATE					= "UPDATE loc_usr_rep_type SET loc_usr_rep_type_flags = :loc_usr_rep_type_flags WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND rep_type_id = :rep_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_usr_rep_type WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND rep_type_id = :rep_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id, rep_type_id) DO UPDATE SET loc_usr_rep_type_flags = EXCLUDED.loc_usr_rep_type_flags";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocUsrRepTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocUsrRepTypeVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocUsrRepTypeVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocUsrRepTypeVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(LocUsrRepTypeVo.COLUMN_REP_TYPE_ID, vo.getRepTypeId())
			.addValue(LocUsrRepTypeVo.COLUMN_LOC_USR_REP_TYPE_FLAGS, vo.getLocUsrRepTypeFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocUsrRepTypeVo.COLUMN_LOC_USR_REP_TYPE_FLAGS, vo.getLocUsrRepTypeFlags())
			.addValue(LocUsrRepTypeVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocUsrRepTypeVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocUsrRepTypeVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(LocUsrRepTypeVo.COLUMN_REP_TYPE_ID, vo.getRepTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId(), vo.getRepTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId, Integer repTypeId) {
		return new MapSqlParameterSource()
			.addValue(LocUsrRepTypeVo.COLUMN_CLI_ID, cliId)
			.addValue(LocUsrRepTypeVo.COLUMN_LOC_ID, locId)
			.addValue(LocUsrRepTypeVo.COLUMN_USR_ID, usrId)
			.addValue(LocUsrRepTypeVo.COLUMN_REP_TYPE_ID, repTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocUsrRepTypeRowWrapper.getInstance()); }
	public LocUsrRepTypeVo findVo(Integer cliId, Integer locId, Integer usrId, Integer repTypeId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId, repTypeId), LocUsrRepTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getUsrId(), vo.getRepTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

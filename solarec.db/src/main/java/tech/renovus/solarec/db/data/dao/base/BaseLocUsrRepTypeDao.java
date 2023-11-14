package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocUsrRepTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.LocUsrRepTypeVo;

public abstract class BaseLocUsrRepTypeDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_usr_rep_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_usr_rep_type WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND rep_type_id = :rep_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_usr_rep_type (cli_id,loc_id,usr_id,rep_type_id,loc_usr_rep_type_flags) VALUES (:cli_id,:loc_id,:usr_id,:rep_type_id,:loc_usr_rep_type_flags)";
	protected String SQL_UPDATE					= "UPDATE loc_usr_rep_type SET loc_usr_rep_type_flags = :loc_usr_rep_type_flags WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND rep_type_id = :rep_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_usr_rep_type WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND rep_type_id = :rep_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id, rep_type_id) DO UPDATE SET loc_usr_rep_type_flags = EXCLUDED.loc_usr_rep_type_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocUsrRepTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocUsrRepTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("rep_type_id", vo.getRepTypeId())
			.addValue("loc_usr_rep_type_flags", vo.getLocUsrRepTypeFlags());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocUsrRepTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("loc_usr_rep_type_flags", vo.getLocUsrRepTypeFlags())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("rep_type_id", vo.getRepTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocUsrRepTypeVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId(), vo.getRepTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId, Integer repTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("usr_id", usrId)
			.addValue("rep_type_id", repTypeId);
	}

	//--- Public methods ------------------------
	public Collection<LocUsrRepTypeVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocUsrRepTypeRowWrapper.getInstance()); }
	public LocUsrRepTypeVo findVo(Integer cliId, Integer locId, Integer usrId, Integer repTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId, repTypeId), LocUsrRepTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocUsrRepTypeVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocUsrRepTypeVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocUsrRepTypeVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocUsrRepTypeVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocUsrRepTypeVo.SYNC_INSERT: this.insert(vo); break;
			case LocUsrRepTypeVo.SYNC_UPDATE: this.update(vo); break;
			case LocUsrRepTypeVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocUsrRepTypeVo> vos) {
		if (vos == null) return;
		for (LocUsrRepTypeVo vo : vos) this.synchronize(vo);
}


}


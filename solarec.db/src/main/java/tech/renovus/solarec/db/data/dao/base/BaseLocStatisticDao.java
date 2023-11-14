package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocStatisticRowWrapper;
import tech.renovus.solarec.db.data.vo.LocStatisticVo;

public abstract class BaseLocStatisticDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_statistic";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_statistic WHERE cli_id = :cli_id AND loc_id = :loc_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_statistic (cli_id,loc_id,stat_date,stat_type_id,stat_pro_id,stat_value,stat_date_added) VALUES (:cli_id,:loc_id,:stat_date,:stat_type_id,:stat_pro_id,:stat_value,:stat_date_added)";
	protected String SQL_UPDATE					= "UPDATE loc_statistic SET stat_pro_id = :stat_pro_id,stat_value = :stat_value,stat_date_added = :stat_date_added WHERE cli_id = :cli_id AND loc_id = :loc_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_statistic WHERE cli_id = :cli_id AND loc_id = :loc_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, stat_date, stat_type_id) DO UPDATE SET stat_pro_id = EXCLUDED.stat_pro_id, stat_value = EXCLUDED.stat_value, stat_date_added = EXCLUDED.stat_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocStatisticDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocStatisticVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("stat_date", vo.getStatDate())
			.addValue("stat_type_id", vo.getStatTypeId())
			.addValue("stat_pro_id", vo.getStatProId())
			.addValue("stat_value", vo.getStatValue())
			.addValue("stat_date_added", vo.getStatDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocStatisticVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_pro_id", vo.getStatProId())
			.addValue("stat_value", vo.getStatValue())
			.addValue("stat_date_added", vo.getStatDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("stat_date", vo.getStatDate())
			.addValue("stat_type_id", vo.getStatTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocStatisticVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getStatDate(), vo.getStatTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("stat_date", statDate)
			.addValue("stat_type_id", statTypeId);
	}

	//--- Public methods ------------------------
	public Collection<LocStatisticVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocStatisticRowWrapper.getInstance()); }
	public LocStatisticVo findVo(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, statDate, statTypeId), LocStatisticRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocStatisticVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocStatisticVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocStatisticVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocStatisticVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocStatisticVo.SYNC_INSERT: this.insert(vo); break;
			case LocStatisticVo.SYNC_UPDATE: this.update(vo); break;
			case LocStatisticVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocStatisticVo> vos) {
		if (vos == null) return;
		for (LocStatisticVo vo : vos) this.synchronize(vo);
}


}


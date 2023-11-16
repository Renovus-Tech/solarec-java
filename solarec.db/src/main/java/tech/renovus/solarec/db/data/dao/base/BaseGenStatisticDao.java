package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenStatisticRowWrapper;
import tech.renovus.solarec.vo.db.data.GenStatisticVo;

public abstract class BaseGenStatisticDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_statistic";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_statistic WHERE cli_id = :cli_id AND gen_id = :gen_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO gen_statistic (cli_id,gen_id,stat_date,stat_type_id,stat_pro_id,stat_value,stat_date_added) VALUES (:cli_id,:gen_id,:stat_date,:stat_type_id,:stat_pro_id,:stat_value,:stat_date_added)";
	protected String SQL_UPDATE					= "UPDATE gen_statistic SET stat_pro_id = :stat_pro_id,stat_value = :stat_value,stat_date_added = :stat_date_added WHERE cli_id = :cli_id AND gen_id = :gen_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM gen_statistic WHERE cli_id = :cli_id AND gen_id = :gen_id AND stat_date = :stat_date AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, stat_date, stat_type_id) DO UPDATE SET stat_pro_id = EXCLUDED.stat_pro_id, stat_value = EXCLUDED.stat_value, stat_date_added = EXCLUDED.stat_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenStatisticDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(GenStatisticVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("stat_date", vo.getStatDate())
			.addValue("stat_type_id", vo.getStatTypeId())
			.addValue("stat_pro_id", vo.getStatProId())
			.addValue("stat_value", vo.getStatValue())
			.addValue("stat_date_added", vo.getStatDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GenStatisticVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_pro_id", vo.getStatProId())
			.addValue("stat_value", vo.getStatValue())
			.addValue("stat_date_added", vo.getStatDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("stat_date", vo.getStatDate())
			.addValue("stat_type_id", vo.getStatTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GenStatisticVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getStatDate(), vo.getStatTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("stat_date", statDate)
			.addValue("stat_type_id", statTypeId);
	}

	//--- Public methods ------------------------
	public Collection<GenStatisticVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GenStatisticRowWrapper.getInstance()); }
	public GenStatisticVo findVo(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, statDate, statTypeId), GenStatisticRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GenStatisticVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(GenStatisticVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GenStatisticVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GenStatisticVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GenStatisticVo.SYNC_INSERT: this.insert(vo); break;
			case GenStatisticVo.SYNC_UPDATE: this.update(vo); break;
			case GenStatisticVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GenStatisticVo> vos) {
		if (vos == null) return;
		for (GenStatisticVo vo : vos) this.synchronize(vo);
}


}


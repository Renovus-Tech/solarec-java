package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliSettingRowWrapper;
import tech.renovus.solarec.db.data.vo.CliSettingVo;

public abstract class BaseCliSettingDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_setting";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_setting WHERE cli_id = :cli_id AND cli_set_name = :cli_set_name";
	protected String SQL_INSERT					= "INSERT INTO cli_setting (cli_id,cli_set_name,cli_set_value) VALUES (:cli_id,:cli_set_name,:cli_set_value)";
	protected String SQL_UPDATE					= "UPDATE cli_setting SET cli_set_value = :cli_set_value WHERE cli_id = :cli_id AND cli_set_name = :cli_set_name";
	protected String SQL_DELETE					= "DELETE FROM cli_setting WHERE cli_id = :cli_id AND cli_set_name = :cli_set_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, cli_set_name) DO UPDATE SET cli_set_value = EXCLUDED.cli_set_value";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliSettingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliSettingVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("cli_set_name", vo.getCliSetName())
			.addValue("cli_set_value", vo.getCliSetValue());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliSettingVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_set_value", vo.getCliSetValue())
			.addValue("cli_id", vo.getCliId())
			.addValue("cli_set_name", vo.getCliSetName());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliSettingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getCliSetName());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, String cliSetName) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("cli_set_name", cliSetName);
	}

	//--- Public methods ------------------------
	public Collection<CliSettingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliSettingRowWrapper.getInstance()); }
	public CliSettingVo findVo(Integer cliId, String cliSetName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, cliSetName), CliSettingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliSettingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(CliSettingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliSettingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliSettingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliSettingVo.SYNC_INSERT: this.insert(vo); break;
			case CliSettingVo.SYNC_UPDATE: this.update(vo); break;
			case CliSettingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliSettingVo> vos) {
		if (vos == null) return;
		for (CliSettingVo vo : vos) this.synchronize(vo);
}


}


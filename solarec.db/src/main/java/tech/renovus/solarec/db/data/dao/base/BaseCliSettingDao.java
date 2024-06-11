package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliSettingRowWrapper;
import tech.renovus.solarec.vo.db.data.CliSettingVo;

public abstract class BaseCliSettingDao <T extends CliSettingVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_setting";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_setting WHERE cli_id = :cli_id AND cli_set_name = :cli_set_name";
	protected String SQL_INSERT					= "INSERT INTO cli_setting (cli_id, cli_set_name, cli_set_value) VALUES (:cli_id, :cli_set_name, :cli_set_value)";
	protected String SQL_UPDATE					= "UPDATE cli_setting SET cli_set_value = :cli_set_value WHERE cli_id = :cli_id AND cli_set_name = :cli_set_name";
	protected String SQL_DELETE					= "DELETE FROM cli_setting WHERE cli_id = :cli_id AND cli_set_name = :cli_set_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, cli_set_name) DO UPDATE SET cli_set_value = EXCLUDED.cli_set_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliSettingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliSettingVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliSettingVo.COLUMN_CLI_SET_NAME, vo.getCliSetName())
			.addValue(CliSettingVo.COLUMN_CLI_SET_VALUE, vo.getCliSetValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliSettingVo.COLUMN_CLI_SET_VALUE, vo.getCliSetValue())
			.addValue(CliSettingVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliSettingVo.COLUMN_CLI_SET_NAME, vo.getCliSetName());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getCliSetName());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, String cliSetName) {
		return new MapSqlParameterSource()
			.addValue(CliSettingVo.COLUMN_CLI_ID, cliId)
			.addValue(CliSettingVo.COLUMN_CLI_SET_NAME, cliSetName);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliSettingRowWrapper.getInstance()); }
	public CliSettingVo findVo(Integer cliId, String cliSetName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, cliSetName), CliSettingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getCliSetName()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

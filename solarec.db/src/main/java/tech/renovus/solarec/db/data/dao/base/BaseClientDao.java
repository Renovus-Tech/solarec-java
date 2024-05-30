package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ClientRowWrapper;
import tech.renovus.solarec.vo.db.data.ClientVo;

public abstract class BaseClientDao <T extends ClientVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM client";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM client WHERE cli_id_auto = :cli_id_auto";
	protected String SQL_INSERT					= "INSERT INTO client (cli_demo_date, data_def_id, cli_name_address, cli_flags, cli_gmt, cli_sec_code, cli_name, cli_name_legal) VALUES (:cli_demo_date, :data_def_id, :cli_name_address, :cli_flags, :cli_gmt, :cli_sec_code, :cli_name, :cli_name_legal)";
	protected String SQL_UPDATE					= "UPDATE client SET cli_demo_date = :cli_demo_date, data_def_id = :data_def_id, cli_name_address = :cli_name_address, cli_flags = :cli_flags, cli_gmt = :cli_gmt, cli_sec_code = :cli_sec_code, cli_name = :cli_name, cli_name_legal = :cli_name_legal WHERE cli_id_auto = :cli_id_auto";
	protected String SQL_DELETE					= "DELETE FROM client WHERE cli_id_auto = :cli_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id_auto) DO UPDATE SET cli_demo_date = EXCLUDED.cli_demo_date, data_def_id = EXCLUDED.data_def_id, cli_name_address = EXCLUDED.cli_name_address, cli_flags = EXCLUDED.cli_flags, cli_gmt = EXCLUDED.cli_gmt, cli_sec_code = EXCLUDED.cli_sec_code, cli_name = EXCLUDED.cli_name, cli_name_legal = EXCLUDED.cli_name_legal";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"cli_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseClientDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_demo_date", vo.getCliDemoDate())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("cli_id_auto", vo.getCliId())
			.addValue("cli_name_address", vo.getCliNameAddress())
			.addValue("cli_flags", vo.getCliFlags())
			.addValue("cli_gmt", vo.getCliGmt())
			.addValue("cli_sec_code", vo.getCliSecCode())
			.addValue("cli_name", vo.getCliName())
			.addValue("cli_name_legal", vo.getCliNameLegal());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_demo_date", vo.getCliDemoDate())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("cli_name_address", vo.getCliNameAddress())
			.addValue("cli_flags", vo.getCliFlags())
			.addValue("cli_gmt", vo.getCliGmt())
			.addValue("cli_sec_code", vo.getCliSecCode())
			.addValue("cli_name", vo.getCliName())
			.addValue("cli_name_legal", vo.getCliNameLegal())
			.addValue("cli_id_auto", vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("cli_id_auto", cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, ClientRowWrapper.getInstance()); }
	public ClientVo findVo(Integer cliId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId), ClientRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setCliId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

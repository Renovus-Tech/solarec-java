package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ClientRowWrapper;
import tech.renovus.solarec.db.data.vo.ClientVo;

public abstract class BaseClientDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM client";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM client WHERE cli_id_auto = :cli_id_auto";
	protected String SQL_INSERT					= "INSERT INTO client (cli_name,cli_name_legal,cli_name_address,cli_flags,data_def_id,cli_gmt,cli_demo_date,cli_sec_code) VALUES (:cli_name,:cli_name_legal,:cli_name_address,:cli_flags,:data_def_id,:cli_gmt,:cli_demo_date,:cli_sec_code)";
	protected String SQL_UPDATE					= "UPDATE client SET cli_name = :cli_name,cli_name_legal = :cli_name_legal,cli_name_address = :cli_name_address,cli_flags = :cli_flags,data_def_id = :data_def_id,cli_gmt = :cli_gmt,cli_demo_date = :cli_demo_date,cli_sec_code = :cli_sec_code WHERE cli_id_auto = :cli_id_auto";
	protected String SQL_DELETE					= "DELETE FROM client WHERE cli_id_auto = :cli_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id_auto) DO UPDATE SET cli_name = EXCLUDED.cli_name, cli_name_legal = EXCLUDED.cli_name_legal, cli_name_address = EXCLUDED.cli_name_address, cli_flags = EXCLUDED.cli_flags, data_def_id = EXCLUDED.data_def_id, cli_gmt = EXCLUDED.cli_gmt, cli_demo_date = EXCLUDED.cli_demo_date, cli_sec_code = EXCLUDED.cli_sec_code";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"cli_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseClientDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(ClientVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_name", vo.getCliName())
			.addValue("cli_name_legal", vo.getCliNameLegal())
			.addValue("cli_name_address", vo.getCliNameAddress())
			.addValue("cli_flags", vo.getCliFlags())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("cli_gmt", vo.getCliGmt())
			.addValue("cli_demo_date", vo.getCliDemoDate())
			.addValue("cli_sec_code", vo.getCliSecCode());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(ClientVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_name", vo.getCliName())
			.addValue("cli_name_legal", vo.getCliNameLegal())
			.addValue("cli_name_address", vo.getCliNameAddress())
			.addValue("cli_flags", vo.getCliFlags())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("cli_gmt", vo.getCliGmt())
			.addValue("cli_demo_date", vo.getCliDemoDate())
			.addValue("cli_sec_code", vo.getCliSecCode())
			.addValue("cli_id_auto", vo.getCliId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(ClientVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("cli_id_auto", cliId);
	}

	//--- Public methods ------------------------
	public Collection<ClientVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, ClientRowWrapper.getInstance()); }
	public ClientVo findVo(Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId), ClientRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(ClientVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setCliId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(ClientVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(ClientVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(ClientVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case ClientVo.SYNC_INSERT: this.insert(vo); break;
			case ClientVo.SYNC_UPDATE: this.update(vo); break;
			case ClientVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<ClientVo> vos) {
		if (vos == null) return;
		for (ClientVo vo : vos) this.synchronize(vo);
}


}


package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenDataRowWrapper;
import tech.renovus.solarec.db.data.vo.GenDataVo;

public abstract class BaseGenDataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_data";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_data WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO gen_data (cli_id,gen_id,data_date,data_type_id,data_pro_id,data_value,data_date_added) VALUES (:cli_id,:gen_id,:data_date,:data_type_id,:data_pro_id,:data_value,:data_date_added)";
	protected String SQL_UPDATE					= "UPDATE gen_data SET data_pro_id = :data_pro_id,data_value = :data_value,data_date_added = :data_date_added WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM gen_data WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(GenDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId())
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GenDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GenDataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getDataDate(), vo.getDataTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("data_date", dataDate)
			.addValue("data_type_id", dataTypeId);
	}

	//--- Public methods ------------------------
	public Collection<GenDataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GenDataRowWrapper.getInstance()); }
	public GenDataVo findVo(Integer cliId, Integer genId, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, dataDate, dataTypeId), GenDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GenDataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(GenDataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GenDataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GenDataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GenDataVo.SYNC_INSERT: this.insert(vo); break;
			case GenDataVo.SYNC_UPDATE: this.update(vo); break;
			case GenDataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GenDataVo> vos) {
		if (vos == null) return;
		for (GenDataVo vo : vos) this.synchronize(vo);
}


}


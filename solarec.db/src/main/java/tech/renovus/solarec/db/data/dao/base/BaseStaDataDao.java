package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaDataRowWrapper;
import tech.renovus.solarec.db.data.vo.StaDataVo;

public abstract class BaseStaDataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM sta_data";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_data WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO sta_data (cli_id,sta_id,data_date,data_type_id,data_pro_id,data_value,data_date_added) VALUES (:cli_id,:sta_id,:data_date,:data_type_id,:data_pro_id,:data_value,:data_date_added)";
	protected String SQL_UPDATE					= "UPDATE sta_data SET data_pro_id = :data_pro_id,data_value = :data_value,data_date_added = :data_date_added WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM sta_data WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStaDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(StaDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id", vo.getStaId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId())
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StaDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id", vo.getStaId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StaDataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getDataDate(), vo.getDataTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("sta_id", staId)
			.addValue("data_date", dataDate)
			.addValue("data_type_id", dataTypeId);
	}

	//--- Public methods ------------------------
	public Collection<StaDataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StaDataRowWrapper.getInstance()); }
	public StaDataVo findVo(Integer cliId, Integer staId, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, dataDate, dataTypeId), StaDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StaDataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(StaDataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StaDataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StaDataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StaDataVo.SYNC_INSERT: this.insert(vo); break;
			case StaDataVo.SYNC_UPDATE: this.update(vo); break;
			case StaDataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StaDataVo> vos) {
		if (vos == null) return;
		for (StaDataVo vo : vos) this.synchronize(vo);
}


}


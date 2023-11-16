package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocDataRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataVo;

public abstract class BaseLocDataDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_data";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_data (cli_id,loc_id,data_date,data_type_id,data_pro_id,data_value,data_date_added) VALUES (:cli_id,:loc_id,:data_date,:data_type_id,:data_pro_id,:data_value,:data_date_added)";
	protected String SQL_UPDATE					= "UPDATE loc_data SET data_pro_id = :data_pro_id,data_value = :data_value,data_date_added = :data_date_added WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(LocDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId())
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocDataVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocDataVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getDataDate(), vo.getDataTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("data_date", dataDate)
			.addValue("data_type_id", dataTypeId);
	}

	//--- Public methods ------------------------
	public Collection<LocDataVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocDataRowWrapper.getInstance()); }
	public LocDataVo findVo(Integer cliId, Integer locId, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, dataDate, dataTypeId), LocDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocDataVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocDataVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocDataVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocDataVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocDataVo.SYNC_INSERT: this.insert(vo); break;
			case LocDataVo.SYNC_UPDATE: this.update(vo); break;
			case LocDataVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocDataVo> vos) {
		if (vos == null) return;
		for (LocDataVo vo : vos) this.synchronize(vo);
}


}


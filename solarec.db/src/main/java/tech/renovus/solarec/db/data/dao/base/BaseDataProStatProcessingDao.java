package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataProStatProcessingRowWrapper;
import tech.renovus.solarec.db.data.vo.DataProStatProcessingVo;

public abstract class BaseDataProStatProcessingDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_pro_stat_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_pro_stat_processing WHERE data_pro_id = :data_pro_id AND stat_pro_id = :stat_pro_id";
	protected String SQL_INSERT					= "INSERT INTO data_pro_stat_processing (data_pro_id,stat_pro_id) VALUES (:data_pro_id,:stat_pro_id)";
	protected String SQL_UPDATE					= "UPDATE data_pro_stat_processing SET  WHERE data_pro_id = :data_pro_id AND stat_pro_id = :stat_pro_id";
	protected String SQL_DELETE					= "DELETE FROM data_pro_stat_processing WHERE data_pro_id = :data_pro_id AND stat_pro_id = :stat_pro_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_pro_id, stat_pro_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataProStatProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DataProStatProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("stat_pro_id", vo.getStatProId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DataProStatProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("stat_pro_id", vo.getStatProId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DataProStatProcessingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getDataProId(), vo.getStatProId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer dataProId, Integer statProId) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", dataProId)
			.addValue("stat_pro_id", statProId);
	}

	//--- Public methods ------------------------
	public Collection<DataProStatProcessingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DataProStatProcessingRowWrapper.getInstance()); }
	public DataProStatProcessingVo findVo(Integer dataProId, Integer statProId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataProId, statProId), DataProStatProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DataProStatProcessingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DataProStatProcessingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DataProStatProcessingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DataProStatProcessingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DataProStatProcessingVo.SYNC_INSERT: this.insert(vo); break;
			case DataProStatProcessingVo.SYNC_UPDATE: this.update(vo); break;
			case DataProStatProcessingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DataProStatProcessingVo> vos) {
		if (vos == null) return;
		for (DataProStatProcessingVo vo : vos) this.synchronize(vo);
}


}


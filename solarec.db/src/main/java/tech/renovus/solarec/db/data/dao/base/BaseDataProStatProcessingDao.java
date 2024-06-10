package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataProStatProcessingRowWrapper;
import tech.renovus.solarec.vo.db.data.DataProStatProcessingVo;

public abstract class BaseDataProStatProcessingDao <T extends DataProStatProcessingVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_pro_stat_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_pro_stat_processing WHERE data_pro_id = :data_pro_id AND stat_pro_id = :stat_pro_id";
	protected String SQL_INSERT					= "INSERT INTO data_pro_stat_processing (data_pro_id, stat_pro_id) VALUES (:data_pro_id, :stat_pro_id)";
	protected String SQL_UPDATE					= "UPDATE data_pro_stat_processing SET  WHERE data_pro_id = :data_pro_id AND stat_pro_id = :stat_pro_id";
	protected String SQL_DELETE					= "DELETE FROM data_pro_stat_processing WHERE data_pro_id = :data_pro_id AND stat_pro_id = :stat_pro_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_pro_id, stat_pro_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataProStatProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataProStatProcessingVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(DataProStatProcessingVo.COLUMN_STAT_PRO_ID, vo.getStatProId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataProStatProcessingVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(DataProStatProcessingVo.COLUMN_STAT_PRO_ID, vo.getStatProId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataProId(), vo.getStatProId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataProId, Integer statProId) {
		return new MapSqlParameterSource()
			.addValue(DataProStatProcessingVo.COLUMN_DATA_PRO_ID, dataProId)
			.addValue(DataProStatProcessingVo.COLUMN_STAT_PRO_ID, statProId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataProStatProcessingRowWrapper.getInstance()); }
	public DataProStatProcessingVo findVo(Integer dataProId, Integer statProId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataProId, statProId), DataProStatProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataProId(), vo.getStatProId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

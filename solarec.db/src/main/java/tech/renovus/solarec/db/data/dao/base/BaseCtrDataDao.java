package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CtrDataRowWrapper;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

public abstract class BaseCtrDataDao <T extends CtrDataVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM ctr_data";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM ctr_data WHERE ctr_id = :ctr_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO ctr_data (ctr_id, data_date, data_type_id, data_pro_id, data_value, data_date_added) VALUES (:ctr_id, :data_date, :data_type_id, :data_pro_id, :data_value, :data_date_added)";
	protected String SQL_UPDATE					= "UPDATE ctr_data SET data_pro_id = :data_pro_id, data_value = :data_value, data_date_added = :data_date_added WHERE ctr_id = :ctr_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM ctr_data WHERE ctr_id = :ctr_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (ctr_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCtrDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("ctr_id", vo.getCtrId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId())
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("data_value", vo.getDataValue())
			.addValue("data_date_added", vo.getDataDateAdded())
			.addValue("ctr_id", vo.getCtrId())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCtrId(), vo.getDataDate(), vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer ctrId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("ctr_id", ctrId)
			.addValue("data_date", dataDate)
			.addValue("data_type_id", dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CtrDataRowWrapper.getInstance()); }
	public CtrDataVo findVo(Integer ctrId, java.util.Date dataDate, Integer dataTypeId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(ctrId, dataDate, dataTypeId), CtrDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCtrId(), vo.getDataDate(), vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

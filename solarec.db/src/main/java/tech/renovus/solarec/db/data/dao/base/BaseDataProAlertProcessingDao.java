package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataProAlertProcessingRowWrapper;
import tech.renovus.solarec.vo.db.data.DataProAlertProcessingVo;

public abstract class BaseDataProAlertProcessingDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_pro_alert_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_pro_alert_processing WHERE data_pro_id = :data_pro_id AND alert_pro_id = :alert_pro_id";
	protected String SQL_INSERT					= "INSERT INTO data_pro_alert_processing (data_pro_id,alert_pro_id) VALUES (:data_pro_id,:alert_pro_id)";
	protected String SQL_UPDATE					= "UPDATE data_pro_alert_processing SET  WHERE data_pro_id = :data_pro_id AND alert_pro_id = :alert_pro_id";
	protected String SQL_DELETE					= "DELETE FROM data_pro_alert_processing WHERE data_pro_id = :data_pro_id AND alert_pro_id = :alert_pro_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_pro_id, alert_pro_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataProAlertProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DataProAlertProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("alert_pro_id", vo.getAlertProId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DataProAlertProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", vo.getDataProId())
			.addValue("alert_pro_id", vo.getAlertProId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DataProAlertProcessingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getDataProId(), vo.getAlertProId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer dataProId, Integer alertProId) {
		return new MapSqlParameterSource()
			.addValue("data_pro_id", dataProId)
			.addValue("alert_pro_id", alertProId);
	}

	//--- Public methods ------------------------
	public Collection<DataProAlertProcessingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DataProAlertProcessingRowWrapper.getInstance()); }
	public DataProAlertProcessingVo findVo(Integer dataProId, Integer alertProId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataProId, alertProId), DataProAlertProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DataProAlertProcessingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DataProAlertProcessingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DataProAlertProcessingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DataProAlertProcessingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DataProAlertProcessingVo.SYNC_INSERT: this.insert(vo); break;
			case DataProAlertProcessingVo.SYNC_UPDATE: this.update(vo); break;
			case DataProAlertProcessingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DataProAlertProcessingVo> vos) {
		if (vos == null) return;
		for (DataProAlertProcessingVo vo : vos) this.synchronize(vo);
}


}


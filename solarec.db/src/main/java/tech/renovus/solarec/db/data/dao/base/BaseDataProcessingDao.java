package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataProcessingRowWrapper;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;

public abstract class BaseDataProcessingDao <T extends DataProcessingVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_processing WHERE data_pro_id_auto = :data_pro_id_auto";
	protected String SQL_INSERT					= "INSERT INTO data_processing (data_def_id, cli_id, loc_id, tri_id, data_pro_result, gen_id, data_pro_date_start, data_pro_date_end, data_pro_file_name, data_pro_file_log) VALUES (:data_def_id, :cli_id, :loc_id, :tri_id, :data_pro_result, :gen_id, :data_pro_date_start, :data_pro_date_end, :data_pro_file_name, :data_pro_file_log)";
	protected String SQL_UPDATE					= "UPDATE data_processing SET data_def_id = :data_def_id, cli_id = :cli_id, loc_id = :loc_id, tri_id = :tri_id, data_pro_result = :data_pro_result, gen_id = :gen_id, data_pro_date_start = :data_pro_date_start, data_pro_date_end = :data_pro_date_end, data_pro_file_name = :data_pro_file_name, data_pro_file_log = :data_pro_file_log WHERE data_pro_id_auto = :data_pro_id_auto";
	protected String SQL_DELETE					= "DELETE FROM data_processing WHERE data_pro_id_auto = :data_pro_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_pro_id_auto) DO UPDATE SET data_def_id = EXCLUDED.data_def_id, cli_id = EXCLUDED.cli_id, loc_id = EXCLUDED.loc_id, tri_id = EXCLUDED.tri_id, data_pro_result = EXCLUDED.data_pro_result, gen_id = EXCLUDED.gen_id, data_pro_date_start = EXCLUDED.data_pro_date_start, data_pro_date_end = EXCLUDED.data_pro_date_end, data_pro_file_name = EXCLUDED.data_pro_file_name, data_pro_file_log = EXCLUDED.data_pro_file_log";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"data_pro_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(DataProcessingVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(DataProcessingVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DataProcessingVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(DataProcessingVo.COLUMN_TRI_ID, vo.getTriId())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_RESULT, vo.getDataProResult())
			.addValue(DataProcessingVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_DATE_START, vo.getDataProDateStart())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_DATE_END, vo.getDataProDateEnd())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_FILE_NAME, vo.getDataProFileName())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_FILE_LOG, vo.getDataProFileLog());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataProcessingVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(DataProcessingVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(DataProcessingVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(DataProcessingVo.COLUMN_TRI_ID, vo.getTriId())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_RESULT, vo.getDataProResult())
			.addValue(DataProcessingVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_DATE_START, vo.getDataProDateStart())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_DATE_END, vo.getDataProDateEnd())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_FILE_NAME, vo.getDataProFileName())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_FILE_LOG, vo.getDataProFileLog())
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_ID, vo.getDataProId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataProId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataProId) {
		return new MapSqlParameterSource()
			.addValue(DataProcessingVo.COLUMN_DATA_PRO_ID, dataProId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataProcessingRowWrapper.getInstance()); }
	public DataProcessingVo findVo(Integer dataProId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataProId), DataProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setDataProId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataProId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

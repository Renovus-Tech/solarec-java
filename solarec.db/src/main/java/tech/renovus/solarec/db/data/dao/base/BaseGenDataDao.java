package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenDataRowWrapper;
import tech.renovus.solarec.vo.db.data.GenDataVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseGenDataDao <T extends GenDataVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM gen_data";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_data WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO gen_data (cli_id, gen_id, data_date, data_type_id, data_pro_id, data_value, data_date_added, gen_data_cert_prov_data) VALUES (:cli_id, :gen_id, :data_date, :data_type_id, :data_pro_id, :data_value, :data_date_added, :gen_data_cert_prov_data)";
	protected String SQL_UPDATE					= "UPDATE gen_data SET data_pro_id = :data_pro_id, data_value = :data_value, data_date_added = :data_date_added, gen_data_cert_prov_data = :gen_data_cert_prov_data WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM gen_data WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added, gen_data_cert_prov_data = EXCLUDED.gen_data_cert_prov_data";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseGenDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenDataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenDataVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenDataVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(GenDataVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId())
			.addValue(GenDataVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(GenDataVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(GenDataVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded())
			.addValue(GenDataVo.COLUMN_GEN_DATA_CERT_PROV_DATA, vo.getGenDataCertProvData());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenDataVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(GenDataVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(GenDataVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded())
			.addValue(GenDataVo.COLUMN_GEN_DATA_CERT_PROV_DATA, vo.getGenDataCertProvData())
			.addValue(GenDataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenDataVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenDataVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(GenDataVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getDataDate(), vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue(GenDataVo.COLUMN_CLI_ID, cliId)
			.addValue(GenDataVo.COLUMN_GEN_ID, genId)
			.addValue(GenDataVo.COLUMN_DATA_DATE, dataDate)
			.addValue(GenDataVo.COLUMN_DATA_TYPE_ID, dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenDataRowWrapper.getInstance()); }
	public GenDataVo findVo(Integer cliId, Integer genId, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, dataDate, dataTypeId), GenDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getDataDate(), vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
			default: 
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}

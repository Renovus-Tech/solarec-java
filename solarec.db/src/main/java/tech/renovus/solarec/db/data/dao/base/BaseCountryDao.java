package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CountryRowWrapper;
import tech.renovus.solarec.vo.db.data.CountryVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseCountryDao <T extends CountryVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM country";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM country WHERE ctr_id_auto = :ctr_id_auto";
	protected String SQL_INSERT					= "INSERT INTO country (ctr_coord_lng, ctr_data_date_max, ctr_data_date_min, ctr_coord_lat, ctr_code_phone, ctr_name, ctr_name_show, ctr_code_2, ctr_code_3) VALUES (:ctr_coord_lng, :ctr_data_date_max, :ctr_data_date_min, :ctr_coord_lat, :ctr_code_phone, :ctr_name, :ctr_name_show, :ctr_code_2, :ctr_code_3)";
	protected String SQL_UPDATE					= "UPDATE country SET ctr_coord_lng = :ctr_coord_lng, ctr_data_date_max = :ctr_data_date_max, ctr_data_date_min = :ctr_data_date_min, ctr_coord_lat = :ctr_coord_lat, ctr_code_phone = :ctr_code_phone, ctr_name = :ctr_name, ctr_name_show = :ctr_name_show, ctr_code_2 = :ctr_code_2, ctr_code_3 = :ctr_code_3 WHERE ctr_id_auto = :ctr_id_auto";
	protected String SQL_DELETE					= "DELETE FROM country WHERE ctr_id_auto = :ctr_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (ctr_id_auto) DO UPDATE SET ctr_coord_lng = EXCLUDED.ctr_coord_lng, ctr_data_date_max = EXCLUDED.ctr_data_date_max, ctr_data_date_min = EXCLUDED.ctr_data_date_min, ctr_coord_lat = EXCLUDED.ctr_coord_lat, ctr_code_phone = EXCLUDED.ctr_code_phone, ctr_name = EXCLUDED.ctr_name, ctr_name_show = EXCLUDED.ctr_name_show, ctr_code_2 = EXCLUDED.ctr_code_2, ctr_code_3 = EXCLUDED.ctr_code_3";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"ctr_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCountryDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CountryVo.COLUMN_CTR_COORD_LNG, vo.getCtrCoordLng())
			.addValue(CountryVo.COLUMN_CTR_DATA_DATE_MAX, vo.getCtrDataDateMax())
			.addValue(CountryVo.COLUMN_CTR_DATA_DATE_MIN, vo.getCtrDataDateMin())
			.addValue(CountryVo.COLUMN_CTR_COORD_LAT, vo.getCtrCoordLat())
			.addValue(CountryVo.COLUMN_CTR_ID, vo.getCtrId())
			.addValue(CountryVo.COLUMN_CTR_CODE_PHONE, vo.getCtrCodePhone())
			.addValue(CountryVo.COLUMN_CTR_NAME, vo.getCtrName())
			.addValue(CountryVo.COLUMN_CTR_NAME_SHOW, vo.getCtrNameShow())
			.addValue(CountryVo.COLUMN_CTR_CODE_2, vo.getCtrCode2())
			.addValue(CountryVo.COLUMN_CTR_CODE_3, vo.getCtrCode3());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CountryVo.COLUMN_CTR_COORD_LNG, vo.getCtrCoordLng())
			.addValue(CountryVo.COLUMN_CTR_DATA_DATE_MAX, vo.getCtrDataDateMax())
			.addValue(CountryVo.COLUMN_CTR_DATA_DATE_MIN, vo.getCtrDataDateMin())
			.addValue(CountryVo.COLUMN_CTR_COORD_LAT, vo.getCtrCoordLat())
			.addValue(CountryVo.COLUMN_CTR_CODE_PHONE, vo.getCtrCodePhone())
			.addValue(CountryVo.COLUMN_CTR_NAME, vo.getCtrName())
			.addValue(CountryVo.COLUMN_CTR_NAME_SHOW, vo.getCtrNameShow())
			.addValue(CountryVo.COLUMN_CTR_CODE_2, vo.getCtrCode2())
			.addValue(CountryVo.COLUMN_CTR_CODE_3, vo.getCtrCode3())
			.addValue(CountryVo.COLUMN_CTR_ID, vo.getCtrId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCtrId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer ctrId) {
		return new MapSqlParameterSource()
			.addValue(CountryVo.COLUMN_CTR_ID, ctrId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CountryRowWrapper.getInstance()); }
	public CountryVo findVo(Integer ctrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(ctrId), CountryRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setCtrId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCtrId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataCategoryRowWrapper;
import tech.renovus.solarec.vo.db.data.DataCategoryVo;

public abstract class BaseDataCategoryDao <T extends DataCategoryVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_category";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_category WHERE data_cat_id_auto = :data_cat_id_auto";
	protected String SQL_INSERT					= "INSERT INTO data_category (data_cat_name, data_cat_description) VALUES (:data_cat_name, :data_cat_description)";
	protected String SQL_UPDATE					= "UPDATE data_category SET data_cat_name = :data_cat_name, data_cat_description = :data_cat_description WHERE data_cat_id_auto = :data_cat_id_auto";
	protected String SQL_DELETE					= "DELETE FROM data_category WHERE data_cat_id_auto = :data_cat_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_cat_id_auto) DO UPDATE SET data_cat_name = EXCLUDED.data_cat_name, data_cat_description = EXCLUDED.data_cat_description";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"data_cat_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataCategoryDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_cat_id_auto", vo.getDataCatId())
			.addValue("data_cat_name", vo.getDataCatName())
			.addValue("data_cat_description", vo.getDataCatDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_cat_name", vo.getDataCatName())
			.addValue("data_cat_description", vo.getDataCatDescription())
			.addValue("data_cat_id_auto", vo.getDataCatId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataCatId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataCatId) {
		return new MapSqlParameterSource()
			.addValue("data_cat_id_auto", dataCatId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataCategoryRowWrapper.getInstance()); }
	public DataCategoryVo findVo(Integer dataCatId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataCatId), DataCategoryRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setDataCatId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataCatId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

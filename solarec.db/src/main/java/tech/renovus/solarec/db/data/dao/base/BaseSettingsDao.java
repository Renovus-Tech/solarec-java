package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.SettingsRowWrapper;
import tech.renovus.solarec.vo.db.data.SettingsVo;

public abstract class BaseSettingsDao <T extends SettingsVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM settings";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM settings WHERE set_name = :set_name";
	protected String SQL_INSERT					= "INSERT INTO settings (set_name, set_cat_name, set_type, set_unit, set_value_default, set_value_min, set_value_max, set_flags) VALUES (:set_name, :set_cat_name, :set_type, :set_unit, :set_value_default, :set_value_min, :set_value_max, :set_flags)";
	protected String SQL_UPDATE					= "UPDATE settings SET set_cat_name = :set_cat_name, set_type = :set_type, set_unit = :set_unit, set_value_default = :set_value_default, set_value_min = :set_value_min, set_value_max = :set_value_max, set_flags = :set_flags WHERE set_name = :set_name";
	protected String SQL_DELETE					= "DELETE FROM settings WHERE set_name = :set_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (set_name) DO UPDATE SET set_cat_name = EXCLUDED.set_cat_name, set_type = EXCLUDED.set_type, set_unit = EXCLUDED.set_unit, set_value_default = EXCLUDED.set_value_default, set_value_min = EXCLUDED.set_value_min, set_value_max = EXCLUDED.set_value_max, set_flags = EXCLUDED.set_flags";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseSettingsDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("set_name", vo.getSetName())
			.addValue("set_cat_name", vo.getSetCatName())
			.addValue("set_type", vo.getSetType())
			.addValue("set_unit", vo.getSetUnit())
			.addValue("set_value_default", vo.getSetValueDefault())
			.addValue("set_value_min", vo.getSetValueMin())
			.addValue("set_value_max", vo.getSetValueMax())
			.addValue("set_flags", vo.getSetFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("set_cat_name", vo.getSetCatName())
			.addValue("set_type", vo.getSetType())
			.addValue("set_unit", vo.getSetUnit())
			.addValue("set_value_default", vo.getSetValueDefault())
			.addValue("set_value_min", vo.getSetValueMin())
			.addValue("set_value_max", vo.getSetValueMax())
			.addValue("set_flags", vo.getSetFlags())
			.addValue("set_name", vo.getSetName());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getSetName());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(String setName) {
		return new MapSqlParameterSource()
			.addValue("set_name", setName);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, SettingsRowWrapper.getInstance()); }
	public SettingsVo findVo(String setName) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(setName), SettingsRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
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

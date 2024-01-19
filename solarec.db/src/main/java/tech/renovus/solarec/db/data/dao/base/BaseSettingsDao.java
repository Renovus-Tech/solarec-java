package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.vo.db.data.SettingsVo;
import tech.renovus.solarec.db.data.dao.wrapper.SettingsRowWrapper;

public abstract class BaseSettingsDao {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM settings";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM settings WHERE set_name = :set_name";
	protected String SQL_INSERT					= "INSERT INTO settings (set_name, set_cat_name, set_type, set_unit, set_value_defult, set_value_min, set_value_max, set_flags) VALUES (:set_name, :set_cat_name, :set_type, :set_unit, :set_value_defult, :set_value_min, :set_value_max, :set_flags)";
	protected String SQL_UPDATE					= "UPDATE settings SET set_cat_name = :set_cat_nameAND set_type = :set_typeAND set_unit = :set_unitAND set_value_defult = :set_value_defultAND set_value_min = :set_value_minAND set_value_max = :set_value_maxAND set_flags = :set_flags WHERE set_name = :set_name";
	protected String SQL_DELETE					= "DELETE FROM settings WHERE set_name = :set_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (set_name) SET set_cat_name = EXCLUDED.set_cat_name, set_type = EXCLUDED.set_type, set_unit = EXCLUDED.set_unit, set_value_defult = EXCLUDED.set_value_defult, set_value_min = EXCLUDED.set_value_min, set_value_max = EXCLUDED.set_value_max, set_flags = EXCLUDED.set_flags";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseSettingsDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(SettingsVo vo) {
		return new MapSqlParameterSource()
			.addValue("set_name", vo.getSetName())
			.addValue("set_cat_name", vo.getSetCatName())
			.addValue("set_type", vo.getSetType())
			.addValue("set_unit", vo.getSetUnit())
			.addValue("set_value_defult", vo.getSetValueDefult())
			.addValue("set_value_min", vo.getSetValueMin())
			.addValue("set_value_max", vo.getSetValueMax())
			.addValue("set_flags", vo.getSetFlags());
	}
	
	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(SettingsVo vo) {
		return new MapSqlParameterSource()
			.addValue("set_cat_name", vo.getSetCatName())
			.addValue("set_type", vo.getSetType())
			.addValue("set_unit", vo.getSetUnit())
			.addValue("set_value_defult", vo.getSetValueDefult())
			.addValue("set_value_min", vo.getSetValueMin())
			.addValue("set_value_max", vo.getSetValueMax())
			.addValue("set_flags", vo.getSetFlags())
			.addValue("set_name", vo.getSetName());
	}
	
	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(SettingsVo vo) {
		return this.createPkMapSqlParameterSource(vo.getSetName());
	}
	
	private MapSqlParameterSource createPkMapSqlParameterSource(String setName) {
		return new MapSqlParameterSource()
			.addValue("set_name", setName);
	}
	//--- Public methods ------------------------
	public Collection<SettingsVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, SettingsRowWrapper.getInstance()); }
	public SettingsVo findVo(String setName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(setName), SettingsRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(SettingsVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(SettingsVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(SettingsVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(SettingsVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case SettingsVo.SYNC_INSERT: this.insert(vo); break;
			case SettingsVo.SYNC_UPDATE: this.update(vo); break;
			case SettingsVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<SettingsVo> vos) {
		if (vos == null) return;
		for (SettingsVo vo : vos) this.synchronize(vo);
	}
}

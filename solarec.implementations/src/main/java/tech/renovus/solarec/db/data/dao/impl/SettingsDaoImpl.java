package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseSettingsDao;
import tech.renovus.solarec.db.data.dao.interfaces.SettingsDao;

@Repository
public class SettingsDaoImpl extends BaseSettingsDao implements SettingsDao {
	
	//--- Private properties --------------------
	private final String SQL_GET_ALL_NAMES_FOR_CLIENT			= "SELECT set_name FROM settings WHERE set_flags ilike '_1%'";
	private final String SQL_GET_ALL_NAMES_FOR_USER				= "SELECT set_name FROM settings WHERE set_flags ilike '1%'";
	
	//--- Constructors --------------------------
	@Autowired public SettingsDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	public Collection<String> getAllNamesForClient() {
		return this.jdbc.queryForList(SQL_GET_ALL_NAMES_FOR_CLIENT, Collections.emptyMap(), String.class);
	}

	public Collection<String> getAllNamesForUser() {
		return this.jdbc.queryForList(SQL_GET_ALL_NAMES_FOR_USER, Collections.emptyMap(), String.class);
	}
	
}
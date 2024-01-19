package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseSettingsDao;
import tech.renovus.solarec.db.data.dao.interfaces.SettingsDao;

@Repository
public class SettingsDaoImpl extends BaseSettingsDao implements SettingsDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public SettingsDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
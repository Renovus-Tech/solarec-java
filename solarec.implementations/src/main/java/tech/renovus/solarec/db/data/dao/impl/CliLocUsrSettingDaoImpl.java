package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliLocUsrSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocUsrSettingDao;

@Repository
public class CliLocUsrSettingDaoImpl extends BaseCliLocUsrSettingDao implements CliLocUsrSettingDao {
	//--- Private properties --------------------

	//--- Constructors --------------------------
	@Autowired public CliLocUsrSettingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------

}
package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefParameterDao;
import tech.renovus.solarec.db.data.dao.wrapper.DataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

@Repository
public class DataDefParameterDaoImpl extends BaseDataDefParameterDao implements DataDefParameterDao {
	
	//--- Private properties --------------------
	protected final String SQL_FIND_ALL_FOR_DATA_DEFINITION			= "SELECT * FROM data_def_parameter WHERE data_def_id = :data_def_id";

	//--- Constructors --------------------------
	@Autowired public DataDefParameterDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<DataDefParameterVo> findAllFor(Integer dataDefId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_DATA_DEFINITION, 
				new MapSqlParameterSource().addValue("data_def_id", dataDefId),
				DataDefParameterRowWrapper.getInstance()
			);
	}
}
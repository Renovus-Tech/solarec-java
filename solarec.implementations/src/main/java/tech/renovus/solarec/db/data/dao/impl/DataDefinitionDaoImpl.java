package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.wrapper.DataDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

@Repository
public class DataDefinitionDaoImpl extends BaseDataDefinitionDao implements DataDefinitionDao {
	
	//--- Private properties --------------------
	protected final String SQL_GET_ALL_INVETERS_DEFINITION		= "SELECT * FROM data_definition WHERE data_def_flags like '__1%'";

	//--- Constructors --------------------------
	@Autowired public DataDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<DataDefinitionVo> getAllInvertersDefinitions() {
		return this.jdbc.query(SQL_GET_ALL_INVETERS_DEFINITION, DataDefinitionRowWrapper.getInstance());
	}
}

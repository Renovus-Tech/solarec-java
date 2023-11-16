package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataDefStatDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefStatDefinitionDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.DataDefStatDefinitionWithStatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;

@Repository
public class DataDefStatDefinitionDaoImpl extends BaseDataDefStatDefinitionDao implements DataDefStatDefinitionDao {
	
	//--- Private properties --------------------
	private final static String SQL_FIND_ALL_FOR_DATA_DEFINITION = "SELECT * FROM data_def_stat_definition ddsd, stat_definition sd WHERE ddsd.data_def_id = :dataDefId AND ddsd.stat_def_id = sd.stat_def_id_auto ORDER BY ddsd.stat_def_call_order";
	
	//--- Constructors --------------------------
	@Autowired public DataDefStatDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<DataDefStatDefinitionVo> getAllForDataDefinition(Integer dataDefId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_DATA_DEFINITION, 
				new MapSqlParameterSource()
					.addValue("dataDefId", dataDefId),
					DataDefStatDefinitionWithStatDefinitionRowWrapper.getInstance()
			);
	}  

}

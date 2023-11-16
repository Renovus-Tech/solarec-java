package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDataDefAlertDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefAlertDefinitionDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.DataDefAlertDefinitionWithAlertDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefAlertDefinitionVo;

@Repository
public class DataDefAlertDefinitionDaoImpl extends BaseDataDefAlertDefinitionDao implements DataDefAlertDefinitionDao {
	
	//--- Private properties --------------------
	private final static String SQL_FIND_ALL_FOR_DATA_DEFINITION = "SELECT * FROM data_def_alert_definition ddad, alert_definition ad WHERE ddad.data_def_id = :dataDefId AND ddad.alert_def_id = ad.alert_def_id_auto ORDER BY ddad.alert_def_call_order";
		
	//--- Constructors --------------------------
	@Autowired public DataDefAlertDefinitionDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<DataDefAlertDefinitionVo> getAllForDataDefinition(Integer dataDefId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_DATA_DEFINITION, 
				new MapSqlParameterSource()
					.addValue("dataDefId", dataDefId),
					DataDefAlertDefinitionWithAlertDefinitionRowWrapper.getInstance()
			);
	}  

}

package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.CliDataDefTriggerRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.ClientRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.DataDefinitionRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.GeneratorRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.LocationRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.StationRowWrapper;
import tech.renovus.solarec.db.data.vo.CliDataDefTriggerVo;

public class CliDataDefTriggerFullRowWrapper implements RowMapper<CliDataDefTriggerVo> {
	
	//--- Constructors --------------------------
	private CliDataDefTriggerFullRowWrapper() {}
	private static CliDataDefTriggerFullRowWrapper instance = new CliDataDefTriggerFullRowWrapper();
	public static CliDataDefTriggerFullRowWrapper getInstance() { return CliDataDefTriggerFullRowWrapper.instance; }
	
	//--- Implemented methods -------------------
	@Override public CliDataDefTriggerVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliDataDefTriggerVo vo = CliDataDefTriggerRowWrapper.getInstance().mapRow(resultSet, arg1);
		if (vo == null) return vo;
		
		if (vo.getDataDefId() != null) vo.setDataDefinitionVo(DataDefinitionRowWrapper.getInstance().mapRow(resultSet, arg1));
		if (vo.getCliId() != null) vo.setClientVo(ClientRowWrapper.getInstance().mapRow(resultSet, arg1));
		if (vo.getLocId() != null) vo.setLocationVo(LocationRowWrapper.getInstance().mapRow(resultSet, arg1));
		if (vo.getGenId() != null) vo.setGeneratorVo(GeneratorRowWrapper.getInstance().mapRow(resultSet, arg1));
		if (vo.getStaId() != null) vo.setStationVo(StationRowWrapper.getInstance().mapRow(resultSet, arg1));
		
		return vo;
	}
}

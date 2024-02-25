package tech.renovus.solarec.db.data.dao.wrapper.custom;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.renovus.solarec.db.data.dao.wrapper.CliDataDefParameterRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.DataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;

public class CliDataDefParameterWithParametersRowWrapper extends CliDataDefParameterRowWrapper {

	//--- Constructors --------------------------
	private CliDataDefParameterWithParametersRowWrapper() {}
	private static CliDataDefParameterWithParametersRowWrapper instance = new CliDataDefParameterWithParametersRowWrapper();
	public static CliDataDefParameterWithParametersRowWrapper getInstance() { return CliDataDefParameterWithParametersRowWrapper.instance; }

	//--- Protected methods --------------------

	//--- Overridden methods --------------------
	@Override public CliDataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliDataDefParameterVo vo = super.mapRow(resultSet, arg1);
		vo.setDataDefParameter(DataDefParameterRowWrapper.getInstance().mapRow(resultSet, arg1));

		if (vo.getDataDefParId() == null) vo.setDataDefParId(vo.getDataDefParameter().getDataDefParId());
		
		return vo;
	}
}
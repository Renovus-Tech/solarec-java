package tech.renovus.solarec.db.data.dao.wrapper.custom;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefParameterRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.GenDataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;

public class GenDataDefParameterWithParametersRowWrapper extends GenDataDefParameterRowWrapper {

	//--- Constructors --------------------------
	private GenDataDefParameterWithParametersRowWrapper() {}
	private static GenDataDefParameterWithParametersRowWrapper instance = new GenDataDefParameterWithParametersRowWrapper();
	public static GenDataDefParameterWithParametersRowWrapper getInstance() { return GenDataDefParameterWithParametersRowWrapper.instance; }

	//--- Protected methods --------------------

	//--- Overridden methods --------------------
	@Override public GenDataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenDataDefParameterVo vo = super.mapRow(resultSet, arg1);
		vo.setDataDefParameter(DataDefParameterRowWrapper.getInstance().mapRow(resultSet, arg1));

		return vo;
	}
}
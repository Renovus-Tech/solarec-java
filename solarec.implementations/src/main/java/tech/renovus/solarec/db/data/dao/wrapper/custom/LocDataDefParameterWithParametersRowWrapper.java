package tech.renovus.solarec.db.data.dao.wrapper.custom;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefParameterRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.LocDataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;

public class LocDataDefParameterWithParametersRowWrapper extends LocDataDefParameterRowWrapper {

	//--- Constructors --------------------------
	private LocDataDefParameterWithParametersRowWrapper() {}
	private static LocDataDefParameterWithParametersRowWrapper instance = new LocDataDefParameterWithParametersRowWrapper();
	public static LocDataDefParameterWithParametersRowWrapper getInstance() { return LocDataDefParameterWithParametersRowWrapper.instance; }

	//--- Protected methods --------------------

	//--- Overridden methods --------------------
	@Override public LocDataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocDataDefParameterVo vo = super.mapRow(resultSet, arg1);
		vo.setDataDefParameter(DataDefParameterRowWrapper.getInstance().mapRow(resultSet, arg1));

		return vo;
	}
}
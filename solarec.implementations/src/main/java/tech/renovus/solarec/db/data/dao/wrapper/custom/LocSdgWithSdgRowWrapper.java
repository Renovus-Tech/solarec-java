package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import tech.renovus.solarec.db.data.dao.wrapper.LocSdgRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.SdgRowWrapper;
import tech.renovus.solarec.vo.db.data.LocSdgVo;

public class LocSdgWithSdgRowWrapper extends LocSdgRowWrapper {

	//--- Constructors --------------------------
	protected LocSdgWithSdgRowWrapper() {}
	private static LocSdgWithSdgRowWrapper instance = new LocSdgWithSdgRowWrapper();
	public static LocSdgWithSdgRowWrapper getInstance() { return LocSdgWithSdgRowWrapper.instance; }

	//--- Overridden methods --------------------
	@Override public LocSdgVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocSdgVo vo = super.mapRow(resultSet, arg1);
		vo.setSdgVo(SdgRowWrapper.getInstance().mapRow(resultSet, arg1));
		return vo;
	}
}

package tech.renovus.solarec.db.data.dao.wrapper.custom;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.renovus.solarec.db.data.dao.wrapper.CliLocAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

public class CliLocAlertWithOtherRowWrapper extends CliLocAlertRowWrapper {

	//--- Constructors --------------------------
	private CliLocAlertWithOtherRowWrapper() {}
	private static CliLocAlertRowWrapper instance = new CliLocAlertWithOtherRowWrapper();
	public static CliLocAlertRowWrapper getInstance() { return CliLocAlertWithOtherRowWrapper.instance; }

	//--- Overridden methods --------------------
	@Override public CliLocAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliLocAlertVo vo = super.mapRow(resultSet, arg1);

		if (vo != null) {
			vo.setCliName(resultSet.getString("cli_name"));
			vo.setLocName(resultSet.getString("loc_name"));
			vo.setLocCode(resultSet.getString("loc_code"));
		}
		
		return vo;
	}
}
package tech.renovus.solarec.db.data.dao.wrapper.custom;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.renovus.solarec.db.data.dao.wrapper.CliGenAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;

public class CliGeAlertWithOtherRowWrapper extends CliGenAlertRowWrapper {

	//--- Constructors --------------------------
	private CliGeAlertWithOtherRowWrapper() {}
	private static CliGeAlertWithOtherRowWrapper instance = new CliGeAlertWithOtherRowWrapper();
	public static CliGeAlertWithOtherRowWrapper getInstance() { return CliGeAlertWithOtherRowWrapper.instance; }

	//--- Overridden methods --------------------
	@Override public CliGenAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliGenAlertVo vo = super.mapRow(resultSet, arg1);

		vo.setCliName(resultSet.getString("cli_name"));
		vo.setLocName(resultSet.getString("loc_name"));
		vo.setLocCode(resultSet.getString("loc_code"));
		vo.setGenName(resultSet.getString("gen_name"));
		vo.setGenCode(resultSet.getString("gen_code"));
		
		return vo;
	}
}
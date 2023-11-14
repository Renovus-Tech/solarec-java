package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.DocStationRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.StationRowWrapper;
import tech.renovus.solarec.db.data.vo.DocStationVo;

public class FullDocStationRowWrapper implements RowMapper<DocStationVo> {

	//--- Constructors --------------------------
	private FullDocStationRowWrapper() {}
	private static FullDocStationRowWrapper instance = new FullDocStationRowWrapper();
	public static FullDocStationRowWrapper getInstance() { return FullDocStationRowWrapper.instance; }

	//--- Overridden methods --------------------
	@Override public DocStationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocStationVo vo = DocStationRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setStationVo(StationRowWrapper.getInstance().mapRow(resultSet, arg1));

		return vo;
	}
}


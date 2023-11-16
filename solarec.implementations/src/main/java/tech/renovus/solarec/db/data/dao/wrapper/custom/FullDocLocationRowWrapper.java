package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.DocLocationRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.LocationRowWrapper;
import tech.renovus.solarec.vo.db.data.DocLocationVo;

public class FullDocLocationRowWrapper implements RowMapper<DocLocationVo> {

	//--- Constructors --------------------------
	private FullDocLocationRowWrapper() {}
	private static FullDocLocationRowWrapper instance = new FullDocLocationRowWrapper();
	public static FullDocLocationRowWrapper getInstance() { return FullDocLocationRowWrapper.instance; }

	//--- Overridden methods --------------------
	@Override public DocLocationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocLocationVo vo = DocLocationRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setLocationVo(LocationRowWrapper.getInstance().mapRow(resultSet, arg1));

		return vo;
	}
}


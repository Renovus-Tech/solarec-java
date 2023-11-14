package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.DocGeneratorRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.GeneratorRowWrapper;
import tech.renovus.solarec.db.data.vo.DocGeneratorVo;

public class FullDocGeneratorRowWrapper implements RowMapper<DocGeneratorVo> {

	//--- Constructors --------------------------
	private FullDocGeneratorRowWrapper() {}
	private static FullDocGeneratorRowWrapper instance = new FullDocGeneratorRowWrapper();
	public static FullDocGeneratorRowWrapper getInstance() { return FullDocGeneratorRowWrapper.instance; }

	//--- Overridden methods --------------------
	@Override public DocGeneratorVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocGeneratorVo vo = DocGeneratorRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setGeneratorVo(GeneratorRowWrapper.getInstance().mapRow(resultSet, arg1));

		return vo;
	}
}


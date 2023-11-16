package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.GenNeighbourVo;

public class GenNeighbourRowWrapper implements RowMapper<GenNeighbourVo> {

	//--- Constructors --------------------------
	private GenNeighbourRowWrapper() {}
	private static GenNeighbourRowWrapper instance = new GenNeighbourRowWrapper();
	public static GenNeighbourRowWrapper getInstance() { return GenNeighbourRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenIdNeighbour(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenIdNeighbourPosition(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GenNeighbourVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenNeighbourVo vo = new GenNeighbourVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenNeighbourVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenNeighbourVo.COLUMN_GEN_ID));
		vo.setGenIdNeighbour(this.getResultSetGenIdNeighbour(resultSet, GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR));
		vo.setGenIdNeighbourPosition(this.getResultSetGenIdNeighbourPosition(resultSet, GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR_POSITION));

		return vo;
	}
}


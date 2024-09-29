package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenNeighbourDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenNeighbourDao;
import tech.renovus.solarec.db.data.dao.wrapper.GenNeighbourRowWrapper;
import tech.renovus.solarec.vo.db.data.GenNeighbourVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;

@Repository
public class GenNeighbourDaoImpl extends BaseGenNeighbourDao implements GenNeighbourDao {
	
	//--- Private constants ---------------------
	private final static String SQL_SELECT_ALL_FOR_LOCATION			= "SELECT * FROM generator g, gen_neighbour gn WHERE g.cli_id = :cli_id and g.loc_id = :loc_id AND g.cli_id = gn.cli_id AND g.gen_id_auto = gn.gen_id ORDER BY gen_id_neighbour_position";
	private final static String SQL_DELETE_ALL_FOR_CLIENT			= "SELECT * FROM gen_neighbour gn WHERE gn.cli_id = :cli_id ORDER BY gen_id_neighbour_position";
	private final static String SQL_DELETE_ALL_FOR_LOCATION			= "DELETE FROM gen_neighbour WHERE cli_id = :cli_id AND gen_id = :gen_id";
	
	//--- Constructors --------------------------
	@Autowired public GenNeighbourDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<GenNeighbourVo> getAllFor(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_LOCATION, 
				new MapSqlParameterSource()
					.addValue(GenNeighbourVo.COLUMN_CLI_ID, cliId)
					.addValue(GeneratorVo.COLUMN_LOC_ID, locId),
				GenNeighbourRowWrapper.getInstance()
			);
	}
	
	@Override public void deleteAllFor(Integer cliId, Integer genId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_LOCATION, new MapSqlParameterSource().addValue(GenNeighbourVo.COLUMN_CLI_ID, cliId).addValue(GenNeighbourVo.COLUMN_GEN_ID, genId));
	}

	@Override public Collection<GenNeighbourVo> getAllFor(Integer cliId) {
		return this.jdbc.query(
				SQL_DELETE_ALL_FOR_CLIENT, 
				new MapSqlParameterSource()
					.addValue(GenNeighbourVo.COLUMN_CLI_ID, cliId),
				GenNeighbourRowWrapper.getInstance()
			);
	}
}

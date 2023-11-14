package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenNeighbourRowWrapper;
import tech.renovus.solarec.db.data.vo.GenNeighbourVo;

public abstract class BaseGenNeighbourDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_neighbour";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_neighbour WHERE cli_id = :cli_id AND gen_id = :gen_id AND gen_id_neighbour = :gen_id_neighbour";
	protected String SQL_INSERT					= "INSERT INTO gen_neighbour (cli_id,gen_id,gen_id_neighbour,gen_id_neighbour_position) VALUES (:cli_id,:gen_id,:gen_id_neighbour,:gen_id_neighbour_position)";
	protected String SQL_UPDATE					= "UPDATE gen_neighbour SET gen_id_neighbour_position = :gen_id_neighbour_position WHERE cli_id = :cli_id AND gen_id = :gen_id AND gen_id_neighbour = :gen_id_neighbour";
	protected String SQL_DELETE					= "DELETE FROM gen_neighbour WHERE cli_id = :cli_id AND gen_id = :gen_id AND gen_id_neighbour = :gen_id_neighbour";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, gen_id_neighbour) DO UPDATE SET gen_id_neighbour_position = EXCLUDED.gen_id_neighbour_position";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenNeighbourDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(GenNeighbourVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("gen_id_neighbour", vo.getGenIdNeighbour())
			.addValue("gen_id_neighbour_position", vo.getGenIdNeighbourPosition());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GenNeighbourVo vo) {
		return new MapSqlParameterSource()
			.addValue("gen_id_neighbour_position", vo.getGenIdNeighbourPosition())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("gen_id_neighbour", vo.getGenIdNeighbour());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GenNeighbourVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getGenIdNeighbour());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Integer genIdNeighbour) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("gen_id_neighbour", genIdNeighbour);
	}

	//--- Public methods ------------------------
	public Collection<GenNeighbourVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GenNeighbourRowWrapper.getInstance()); }
	public GenNeighbourVo findVo(Integer cliId, Integer genId, Integer genIdNeighbour) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, genIdNeighbour), GenNeighbourRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GenNeighbourVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(GenNeighbourVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GenNeighbourVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GenNeighbourVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GenNeighbourVo.SYNC_INSERT: this.insert(vo); break;
			case GenNeighbourVo.SYNC_UPDATE: this.update(vo); break;
			case GenNeighbourVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GenNeighbourVo> vos) {
		if (vos == null) return;
		for (GenNeighbourVo vo : vos) this.synchronize(vo);
}


}


package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenNeighbourRowWrapper;
import tech.renovus.solarec.vo.db.data.GenNeighbourVo;

public abstract class BaseGenNeighbourDao <T extends GenNeighbourVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_neighbour";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_neighbour WHERE cli_id = :cli_id AND gen_id = :gen_id AND gen_id_neighbour = :gen_id_neighbour";
	protected String SQL_INSERT					= "INSERT INTO gen_neighbour (cli_id, gen_id, gen_id_neighbour, gen_id_neighbour_position) VALUES (:cli_id, :gen_id, :gen_id_neighbour, :gen_id_neighbour_position)";
	protected String SQL_UPDATE					= "UPDATE gen_neighbour SET gen_id_neighbour_position = :gen_id_neighbour_position WHERE cli_id = :cli_id AND gen_id = :gen_id AND gen_id_neighbour = :gen_id_neighbour";
	protected String SQL_DELETE					= "DELETE FROM gen_neighbour WHERE cli_id = :cli_id AND gen_id = :gen_id AND gen_id_neighbour = :gen_id_neighbour";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, gen_id_neighbour) DO UPDATE SET gen_id_neighbour_position = EXCLUDED.gen_id_neighbour_position";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenNeighbourDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenNeighbourVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenNeighbourVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR, vo.getGenIdNeighbour())
			.addValue(GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR_POSITION, vo.getGenIdNeighbourPosition());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR_POSITION, vo.getGenIdNeighbourPosition())
			.addValue(GenNeighbourVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenNeighbourVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR, vo.getGenIdNeighbour());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getGenIdNeighbour());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Integer genIdNeighbour) {
		return new MapSqlParameterSource()
			.addValue(GenNeighbourVo.COLUMN_CLI_ID, cliId)
			.addValue(GenNeighbourVo.COLUMN_GEN_ID, genId)
			.addValue(GenNeighbourVo.COLUMN_GEN_ID_NEIGHBOUR, genIdNeighbour);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenNeighbourRowWrapper.getInstance()); }
	public GenNeighbourVo findVo(Integer cliId, Integer genId, Integer genIdNeighbour) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, genIdNeighbour), GenNeighbourRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getGenIdNeighbour()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}

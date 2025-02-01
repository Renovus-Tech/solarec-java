package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.FrequencyRowWrapper;
import tech.renovus.solarec.vo.db.data.FrequencyVo;

public abstract class BaseFrequencyDao <T extends FrequencyVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM frequency";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM frequency WHERE frq_id_auto = :frq_id_auto";
	protected String SQL_INSERT					= "INSERT INTO frequency (frq_amount, frq_name, frq_unit, frq_flags) VALUES (:frq_amount, :frq_name, :frq_unit, :frq_flags)";
	protected String SQL_UPDATE					= "UPDATE frequency SET frq_amount = :frq_amount, frq_name = :frq_name, frq_unit = :frq_unit, frq_flags = :frq_flags WHERE frq_id_auto = :frq_id_auto";
	protected String SQL_DELETE					= "DELETE FROM frequency WHERE frq_id_auto = :frq_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (frq_id_auto) DO UPDATE SET frq_amount = EXCLUDED.frq_amount, frq_name = EXCLUDED.frq_name, frq_unit = EXCLUDED.frq_unit, frq_flags = EXCLUDED.frq_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"frq_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseFrequencyDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(FrequencyVo.COLUMN_FRQ_ID, vo.getFrqId())
			.addValue(FrequencyVo.COLUMN_FRQ_AMOUNT, vo.getFrqAmount())
			.addValue(FrequencyVo.COLUMN_FRQ_NAME, vo.getFrqName())
			.addValue(FrequencyVo.COLUMN_FRQ_UNIT, vo.getFrqUnit())
			.addValue(FrequencyVo.COLUMN_FRQ_FLAGS, vo.getFrqFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(FrequencyVo.COLUMN_FRQ_AMOUNT, vo.getFrqAmount())
			.addValue(FrequencyVo.COLUMN_FRQ_NAME, vo.getFrqName())
			.addValue(FrequencyVo.COLUMN_FRQ_UNIT, vo.getFrqUnit())
			.addValue(FrequencyVo.COLUMN_FRQ_FLAGS, vo.getFrqFlags())
			.addValue(FrequencyVo.COLUMN_FRQ_ID, vo.getFrqId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getFrqId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer frqId) {
		return new MapSqlParameterSource()
			.addValue(FrequencyVo.COLUMN_FRQ_ID, frqId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, FrequencyRowWrapper.getInstance()); }
	public FrequencyVo findVo(Integer frqId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(frqId), FrequencyRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setFrqId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getFrqId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
			default: 
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}

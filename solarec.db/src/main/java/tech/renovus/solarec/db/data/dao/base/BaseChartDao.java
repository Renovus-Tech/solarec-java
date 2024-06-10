package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ChartRowWrapper;
import tech.renovus.solarec.vo.db.data.ChartVo;

public abstract class BaseChartDao <T extends ChartVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM chart";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM chart WHERE chr_id_auto = :chr_id_auto";
	protected String SQL_INSERT					= "INSERT INTO chart (chr_name, chr_title, chr_description, chr_flags, chr_url) VALUES (:chr_name, :chr_title, :chr_description, :chr_flags, :chr_url)";
	protected String SQL_UPDATE					= "UPDATE chart SET chr_name = :chr_name, chr_title = :chr_title, chr_description = :chr_description, chr_flags = :chr_flags, chr_url = :chr_url WHERE chr_id_auto = :chr_id_auto";
	protected String SQL_DELETE					= "DELETE FROM chart WHERE chr_id_auto = :chr_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (chr_id_auto) DO UPDATE SET chr_name = EXCLUDED.chr_name, chr_title = EXCLUDED.chr_title, chr_description = EXCLUDED.chr_description, chr_flags = EXCLUDED.chr_flags, chr_url = EXCLUDED.chr_url";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"chr_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseChartDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(ChartVo.COLUMN_CHR_ID, vo.getChrId())
			.addValue(ChartVo.COLUMN_CHR_NAME, vo.getChrName())
			.addValue(ChartVo.COLUMN_CHR_TITLE, vo.getChrTitle())
			.addValue(ChartVo.COLUMN_CHR_DESCRIPTION, vo.getChrDescription())
			.addValue(ChartVo.COLUMN_CHR_FLAGS, vo.getChrFlags())
			.addValue(ChartVo.COLUMN_CHR_URL, vo.getChrUrl());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(ChartVo.COLUMN_CHR_NAME, vo.getChrName())
			.addValue(ChartVo.COLUMN_CHR_TITLE, vo.getChrTitle())
			.addValue(ChartVo.COLUMN_CHR_DESCRIPTION, vo.getChrDescription())
			.addValue(ChartVo.COLUMN_CHR_FLAGS, vo.getChrFlags())
			.addValue(ChartVo.COLUMN_CHR_URL, vo.getChrUrl())
			.addValue(ChartVo.COLUMN_CHR_ID, vo.getChrId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getChrId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer chrId) {
		return new MapSqlParameterSource()
			.addValue(ChartVo.COLUMN_CHR_ID, chrId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, ChartRowWrapper.getInstance()); }
	public ChartVo findVo(Integer chrId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(chrId), ChartRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setChrId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getChrId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

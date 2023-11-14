package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ChartRowWrapper;
import tech.renovus.solarec.db.data.vo.ChartVo;

public abstract class BaseChartDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM chart";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM chart WHERE chr_id_auto = :chr_id_auto";
	protected String SQL_INSERT					= "INSERT INTO chart (chr_name,chr_title,chr_description,chr_flags) VALUES (:chr_name,:chr_title,:chr_description,:chr_flags)";
	protected String SQL_UPDATE					= "UPDATE chart SET chr_name = :chr_name,chr_title = :chr_title,chr_description = :chr_description,chr_flags = :chr_flags WHERE chr_id_auto = :chr_id_auto";
	protected String SQL_DELETE					= "DELETE FROM chart WHERE chr_id_auto = :chr_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (chr_id_auto) DO UPDATE SET chr_name = EXCLUDED.chr_name, chr_title = EXCLUDED.chr_title, chr_description = EXCLUDED.chr_description, chr_flags = EXCLUDED.chr_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"chr_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseChartDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(ChartVo vo) {
		return new MapSqlParameterSource()
			.addValue("chr_name", vo.getChrName())
			.addValue("chr_title", vo.getChrTitle())
			.addValue("chr_description", vo.getChrDescription())
			.addValue("chr_flags", vo.getChrFlags());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(ChartVo vo) {
		return new MapSqlParameterSource()
			.addValue("chr_name", vo.getChrName())
			.addValue("chr_title", vo.getChrTitle())
			.addValue("chr_description", vo.getChrDescription())
			.addValue("chr_flags", vo.getChrFlags())
			.addValue("chr_id_auto", vo.getChrId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(ChartVo vo) {
		return this.createPkMapSqlParameterSource(vo.getChrId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer chrId) {
		return new MapSqlParameterSource()
			.addValue("chr_id_auto", chrId);
	}

	//--- Public methods ------------------------
	public Collection<ChartVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, ChartRowWrapper.getInstance()); }
	public ChartVo findVo(Integer chrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(chrId), ChartRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(ChartVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setChrId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(ChartVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(ChartVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(ChartVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case ChartVo.SYNC_INSERT: this.insert(vo); break;
			case ChartVo.SYNC_UPDATE: this.update(vo); break;
			case ChartVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<ChartVo> vos) {
		if (vos == null) return;
		for (ChartVo vo : vos) this.synchronize(vo);
}


}


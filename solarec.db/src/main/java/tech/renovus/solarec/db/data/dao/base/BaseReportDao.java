package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ReportRowWrapper;
import tech.renovus.solarec.vo.db.data.ReportVo;

public abstract class BaseReportDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM report";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM report WHERE cli_id = :cli_id AND rep_id_auto = :rep_id_auto";
	protected String SQL_INSERT					= "INSERT INTO report (cli_id,rep_date_generated,rep_date_from,rep_date_to,rep_title,rep_file,rep_flags,rep_type_id,reg_file) VALUES (:cli_id,:rep_date_generated,:rep_date_from,:rep_date_to,:rep_title,:rep_file,:rep_flags,:rep_type_id,:reg_file)";
	protected String SQL_UPDATE					= "UPDATE report SET rep_date_generated = :rep_date_generated,rep_date_from = :rep_date_from,rep_date_to = :rep_date_to,rep_title = :rep_title,rep_file = :rep_file,rep_flags = :rep_flags,rep_type_id = :rep_type_id,reg_file = :reg_file WHERE cli_id = :cli_id AND rep_id_auto = :rep_id_auto";
	protected String SQL_DELETE					= "DELETE FROM report WHERE cli_id = :cli_id AND rep_id_auto = :rep_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, rep_id_auto) DO UPDATE SET rep_date_generated = EXCLUDED.rep_date_generated, rep_date_from = EXCLUDED.rep_date_from, rep_date_to = EXCLUDED.rep_date_to, rep_title = EXCLUDED.rep_title, rep_file = EXCLUDED.rep_file, rep_flags = EXCLUDED.rep_flags, rep_type_id = EXCLUDED.rep_type_id, reg_file = EXCLUDED.reg_file";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"rep_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseReportDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(ReportVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("rep_date_generated", vo.getRepDateGenerated())
			.addValue("rep_date_from", vo.getRepDateFrom())
			.addValue("rep_date_to", vo.getRepDateTo())
			.addValue("rep_title", vo.getRepTitle())
			.addValue("rep_file", vo.getRepFile())
			.addValue("rep_flags", vo.getRepFlags())
			.addValue("rep_type_id", vo.getRepTypeId())
			.addValue("reg_file", vo.getRegFile());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(ReportVo vo) {
		return new MapSqlParameterSource()
			.addValue("rep_date_generated", vo.getRepDateGenerated())
			.addValue("rep_date_from", vo.getRepDateFrom())
			.addValue("rep_date_to", vo.getRepDateTo())
			.addValue("rep_title", vo.getRepTitle())
			.addValue("rep_file", vo.getRepFile())
			.addValue("rep_flags", vo.getRepFlags())
			.addValue("rep_type_id", vo.getRepTypeId())
			.addValue("reg_file", vo.getRegFile())
			.addValue("cli_id", vo.getCliId())
			.addValue("rep_id_auto", vo.getRepId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(ReportVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getRepId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer repId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("rep_id_auto", repId);
	}

	//--- Public methods ------------------------
	public Collection<ReportVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, ReportRowWrapper.getInstance()); }
	public ReportVo findVo(Integer cliId, Integer repId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, repId), ReportRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(ReportVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setRepId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(ReportVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(ReportVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(ReportVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case ReportVo.SYNC_INSERT: this.insert(vo); break;
			case ReportVo.SYNC_UPDATE: this.update(vo); break;
			case ReportVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<ReportVo> vos) {
		if (vos == null) return;
		for (ReportVo vo : vos) this.synchronize(vo);
}


}


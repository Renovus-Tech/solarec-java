package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ReportRowWrapper;
import tech.renovus.solarec.vo.db.data.ReportVo;

public abstract class BaseReportDao <T extends ReportVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM report";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM report WHERE cli_id = :cli_id AND rep_id_auto = :rep_id_auto";
	protected String SQL_INSERT					= "INSERT INTO report (rep_date_generated, rep_date_from, rep_type_id, cli_id, rep_date_to, reg_file, rep_title, rep_file, rep_flags) VALUES (:rep_date_generated, :rep_date_from, :rep_type_id, :cli_id, :rep_date_to, :reg_file, :rep_title, :rep_file, :rep_flags)";
	protected String SQL_UPDATE					= "UPDATE report SET rep_date_generated = :rep_date_generated, rep_date_from = :rep_date_from, rep_type_id = :rep_type_id, rep_date_to = :rep_date_to, reg_file = :reg_file, rep_title = :rep_title, rep_file = :rep_file, rep_flags = :rep_flags WHERE cli_id = :cli_id AND rep_id_auto = :rep_id_auto";
	protected String SQL_DELETE					= "DELETE FROM report WHERE cli_id = :cli_id AND rep_id_auto = :rep_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, rep_id_auto) DO UPDATE SET rep_date_generated = EXCLUDED.rep_date_generated, rep_date_from = EXCLUDED.rep_date_from, rep_type_id = EXCLUDED.rep_type_id, rep_date_to = EXCLUDED.rep_date_to, reg_file = EXCLUDED.reg_file, rep_title = EXCLUDED.rep_title, rep_file = EXCLUDED.rep_file, rep_flags = EXCLUDED.rep_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"rep_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseReportDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(ReportVo.COLUMN_REP_DATE_GENERATED, vo.getRepDateGenerated())
			.addValue(ReportVo.COLUMN_REP_DATE_FROM, vo.getRepDateFrom())
			.addValue(ReportVo.COLUMN_REP_TYPE_ID, vo.getRepTypeId())
			.addValue(ReportVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(ReportVo.COLUMN_REP_DATE_TO, vo.getRepDateTo())
			.addValue(ReportVo.COLUMN_REP_ID, vo.getRepId())
			.addValue(ReportVo.COLUMN_REG_FILE, vo.getRegFile())
			.addValue(ReportVo.COLUMN_REP_TITLE, vo.getRepTitle())
			.addValue(ReportVo.COLUMN_REP_FILE, vo.getRepFile())
			.addValue(ReportVo.COLUMN_REP_FLAGS, vo.getRepFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(ReportVo.COLUMN_REP_DATE_GENERATED, vo.getRepDateGenerated())
			.addValue(ReportVo.COLUMN_REP_DATE_FROM, vo.getRepDateFrom())
			.addValue(ReportVo.COLUMN_REP_TYPE_ID, vo.getRepTypeId())
			.addValue(ReportVo.COLUMN_REP_DATE_TO, vo.getRepDateTo())
			.addValue(ReportVo.COLUMN_REG_FILE, vo.getRegFile())
			.addValue(ReportVo.COLUMN_REP_TITLE, vo.getRepTitle())
			.addValue(ReportVo.COLUMN_REP_FILE, vo.getRepFile())
			.addValue(ReportVo.COLUMN_REP_FLAGS, vo.getRepFlags())
			.addValue(ReportVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(ReportVo.COLUMN_REP_ID, vo.getRepId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getRepId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer repId) {
		return new MapSqlParameterSource()
			.addValue(ReportVo.COLUMN_CLI_ID, cliId)
			.addValue(ReportVo.COLUMN_REP_ID, repId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, ReportRowWrapper.getInstance()); }
	public ReportVo findVo(Integer cliId, Integer repId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, repId), ReportRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setRepId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getRepId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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

package tech.renovus.solarec.db.data.dao.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDocumentDao;
import tech.renovus.solarec.db.data.dao.interfaces.DocumentDao;
import tech.renovus.solarec.db.data.dao.wrapper.DocumentRowWrapper;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.DocGeneratorVo;
import tech.renovus.solarec.vo.db.data.DocLocationVo;
import tech.renovus.solarec.vo.db.data.DocStationVo;
import tech.renovus.solarec.vo.db.data.DocumentVo;

@Repository
public class DocumentDaoImpl extends BaseDocumentDao implements DocumentDao {
	
	//--- Private properties --------------------
	private final static String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM view_documents_sorted d WHERE cli_id = :cli_id ";
	private final static String SQL_SELECT_ALL_FOR_LOCATION		= "SELECT d.* FROM view_documents_sorted d, doc_location dl WHERE d.cli_id = dl.cli_id AND d.doc_id_auto = dl.doc_id AND dl.cli_id = :cli_id AND dl.loc_id = :loc_id ";
	private final static String SQL_SELECT_ALL_FOR_GENERATOR	= "SELECT d.* FROM view_documents_sorted d, doc_generator dg WHERE d.cli_id = dg.cli_id AND d.doc_id_auto = dg.doc_id AND dg.cli_id = :cli_id AND dg.gen_id = :gen_id ";
	private final static String SQL_SELECT_ALL_FOR_STATION		= "SELECT d.* FROM view_documents_sorted d, doc_station ds WHERE d.cli_id = ds.cli_id AND d.doc_id_auto = ds.doc_id AND ds.cli_id = :cli_id AND ds.sta_id = :sta_id ";
	
	//--- Constructors --------------------------
	@Autowired public DocumentDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Private methods ------------------------
	private void addDateFilters(String name, String term, Date from, Date to, Boolean isOpen, MapSqlParameterSource params, StringBuilder sql) {
		if (to != null) to = DateUtil.addUnit(to, Calendar.DAY_OF_YEAR, 1);
		
		if (from != null || to != null) {
			sql.append(" AND ((");
			if (from != null) {
				sql.append(" doc_date_from >= :from ");
				params.addValue("from", from);
			}
			if (from != null && to != null) sql.append(" AND ");
			if (to != null) {
				sql.append(" doc_date_to < :to ");
				params.addValue("to", to);
			}
			sql.append(") OR (");
			if (from != null) {
				sql.append(" (doc_date_from is null and doc_date_added >= :fromAdded) ");
				params.addValue("fromAdded", from);
			}
			if (from != null && to != null) sql.append(" AND ");
			if (to != null) {
				sql.append(" (doc_date_to is null and doc_date_added < :toAdded) ");
				params.addValue("toAdded", to);
			}
			sql.append("))");
		}
		
		if (StringUtil.notEmpty(name)) {
			sql.append(" and doc_name like :name");
			params.addValue("name", "%" + name + "%");
		}
		
		if (StringUtil.notEmpty(term)) {
			sql.append(" and lower(doc_file_content) like :term");
			params.addValue("term", "%" + term.toLowerCase() + "%");
		}
		
		if (isOpen != null) {
			sql.append(" and doc_flags like :flags");
			params.addValue("flags", FlagUtil.getSqlFlagValue(null, DocumentVo.FLAG_IS_OPEN, isOpen));
		}
		
		sql.append(" ORDER BY d.doc_date_sort DESC, d.doc_date_from DESC, d.doc_date_to DESC, d.doc_date_added DESC, d.doc_id_auto DESC");
	}
	
	//--- Overridden methods --------------------
	@Override public Collection<DocumentVo> findAll(Integer cliId, String name, String term, Date from, Date to, Boolean isOpen) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue(DocumentVo.COLUMN_CLI_ID, cliId);
		StringBuilder sql = new StringBuilder(SQL_SELECT_ALL_FOR_CLIENT);
		this.addDateFilters(name, term, from, to, isOpen, params, sql);
		return this.jdbc.query( sql.toString(), params, DocumentRowWrapper.getInstance() );
	}

	@Override public Collection<DocumentVo> findAllLocation(Integer cliId, Integer locId, String name, String term, Date from, Date to, Boolean isOpen) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue(DocumentVo.COLUMN_CLI_ID, cliId).addValue(DocLocationVo.COLUMN_LOC_ID, locId);
		StringBuilder sql = new StringBuilder(SQL_SELECT_ALL_FOR_LOCATION);
		this.addDateFilters(name, term, from, to, isOpen, params, sql);
		return this.jdbc.query( sql.toString(), params, DocumentRowWrapper.getInstance() );
	}

	@Override public Collection<DocumentVo> findAllGenerator(Integer cliId, Integer genId, String name, String term, Date from, Date to, Boolean isOpen) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue(DocumentVo.COLUMN_CLI_ID, cliId).addValue(DocGeneratorVo.COLUMN_GEN_ID, genId);
		StringBuilder sql = new StringBuilder(SQL_SELECT_ALL_FOR_GENERATOR);
		this.addDateFilters(name, term, from, to, isOpen, params, sql);
		return this.jdbc.query( sql.toString(), params, DocumentRowWrapper.getInstance() );

	}

	@Override public Collection<DocumentVo> findAllStation(Integer cliId, Integer staId, String name, String term, Date from, Date to, Boolean isOpen) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue(DocumentVo.COLUMN_CLI_ID, cliId).addValue(DocStationVo.COLUMN_STA_ID, staId);
		StringBuilder sql = new StringBuilder(SQL_SELECT_ALL_FOR_STATION);
		this.addDateFilters(name, term, from, to, isOpen, params, sql);
		return this.jdbc.query( sql.toString(), params, DocumentRowWrapper.getInstance() );

	}
}

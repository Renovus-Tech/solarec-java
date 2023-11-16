package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDocStationDao;
import tech.renovus.solarec.db.data.dao.interfaces.DocStationDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.FullDocStationRowWrapper;
import tech.renovus.solarec.vo.db.data.DocStationVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class DocStationDaoImpl extends BaseDocStationDao implements DocStationDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_STATION			= "DELETE FROM doc_station WHERE cli_id = :cli_id AND sta_id = :sta_id";
	private final String SQL_DELETE_ALL_FOR_DOCUMENT		= "DELETE FROM doc_station WHERE cli_id = :cli_id AND doc_id = :doc_id";
	private final String SQL_GEL_ALL_FOR_DOCUMENTS			= "SELECT * FROM doc_station ds, station s WHERE ds.cli_id = :cli_id AND ds.doc_id in (:docIds) AND ds.cli_id = s.cli_id AND ds.sta_id = s.sta_id_auto";
	
	//--- Constructors --------------------------
	@Autowired public DocStationDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void deleteAllForStation(Integer cliId, Integer staId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_STATION,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("sta_id", staId)
			);
	}
	
	@Override public void deleteDocument(Integer cliId, Integer docId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_DOCUMENT,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("doc_id", docId)
			);
	}
	
	@Override public Collection<DocStationVo> findAllFor(Integer cliId, Collection<Integer> docIds) {
		if (CollectionUtil.isEmpty(docIds)) return null;
		
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("docIds", docIds).addValue("cli_id", cliId);
		StringBuilder sql = new StringBuilder(SQL_GEL_ALL_FOR_DOCUMENTS);
		return this.jdbc.query( sql.toString(), params, FullDocStationRowWrapper.getInstance() );
	}
}

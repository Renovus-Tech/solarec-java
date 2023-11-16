package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDocLocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.DocLocationDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.FullDocLocationRowWrapper;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.DocLocationVo;

@Repository
public class DocLocationDaoImpl extends BaseDocLocationDao implements DocLocationDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_LOCATION			= "DELETE FROM doc_location WHERE cli_id = :cli_id AND loc_id = :loc_id";
	private final String SQL_DELETE_ALL_FOR_DOCUMENT			= "DELETE FROM doc_location WHERE cli_id = :cli_id AND doc_id = :doc_id";
	private final String SQL_GEL_ALL_FOR_DOCUMENTS				= "SELECT * FROM doc_location dl, location l WHERE dl.cli_id = :cli_id AND dl.doc_id in (:docIds) AND dl.cli_id = l.cli_id AND dl.loc_id = l.loc_id_auto";

	//--- Constructors --------------------------
	@Autowired public DocLocationDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void deleteAllForLocation(Integer cliId, Integer locId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_LOCATION,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("loc_id", locId)
			);
	}

	@Override public void deleteDocument(Integer cliId, Integer docId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_DOCUMENT,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("doc_id", docId)
			);
	}
	
	@Override public Collection<DocLocationVo> findAllFor(Integer cliId, Collection<Integer> docIds) {
		if (CollectionUtil.isEmpty(docIds)) return null;
		
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("docIds", docIds).addValue("cli_id", cliId);
		StringBuilder sql = new StringBuilder(SQL_GEL_ALL_FOR_DOCUMENTS);
		return this.jdbc.query( sql.toString(), params, FullDocLocationRowWrapper.getInstance() );
	}
}

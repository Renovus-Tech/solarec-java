package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseDocGeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.DocGeneratorDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.FullDocGeneratorRowWrapper;
import tech.renovus.solarec.vo.db.data.DocGeneratorVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class DocGeneratorDaoImpl extends BaseDocGeneratorDao implements DocGeneratorDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_GENERATOR			= "DELETE FROM doc_generator WHERE cli_id = :cli_id AND gen_id = :gen_id";
	private final String SQL_DELETE_ALL_FOR_DOCUMENT			= "DELETE FROM doc_generator WHERE cli_id = :cli_id AND doc_id = :doc_id";
	private final String SQL_GEL_ALL_FOR_DOCUMENTS				= "SELECT * FROM doc_generator dg, generator g WHERE dg.cli_id = :cli_id AND dg.doc_id in (:docIds) AND dg.cli_id = g.cli_id AND dg.gen_id = g.gen_id_auto";
	
	//--- Constructors --------------------------
	@Autowired public DocGeneratorDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public void deleteAllForGenerator(Integer cliId, Integer genId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_GENERATOR,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("gen_id", genId)
			);
	}

	@Override public void deleteDocument(Integer cliId, Integer docId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_DOCUMENT,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("doc_id", docId)
			);
		
	}
	
	@Override public Collection<DocGeneratorVo> findAllFor(Integer cliId, Collection<Integer> docIds) {
		if (CollectionUtil.isEmpty(docIds)) return null;
		
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("docIds", docIds).addValue("cli_id", cliId);
		StringBuilder sql = new StringBuilder(SQL_GEL_ALL_FOR_DOCUMENTS);
		return this.jdbc.query( sql.toString(), params, FullDocGeneratorRowWrapper.getInstance() );
	}

}

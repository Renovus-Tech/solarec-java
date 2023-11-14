package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DocGeneratorVo;

public interface DocGeneratorDao {

	Collection<DocGeneratorVo> findAll();
	DocGeneratorVo findVo(Integer cliId, Integer docId, Integer genId);
	void insert(DocGeneratorVo vo);
	void update(DocGeneratorVo vo);
	void delete(DocGeneratorVo vo);
	void synchronize(DocGeneratorVo vo);
	void synchronize(Collection<DocGeneratorVo> vos);
	
	void deleteAllForGenerator(Integer cliId, Integer genId);
	void deleteDocument(Integer cliId, Integer docId);
	Collection<DocGeneratorVo> findAllFor(Integer cliId, Collection<Integer> docIds);

}


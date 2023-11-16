package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.DocLocationVo;

public interface DocLocationDao {

	Collection<DocLocationVo> findAll();
	DocLocationVo findVo(Integer cliId, Integer docId, Integer locId);
	void insert(DocLocationVo vo);
	void update(DocLocationVo vo);
	void delete(DocLocationVo vo);
	void synchronize(DocLocationVo vo);
	void synchronize(Collection<DocLocationVo> vos);
	
	void deleteAllForLocation(Integer cliId, Integer locId);
	void deleteDocument(Integer cliId, Integer docId);
	Collection<DocLocationVo> findAllFor(Integer cliId, Collection<Integer> docIds);
}


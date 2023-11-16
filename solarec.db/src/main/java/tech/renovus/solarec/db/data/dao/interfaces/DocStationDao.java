package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.DocStationVo;

public interface DocStationDao {

	Collection<DocStationVo> findAll();
	DocStationVo findVo(Integer cliId, Integer staId, Integer docId);
	void insert(DocStationVo vo);
	void update(DocStationVo vo);
	void delete(DocStationVo vo);
	void synchronize(DocStationVo vo);
	void synchronize(Collection<DocStationVo> vos);
	
	void deleteAllForStation(Integer cliId, Integer staId);
	void deleteDocument(Integer cliId, Integer docId);
	Collection<DocStationVo> findAllFor(Integer cliId, Collection<Integer> docIds);

}


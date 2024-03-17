package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocMetadataVo;

public interface LocMetadataDao {

	Collection<LocMetadataVo> findAll();
	LocMetadataVo findVo(Integer cliId, Integer locId, String metadataName);
	void insert(LocMetadataVo vo);
	void update(LocMetadataVo vo);
	void delete(LocMetadataVo vo);
	void synchronize(LocMetadataVo vo);
	void synchronize(Collection<LocMetadataVo> vos);

	Collection<LocMetadataVo> getAllFor(Integer cliId, Integer locId);
}


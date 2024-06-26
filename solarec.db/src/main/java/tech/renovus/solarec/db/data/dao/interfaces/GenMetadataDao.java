package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.GenMetadataVo;

public interface GenMetadataDao {

	Collection<GenMetadataVo> findAll();
	GenMetadataVo findVo(Integer cliId, Integer genId, String metadataCode);
	void insert(GenMetadataVo vo);
	void update(GenMetadataVo vo);
	void delete(GenMetadataVo vo);
	void synchronize(GenMetadataVo vo);
	void synchronize(Collection<GenMetadataVo> vos);

	Collection<GenMetadataVo> getAllFor(Integer cliId, Integer genId);
}


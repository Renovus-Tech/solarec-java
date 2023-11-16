package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.StaMetadataVo;

public interface StaMetadataDao {

	Collection<StaMetadataVo> findAll();
	StaMetadataVo findVo(Integer cliId, Integer staId, String metadataName);
	void insert(StaMetadataVo vo);
	void update(StaMetadataVo vo);
	void delete(StaMetadataVo vo);
	void synchronize(StaMetadataVo vo);
	void synchronize(Collection<StaMetadataVo> vos);

}


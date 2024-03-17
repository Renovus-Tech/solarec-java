package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliMetadataVo;

public interface CliMetadataDao {

	Collection<CliMetadataVo> findAll();
	CliMetadataVo findVo(Integer cliId, String metadataName);
	void insert(CliMetadataVo vo);
	void update(CliMetadataVo vo);
	void delete(CliMetadataVo vo);
	void synchronize(CliMetadataVo vo);
	void synchronize(Collection<CliMetadataVo> vos);

	Collection<CliMetadataVo> getAllFor(Integer cliId);
}

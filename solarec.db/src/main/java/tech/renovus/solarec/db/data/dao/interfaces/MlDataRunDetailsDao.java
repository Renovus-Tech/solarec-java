package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import tech.renovus.solarec.vo.db.data.MlDataRunDetailsVo;

public interface MlDataRunDetailsDao {

	Collection<MlDataRunDetailsVo> findAll();
	MlDataRunDetailsVo findVo(Integer runDetId);
	void insert(MlDataRunDetailsVo vo);
	void update(MlDataRunDetailsVo vo);
	void delete(MlDataRunDetailsVo vo);
	void synchronize(MlDataRunDetailsVo vo);
	void synchronize(Collection<MlDataRunDetailsVo> vos);
	
	Collection<MlDataRunDetailsVo> retrieveVo(Integer cliId, List<Integer> generators, Date from, Date to);

}


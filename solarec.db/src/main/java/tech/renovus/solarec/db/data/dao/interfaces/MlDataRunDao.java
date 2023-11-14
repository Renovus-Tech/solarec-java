package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.MlDataRunVo;

public interface MlDataRunDao {

	Collection<MlDataRunVo> findAll();
	MlDataRunVo findVo(Integer runId);
	void insert(MlDataRunVo vo);
	void update(MlDataRunVo vo);
	void delete(MlDataRunVo vo);
	void synchronize(MlDataRunVo vo);
	void synchronize(Collection<MlDataRunVo> vos);

}


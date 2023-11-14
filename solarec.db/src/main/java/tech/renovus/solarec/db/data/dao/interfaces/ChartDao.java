package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.ChartVo;

public interface ChartDao {

	Collection<ChartVo> findAll();
	ChartVo findVo(Integer chrId);
	void insert(ChartVo vo);
	void update(ChartVo vo);
	void delete(ChartVo vo);
	void synchronize(ChartVo vo);
	void synchronize(Collection<ChartVo> vos);

}


package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.DataCategoryVo;

public interface DataCategoryDao {

	Collection<DataCategoryVo> findAll();
	DataCategoryVo findVo(Integer dataCatId);
	void insert(DataCategoryVo vo);
	void update(DataCategoryVo vo);
	void delete(DataCategoryVo vo);
	void synchronize(DataCategoryVo vo);
	void synchronize(Collection<DataCategoryVo> vos);

}

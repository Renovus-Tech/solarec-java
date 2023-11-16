package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.DataTypeVo;

public interface DataTypeDao {

	Collection<DataTypeVo> findAll();
	DataTypeVo findVo(Integer dataTypeId);
	void insert(DataTypeVo vo);
	void update(DataTypeVo vo);
	void delete(DataTypeVo vo);
	void synchronize(DataTypeVo vo);
	void synchronize(Collection<DataTypeVo> vos);

}


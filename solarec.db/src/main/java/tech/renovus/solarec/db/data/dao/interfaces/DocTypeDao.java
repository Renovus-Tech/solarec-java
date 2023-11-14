package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DocTypeVo;

public interface DocTypeDao {

	Collection<DocTypeVo> findAll();
	DocTypeVo findVo(Integer docTypeId);
	void insert(DocTypeVo vo);
	void update(DocTypeVo vo);
	void delete(DocTypeVo vo);
	void synchronize(DocTypeVo vo);
	void synchronize(Collection<DocTypeVo> vos);

}


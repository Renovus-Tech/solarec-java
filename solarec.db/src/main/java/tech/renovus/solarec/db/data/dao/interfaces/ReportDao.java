package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.ReportVo;

public interface ReportDao {

	Collection<ReportVo> findAll();
	ReportVo findVo(Integer cliId, Integer repId);
	void insert(ReportVo vo);
	void update(ReportVo vo);
	void delete(ReportVo vo);
	void synchronize(ReportVo vo);
	void synchronize(Collection<ReportVo> vos);

}


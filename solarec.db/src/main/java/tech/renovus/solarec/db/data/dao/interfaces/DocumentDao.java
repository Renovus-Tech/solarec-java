package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.DocumentVo;

public interface DocumentDao {

	Collection<DocumentVo> findAll();
	DocumentVo findVo(Integer cliId, Integer docId);
	void insert(DocumentVo vo);
	void update(DocumentVo vo);
	void delete(DocumentVo vo);
	void synchronize(DocumentVo vo);
	void synchronize(Collection<DocumentVo> vos);
	
	Collection<DocumentVo> findAll(Integer cliId, String name, String term, Date from, Date to, Boolean isOpen);
	Collection<DocumentVo> findAllLocation	(Integer cliId, Integer locId, String name, String term, Date from, Date to, Boolean isOpen);
	Collection<DocumentVo> findAllGenerator	(Integer cliId, Integer genId, String name, String term, Date from, Date to, Boolean isOpen);
	Collection<DocumentVo> findAllStation	(Integer cliId, Integer staId, String name, String term, Date from, Date to, Boolean isOpen);

}


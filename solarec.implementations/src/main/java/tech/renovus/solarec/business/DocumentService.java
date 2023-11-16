package tech.renovus.solarec.business;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.DocTypeVo;
import tech.renovus.solarec.vo.db.data.DocumentVo;

public interface DocumentService {
	
	DocumentVo findVo(Integer docId, UserData userData) throws CoreException;
	Collection<DocumentVo> create(DocumentVo vo, UserData userDatao) throws CoreException;
	void update(DocumentVo vo, UserData userDatao);
	void delete(Integer id, UserData userDatao);

	Collection<DocumentVo> findAll			(Integer offset, Integer size, String name, String term, Date from, Date to, String period, Boolean isOpen, UserData userData) throws CoreException;
	Collection<DocumentVo> findAllLocation	(Integer locId, String name, String term, Date from, Date to, String period, Boolean isOpen, UserData userData) throws CoreException;
	Collection<DocumentVo> findAllGenerator	(Integer genId, String name, String term, Date from, Date to, String period, Boolean isOpen, UserData userData) throws CoreException;
	Collection<DocumentVo> findAllStation	(Integer staId, String name, String term, Date from, Date to, String period, Boolean isOpen, UserData userData) throws CoreException;
	File getFilePath(DocumentVo docVo);
	
	Collection<DocTypeVo> getAllDocTypes(UserData userData);
}

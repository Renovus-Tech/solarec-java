package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.ClientVo;

public interface ClientDao {

	Collection<ClientVo> findAll();
	ClientVo findVo(Integer cliId);
	void insert(ClientVo vo);
	void update(ClientVo vo);
	void delete(ClientVo vo);
	void synchronize(ClientVo vo);
	void synchronize(Collection<ClientVo> vos);
	ClientVo findBy(String cliName);
	Collection<ClientVo> findAllForUser(Integer usrId, boolean sortedByAccess);

}


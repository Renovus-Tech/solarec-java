package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.GenNeighbourVo;

public interface GenNeighbourDao {

	Collection<GenNeighbourVo> findAll();
	GenNeighbourVo findVo(Integer cliId, Integer genId, Integer genIdNeighbour);
	void insert(GenNeighbourVo vo);
	void update(GenNeighbourVo vo);
	void delete(GenNeighbourVo vo);
	void synchronize(GenNeighbourVo vo);
	void synchronize(Collection<GenNeighbourVo> vos);
	
	Collection<GenNeighbourVo> getAllFor(Integer cliId, Integer locId);
	Collection<GenNeighbourVo> getAllFor(Integer cliId);
	void deleteAllFor(Integer cliId, Integer genId);

}


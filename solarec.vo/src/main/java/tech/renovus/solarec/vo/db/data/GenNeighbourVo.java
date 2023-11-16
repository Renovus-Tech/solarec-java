package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbGenNeighbourVo;

public class GenNeighbourVo extends DbGenNeighbourVo {

	//--- Constructors --------------------------
	public GenNeighbourVo() {
	}

	public GenNeighbourVo(Integer cliId, Integer genId, Integer genIdNeighbour) {
		this.setPk(cliId, genId, genIdNeighbour);
	}

	public GenNeighbourVo(GeneratorVo genVo, Integer genIdNeighbour, Integer position) {
		this.setPk(genVo.getCliId(), genVo.getGenId(), genIdNeighbour);
		this.setGenIdNeighbourPosition(position);
		this.setSyncType(GenNeighbourVo.SYNC_INSERT);
	}

}
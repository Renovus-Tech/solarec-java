package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDocGeneratorVo;

public class DocGeneratorVo extends DbDocGeneratorVo {

	//--- Private properties --------------------
	private GeneratorVo generatorVo;
	
	//--- Constructors --------------------------
	public DocGeneratorVo() {
	}

	public DocGeneratorVo(Integer cliId, Integer docId, Integer genId) {
		this.setPk(cliId, docId, genId);
	}
	
	public DocGeneratorVo(DocumentVo docVo, GeneratorVo genVo) {
		this.setPk(docVo.getCliId(), docVo.getDocId(), genVo.getGenId());
		this.generatorVo = genVo;
	}

	//--- Getters and Setters -------------------
	public GeneratorVo getGeneratorVo() {
		return generatorVo;
	}
	public void setGeneratorVo(GeneratorVo generatorVo) {
		this.generatorVo = generatorVo;
	}
}
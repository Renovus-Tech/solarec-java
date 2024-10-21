package testing.tech.renovus.solarec;

import tech.renovus.solarec.business.impl.alert.base.AbstractAlertProcessing;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.GenAlertVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocAlertVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaAlertVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public class AlertProcessingTest extends AbstractAlertProcessing {

	//--- Public properties ---------------------
	private boolean calledPrepareFor = false;
	private boolean calledProcess = false;
	private boolean calledGenerateAlertToSave = false;
	
	private DataProcessingVo dataProVo = null;
	
	//--- Overridden methods --------------------
	@Override
	public void prepareFor(DataProcessingVo dataProVo) {
		this.calledPrepareFor = true;
		this.dataProVo = dataProVo;
	}
	
	@Override public void process() throws ProcessingException { this.calledProcess = true; }
	
	@Override
	public ClientVo generateAlertToSave() {
		this.calledGenerateAlertToSave = true;
		
		ClientVo cliVo = new ClientVo();
		cliVo.setCliId(this.dataProVo.getCliId());
		
		LocationVo locVo = new LocationVo();
		locVo.setLocId(this.dataProVo.getLocId());
		
		cliVo.add(locVo);
		
		LocAlertVo locAlertVo = new LocAlertVo();
		locVo.add(locAlertVo);
		
		GeneratorVo genVo = new GeneratorVo();
		locVo.add(genVo);
		
		GenAlertVo genAlertVo = new GenAlertVo();
		genVo.add(genAlertVo);
		
		StationVo staVo = new StationVo();
		locVo.add(staVo);
		
		StaAlertVo staAlertVo = new StaAlertVo();
		staVo.add(staAlertVo);
		
		return cliVo;
	}

}

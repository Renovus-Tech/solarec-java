package tech.renovus.solarec.inverters.brand.solarman;

import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;

/**
 * Documentation: https://forum.gce-electronics.com/uploads/short-url/3v9kqnZYHoiX53IZEl0XX1mKs1.pdf
 */
public class SolarmanInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_PRD		= "https://api.solarmanpv.com";
	private static final String URL_TEST	= "";
	
	//--- Overridden methods --------------------
	@Override public void prepareFor(ClientVo client) {}
	@Override public boolean canRetrieve() { return false; }
	@Override public boolean continueWithStats() { return false; }
	@Override public String getReasonWhyCantRetrieve() {return null;}
	
	@Override
	public InverterData retrieveData() {
		// TODO Auto-generated method stub
		return null;
	}

}

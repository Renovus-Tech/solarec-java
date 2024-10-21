package testing.tech.renovus.solarec;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessing;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class DataProcessingTest extends AbstractDataProcessing {

	//--- Public properties ---------------------
	public boolean calledValidateFile;
	public boolean calledPrepareFor;
	public boolean calledProcess;
	public boolean calledGenerateDataToSave;
	public boolean calledContinueWithStatistics;
	
	//--- Overridden methods --------------------
	@Override
	public Collection<File> validateFile(ClientVo client, InputStream stream) throws ProcessingException {
		this.calledValidateFile = true;
		return null;
	}

	@Override
	public void prepareFor(ClientVo cliVo, InputStream stream) {
		this.calledPrepareFor = true;
	}

	@Override
	public void process() throws ProcessingException {
		this.calledProcess = true;
	}

	@Override
	public ClientVo generateDataToSave() {
		this.calledGenerateDataToSave = true;
		return null;
	}

	@Override
	public boolean continueWithSatistics() {
		this.calledContinueWithStatistics = true;
		return false;
	}

}

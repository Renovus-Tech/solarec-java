package tech.renovus.solarec.business;

import java.io.File;
import java.util.Collection;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.rest.background.Processing;
import tech.renovus.solarec.vo.rest.entity.DataDefinitionTrigger;

public interface ProcessingService {

	///--- Service methods ----------------------
	Collection<DataProcessingVo> process(Processing request, UserData userData) throws CoreException;
	Collection<DataProcessingVo> process(DataDefinitionTrigger trigger, UserData userData) throws CoreException;
	Collection<DataProcessingVo> findAllClient(Integer cliId, UserData userData);
	
	DataProcessingVo doProcessing(DataDefinitionVo dataDefVo, Processing request, File fileToProcess, ClientVo cliVo) throws CoreException;
}

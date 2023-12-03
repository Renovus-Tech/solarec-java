package tech.renovus.solarec.business.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tech.renovus.solarec.business.CalculationService;
import tech.renovus.solarec.business.impl.calculation.DataCalculationFactory;
import tech.renovus.solarec.business.impl.calculation.base.AbstractDataCalculation;
import tech.renovus.solarec.configuration.RenovusSolarConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.StatProcessingDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;

@Service
public class CalculationServiceImpl implements CalculationService {

	//--- Resources -----------------------------
	@Resource StatProcessingDao statProDao;
	@Resource RenovusSolarConfiguration config;
	@Autowired AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	//--- Private methods -----------------------
	private void saveToFile(File file, String content) throws CoreException {
		try (
			FileWriter o = new FileWriter(file, false);
		) {
			o.write(content);
			o.flush();
		} catch (IOException e) {
			throw new CoreException(e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW) private StatProcessingVo createStatProcessing(DataProcessingVo dataProVo, StatDefinitionVo statDefVo) {
		StatProcessingVo result = new StatProcessingVo();
		result.setCliId(dataProVo.getCliId());
		result.setStatDefId(statDefVo.getStatDefId());
		result.setStatProDateStart(new Date());
		result.setStatProResult(StatProcessingVo.RESULT_PROCESSING_PENDING);

		this.statProDao.insert(result);
		return result;
	}

	//--- Overridden methods --------------------
	@Override public StatProcessingVo calculate(DataProcessingVo dataProVo, StatDefinitionVo statDefVo) {
		String processingLog	= null;
		StatProcessingVo result = this.createStatProcessing(dataProVo, statDefVo);
		
		try {
			
			AbstractDataCalculation calculation = DataCalculationFactory.getInstance().get(statDefVo);
			this.autowireCapableBeanFactory.autowireBean(calculation);
			calculation.prepareFor(statDefVo, dataProVo, result);
			calculation.calculate();
			processingLog = calculation.getLog();
			
			dataProVo.setDataProResult(StatProcessingVo.RESULT_PROCESSING_OK);
			
		} catch (Exception e) {
			dataProVo.setDataProResult(StatProcessingVo.RESULT_PROCESSING_ERROR);
			processingLog = "Error: " + e.getLocalizedMessage();
			processingLog += StringUtil.toString(e);
			
		} finally {
			result.setStatProDateEnd(new Date());
		}
		
		File logFile	= new File(this.config.getPathLog(), DateUtil.formatDateTime(new Date(), DateUtil.FMT_DATE) + File.separator + "stat_processing_" + result.getStatProId() + ".log");
		logFile.getParentFile().mkdirs();
		result.setStatProFileLog(logFile.getName());
		
		try {
			this.saveToFile(logFile, processingLog);
		} catch (CoreException e) {
			System.out.println("Save log error" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		this.statProDao.update(result);
		
		return result;
	}

}

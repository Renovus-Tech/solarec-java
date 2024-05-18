package tech.renovus.solarec.scheduler;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.CtrDataDao;
import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.grid.DataGridService.DataGridServiceException;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

@Component
public class DataGridCheckScheduler {

	//--- Resources -----------------------------
	@Resource DataGridService dataGridService;
	@Resource CountryDao countryDao;
	@Resource CtrDataDao ctrDataDao;
	
    //--- Private methods -----------------------
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void process(CountryVo ctrVo) throws DataGridServiceException {
		Collection<CtrDataVo> datas = this.dataGridService.retrieveGridData(ctrVo);
		if (CollectionUtil.notEmpty(datas)) {
			Date newDate = new Date();
			Date minDate = null;
			Date maxDate = null;
			for (CtrDataVo data : datas) {
				data.setCtrId(ctrVo.getCtrId());
				data.setDataDateAdded(newDate);
				data.setSyncType(CtrDataVo.SYNC_INSERT);
				
				if (minDate == null || data.getDataDate().before(minDate)) minDate = data.getDataDate();
				if (maxDate == null || data.getDataDate().after(maxDate)) maxDate = data.getDataDate();
			}
		
			if (minDate != null && (ctrVo.getCtrDataDateMin() == null || minDate.before(ctrVo.getCtrDataDateMin()))) ctrVo.setCtrDataDateMin(minDate);
			if (maxDate != null && (ctrVo.getCtrDataDateMax() == null || maxDate.after(ctrVo.getCtrDataDateMax()))) ctrVo.setCtrDataDateMax(maxDate);
			ctrVo.setSyncType(CountryVo.SYNC_UPDATE);
			
			this.ctrDataDao.synchronize(datas);
			this.countryDao.synchronize(ctrVo);
			
			LoggerService.dataGridLogger().info("Amount of data: " + CollectionUtil.size(datas));
			LoggerService.dataGridLogger().info("Min date: " + ctrVo.getCtrDataDateMin());
			LoggerService.dataGridLogger().info("Max date: " + ctrVo.getCtrDataDateMax());
		} else {
			LoggerService.dataGridLogger().info( "No data not retrieve");
		}
	}
	

	//--- Public methods ------------------------
	@Scheduled(cron="0 0 */6 * * *") // every 6 hours
	public void checkDataGrid() {
		Collection<CountryVo> countriesInUse = this.countryDao.getCountriesInUse();
		
		LoggerService.dataGridLogger().info("Starting check of data grid");
		
		try {
			for (CountryVo ctrVo : countriesInUse) {
				LoggerService.dataGridLogger().info("Start retrieve for: " + ctrVo.getCtrName() + " (" + ctrVo.getCtrId() + ")");
				
				try {
					this.process(ctrVo);
				} catch (DataGridServiceException e) {
					LoggerService.dataGridLogger().error("Error found: " + e.getLocalizedMessage(), e);
					throw e;
				}
			}
				
		} catch (Exception e) {
			LoggerService.dataGridLogger().error("Error during data retrieval (all stoped): " + e.getLocalizedMessage(), e);
		} finally {
			LoggerService.dataGridLogger().info("End check of data grid");
		}
	}
}

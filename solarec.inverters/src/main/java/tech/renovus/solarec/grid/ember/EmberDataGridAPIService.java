package tech.renovus.solarec.grid.ember;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.grid.ember.api.CarbonIntensityMonthlyResponse;
import tech.renovus.solarec.grid.ember.api.CountryData;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;

@Service
@ConditionalOnProperty(name = "solarec.service.grid.provider", havingValue = "emberapi")
public class EmberDataGridAPIService implements DataGridService {
	
	//--- Private constants ----------------------
	private static final String URL	= "https://api.ember-energy.org/v1/carbon-intensity/monthly"; //?entity=Brazil&include_all_dates_value_range=true&api_key=MY_API_KEY"

	//--- Resources -----------------------------
	@Autowired EmberDataGridAPIConfiguration config;

	private SimpleDateFormat dateFormatRequest = new SimpleDateFormat("yyyy-MM");
	private SimpleDateFormat dateFormatResponse = new SimpleDateFormat("yyyy-MM-dd");
	
	//--- Private methods -----------------------
	private CarbonIntensityMonthlyResponse retrieveMonthlyData(CountryVo ctrVo, Date startDate) {
		Map<String, String> params = new HashMap<>();
		params.put("entity_code", ctrVo.getCtrCode3());
		params.put("include_all_dates_value_range", BooleanUtils.FALSE_STRING);
		params.put("start_date", this.dateFormatRequest.format(startDate));
		params.put("api_key", this.config.getKey());
		
		return JsonCaller.get(
				URL, 
				null, 
				params, 
				CarbonIntensityMonthlyResponse.class
			);
	}
	
	//--- Implemented methods -------------------1
	@Override
	public Collection<CtrDataVo> retrieveGridData(CountryVo ctrVo) throws DataGridServiceException {
		Calendar cal = GregorianCalendar.getInstance();
		if (ctrVo.getCtrDataDateMax() == null) {
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.add(Calendar.YEAR, -1);
		} else {
			cal.setTimeInMillis(ctrVo.getCtrDataDateMax().getTime());
			cal.add(Calendar.MONTH, -1);
		}
		
		CarbonIntensityMonthlyResponse response = this.retrieveMonthlyData(ctrVo, cal.getTime());
		
		Collection<CtrDataVo> result = new ArrayList<>();
		if (CollectionUtil.notEmpty(response.getData())) {
			try {
				
				for (CountryData apiData : response.getData()) {
					if (apiData.getEmissionsIntensityGco2PerKwh() == null) continue;
					
					Date date = this.dateFormatResponse.parse(apiData.getDate());
					cal.setTime(date);
					cal.add(Calendar.MONTH, 1);
					cal.add(Calendar.DAY_OF_MONTH, -1);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					
					int daysInMonth = cal.get(Calendar.DAY_OF_MONTH);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					
					double hourGco2 = apiData.getEmissionsIntensityGco2PerKwh().doubleValue() / 24 / daysInMonth;
					
					for (int d = 0; d < daysInMonth; d ++) {
						for (int h = 0; h < 24; h++) {
							CtrDataVo data = new CtrDataVo();
							data.setDataTypeId(DataTypeVo.TYPE_COUNTRY_EMISSIONS_INTENSITY_GCO2_PER_KWH);
							data.setDataValue(hourGco2);
							data.setDataDate(cal.getTime());
		
							if (ctrVo.getCtrDataDateMax() == null || cal.getTime().after(ctrVo.getCtrDataDateMax())) {
								
								result.add(data);
								
								cal.add(Calendar.HOUR_OF_DAY, 1);
							}
						}
						
						cal.add(Calendar.DAY_OF_YEAR, 1);
						cal.set(Calendar.HOUR_OF_DAY, 0);
					}
					
					CtrDataVo data = new CtrDataVo();
					data.setDataTypeId(DataTypeVo.TYPE_COUNTRY_EMISSIONS_INTENSITY_GCO2_PER_KWH);
					data.setDataValue(apiData.getEmissionsIntensityGco2PerKwh());
					data.setDataDate(date);
					
					result.add(data);
				}
			} catch (ParseException e ) {
				throw new DataGridServiceException(e);
			}
		}
		
		return result;
	}

}

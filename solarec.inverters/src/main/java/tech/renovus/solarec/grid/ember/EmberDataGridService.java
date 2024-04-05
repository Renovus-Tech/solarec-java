package tech.renovus.solarec.grid.ember;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.client.RestTemplate;

import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;

public class EmberDataGridService implements DataGridService {
	
	//--- Private constants ----------------------
	private static final int COL_COUNTRY_CODE						= 1;
	private static final int COL_YEAR								= 2;
	private static final int COL_EMISSIONS_INTENSITY_GCO2_PER_KWH	= 5;

	//--- Private methods -----------------------
	private File downloadData() throws IOException {
		String fileUrl = "https://raw.githubusercontent.com/ember-climate/ember-data-api/main/data/api_country_overview_yearly.csv";
        File outputFile = File.createTempFile("api_country_overview_yearly", ".csv");

        RestTemplate restTemplate = new RestTemplate();
        byte[] fileBytes = restTemplate.getForObject(fileUrl, byte[].class);

        try (FileOutputStream stream = new FileOutputStream(outputFile)) {
            stream.write(fileBytes);
        }

       return outputFile;
	}
	
	//--- Implemented methods -------------------
	@Override
	public Collection<CtrDataVo> retrieveGridData(CountryVo ctrVo) throws DataGridServiceException {
		try {
			File csvFile = this.downloadData();
			
			try (
				Reader reader		= new FileReader(csvFile);
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			) {
				Collection<CtrDataVo> result	= new ArrayList<>();
				String ctrCode					= ctrVo.getCtrCode3();
				Calendar cal					= GregorianCalendar.getInstance();
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				
				for (CSVRecord csvRecord : csvParser) {
					String country = csvRecord.get(COL_COUNTRY_CODE);
					if (! ctrCode.equals(country)) continue;
					
					int year = Integer.parseInt(csvRecord.get(COL_YEAR));
					int daysInYear = Year.of(year).length();
					double yearGco2 = Double.valueOf(csvRecord.get(COL_EMISSIONS_INTENSITY_GCO2_PER_KWH)); //Accumulative yearly value
					Double hourGco2 = Double.valueOf(yearGco2 / daysInYear / 24);
					
					cal.set(Calendar.YEAR, year);
					cal.set(Calendar.MONTH, Calendar.JANUARY);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					
					for (int d = 0; d < daysInYear; d ++) {
						for (int h = 0; h < 24; h++) {
							CtrDataVo data = new CtrDataVo();
							data.setDataTypeId(DataTypeVo.TYPE_COUNTRY_EMISSIONS_INTENSITY_GCO2_PER_KWH);
							data.setDataValue(hourGco2);
							data.setDataDate(cal.getTime());
		
							result.add(data);
							
							cal.add(Calendar.HOUR_OF_DAY, 1);
						}
						
						cal.add(Calendar.DAY_OF_YEAR, 1);
						cal.set(Calendar.HOUR_OF_DAY, 0);
					}
				}
				
				return result;
			}
		} catch (IOException e) {
			throw new DataGridServiceException(e);
		}
	}

}

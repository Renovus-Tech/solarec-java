package tech.renovus.solarec.business.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.db.data.dao.interfaces.StatProcessingDao;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import testing.tech.renovus.solarec.DataCalculationTest;

@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceImplTest {

	@Mock private StatProcessingDao statProDao;

	@InjectMocks CalculationServiceImpl service = new CalculationServiceImpl();
	
	@Test 
	public void test() {
		DataProcessingVo dataProVo = new DataProcessingVo();
		dataProVo.setCliId(Integer.valueOf(1));
		dataProVo.setLocId(Integer.valueOf(2));
		
		StatDefinitionVo statDefVo = new StatDefinitionVo();
		statDefVo.setStatDefId(Integer.valueOf(3));
		statDefVo.setStatDefExecutable(DataCalculationTest.class.getCanonicalName());
		
		this.service.calculate(dataProVo, statDefVo);
		
		verify(this.statProDao).insert(any());
	}
}

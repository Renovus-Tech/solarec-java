package tech.renovus.solarec.vo.db.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlertDefinitionVoTest {

	@Test
	public void test() throws Exception {
		Integer pk1 = Integer.valueOf(1);

		AlertDefinitionVo vo = new AlertDefinitionVo();
		AlertDefinitionVo vo2 = new AlertDefinitionVo();
		AlertDefinitionVo voPk = new AlertDefinitionVo(pk1);
		
		vo.setAlertDefId(pk1);
		vo.setChildrensId();
		vo.synchronize(vo2);
		vo.synchronizeForce(AlertDefinitionVo.SYNC_INIT);
		
		vo2.setPk(vo);

		assertEquals(pk1, voPk.getAlertDefId());
		assertEquals(voPk, vo);
		assertTrue(voPk.isSame(voPk));
		assertTrue(voPk.validData());
		
		assertEquals(voPk.hashCode(), voPk.hashCode());
		
		assertEquals(vo,vo2);
	}

}

package tech.renovus.solarec.grid.disabled;

import java.util.Collection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

@Service
@ConditionalOnProperty(name = "solarec.service.grid.provider", havingValue = "disabled")
public class DisabledDataGridService implements DataGridService {

	@Override
	public Collection<CtrDataVo> retrieveGridData(CountryVo ctrVo) throws DataGridServiceException {
		//Do nothing is disabled
		return null;
	}

}

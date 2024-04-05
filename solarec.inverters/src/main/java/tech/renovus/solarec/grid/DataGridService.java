package tech.renovus.solarec.grid;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

public interface DataGridService {

	public static class DataGridServiceException extends Exception {
		
		private static final long serialVersionUID = -4151013261263513247L;
		
		public DataGridServiceException() { super(); }
		public DataGridServiceException(String msg) { super(msg); }
		public DataGridServiceException(Exception parent) { super(parent); }
		public DataGridServiceException(String msg, Exception parent) { super(msg, parent); }
	}

	
	Collection<CtrDataVo> retrieveGridData(CountryVo ctrVo) throws DataGridServiceException;
}

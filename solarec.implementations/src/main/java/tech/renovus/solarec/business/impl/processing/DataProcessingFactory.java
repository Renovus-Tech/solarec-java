package tech.renovus.solarec.business.impl.processing;

import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessing;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

public class DataProcessingFactory {

	//--- Constructors --------------------------
	private DataProcessingFactory() {}
	private static DataProcessingFactory instance = new DataProcessingFactory();
	public static DataProcessingFactory getInstance() { return DataProcessingFactory.instance; }
	
	//--- Private methods -----------------------
	private AbstractDataProcessing get(Class<? extends AbstractDataProcessing> aClass) throws CoreException {
		try {
			return aClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CoreException(e);
		}
	}
	
	//--- Factory methods -----------------------
//	public AbstractDataProcessing getSampleProcessing() throws CoreException { return this.get(SampleProcessing.class); }
//	public AbstractDataProcessing getSacadaProcessing() throws CoreException { return this.get(ScadaExcelProcessing.class); }
//	public AbstractDataProcessing getCSVSampleProcessing() throws CoreException { return this.get(CSVSampleProcessing.class); }
	
	public AbstractDataProcessing get(DataDefinitionVo vo) throws CoreException { return this.get(vo.getDataDefExecutable()); }
	public AbstractDataProcessing get(String className) throws CoreException {
		try {
			return this.get((Class<? extends AbstractDataProcessing>) Class.forName(className));
		} catch (ClassNotFoundException | CoreException e) {
			throw new CoreException(e);
		}
	}


}

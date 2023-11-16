package tech.renovus.solarec.business.impl.alert;

import tech.renovus.solarec.business.impl.alert.base.AbstractAlertProcessing;
import tech.renovus.solarec.exceptions.CoreException;

public class AlertProcessingFactory {

	//--- Constructors --------------------------
	private AlertProcessingFactory() {}
	private static AlertProcessingFactory instance = new AlertProcessingFactory();
	public static AlertProcessingFactory getInstance() { return AlertProcessingFactory.instance; }
	
	//--- Private methods -----------------------
	private AbstractAlertProcessing get(Class<? extends AbstractAlertProcessing> aClass) throws CoreException {
		try {
			return aClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CoreException(e);
		}
	}
//	
//	//--- Factory methods -----------------------
//	public AbstractAlertProcessing getSampleProcessing() throws CoreException { return null; /* this.get(SampleAlert.class); */ }
//	
//	public AbstractAlertProcessing get(AlertDefinitionVo vo) throws CoreException { return this.get(vo.getAlertDefExecutable()); }
	public AbstractAlertProcessing get(String className) throws CoreException {
		try {
			return this.get((Class<? extends AbstractAlertProcessing>) Class.forName(className));
		} catch (ClassNotFoundException | CoreException e) {
			throw new CoreException(e);
		}
	}


}

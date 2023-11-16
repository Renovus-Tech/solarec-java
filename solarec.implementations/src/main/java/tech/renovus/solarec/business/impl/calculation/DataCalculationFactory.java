package tech.renovus.solarec.business.impl.calculation;

import tech.renovus.solarec.business.impl.calculation.base.AbstractDataCalculation;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;

public class DataCalculationFactory {

	//--- Constructors --------------------------
	private DataCalculationFactory() {}
	private static DataCalculationFactory instance = new DataCalculationFactory();
	public static DataCalculationFactory getInstance() { return DataCalculationFactory.instance; }
	
	//--- Private methods -----------------------
	private AbstractDataCalculation get(Class<? extends AbstractDataCalculation> aClass) throws CoreException {
		try {
			return aClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CoreException(e);
		}
	}
	
	//--- Factory methods -----------------------
	public AbstractDataCalculation get(StatDefinitionVo vo) throws CoreException { return this.get(vo.getStatDefExecutable()); }
	public AbstractDataCalculation get(String className) throws CoreException {
		try {
			return this.get((Class<? extends AbstractDataCalculation>) Class.forName(className));
		} catch (ClassNotFoundException | CoreException e) {
			throw new CoreException(e);
		}
	}


}

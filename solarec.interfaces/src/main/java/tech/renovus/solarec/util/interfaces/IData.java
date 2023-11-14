package tech.renovus.solarec.util.interfaces;

import java.util.Date;

public interface IData {

	void setDataDate(Date aDate);
	void setDataTypeId(Integer aType);
	void setDataValue(Double aValue);
	void setDataProId(Integer dataProId);
	void setDataDateAdded(Date dateAdded);
	
	Date getDataDate();
	Integer getDataTypeId();
	Double getDataValue();
	Integer getDataProId();
	Date getDataDateAdded();
	
	public int compareTo(IData data);
	
}

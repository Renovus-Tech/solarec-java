package tech.renovus.solarec.util.interfaces;

import java.util.Date;

import tech.renovus.solarec.exceptions.CoreException;

public interface IDataContainer {

	Integer getId();
	String getName();
	boolean isRequired();

	void addData(Date date, int typeId, double value) throws CoreException;
}

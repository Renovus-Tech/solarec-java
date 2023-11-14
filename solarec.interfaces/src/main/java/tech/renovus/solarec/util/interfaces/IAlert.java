package tech.renovus.solarec.util.interfaces;

import java.util.Date;

public interface IAlert {

	public Integer getAlertProId();
	public Date getAlertDateAdded();
	public Date getAlertDateSend();
	public String getAlertMessage();

	public void setAlertProId(Integer alertProId);
	public void setAlertDateAdded(Date alertDateAdded);
	public void setAlertDateSend(Date alertDateSend);
	public void setAlertMessage(String alertMessage);

}

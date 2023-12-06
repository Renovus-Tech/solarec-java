package tech.renovus.solarec.vo.rest.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Alert {
	
	//--- Public constants ----------------------
	public static final int TYPE_NORMAL		= 1;
	public static final int TYPE_WARNING	= 2;
	public static final int TYPE_HIGH		= 3;
	public static final int TYPE_CRITICAL	= 4;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date date;
	private Integer type;
	private boolean firstView;
	private String message;
	private String extraInfo;
	
	//--- Getters and Setters -------------------
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public boolean isFirstView() {
		return firstView;
	}
	public void setFirstView(boolean firstView) {
		this.firstView = firstView;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	

}
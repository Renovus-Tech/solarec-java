package tech.renovus.solarec.vo.custom.chart;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Error {
	
	//--- Public constants ----------------------
	public static final int CODE_SYSTEM		= 901;
	public static final int CODE_NO_DATA	= 902;

	//--- Private properties --------------------
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date time;
	private int code;
    private String message;
    private boolean noData;

    //--- Constructors --------------------------
    public Error() {}
    
    public Error(Integer code, String message, boolean noData) {
    	this.time		= new Date();
    	this.code		= code;
    	this.message	= message;
    	this.noData		= noData;
    }
    
    //--- Getters and Setters -------------------
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

}

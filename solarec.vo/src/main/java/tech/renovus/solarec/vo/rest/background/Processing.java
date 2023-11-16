package tech.renovus.solarec.vo.rest.background;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Processing {

	//--- Private properties --------------------
	private Integer clientId;
	private Integer locationId;
	private Integer generatorId;
	private Integer stationId;
	private String filePath;
	
	private Integer processingId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date end;
	private Integer result;
	private String logPath;
	
	@JsonIgnore
	private MultipartFile filePart;
	
	//--- Getters and Setters -------------------
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getGeneratorId() {
		return generatorId;
	}
	public void setGeneratorId(Integer generatorId) {
		this.generatorId = generatorId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getProcessingId() {
		return processingId;
	}
	public void setProcessingId(Integer processingId) {
		this.processingId = processingId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getLogPath() {
		return logPath;
	}
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	public MultipartFile getFilePart() {
		return filePart;
	}
	public void setFilePart(MultipartFile filePart) {
		this.filePart = filePart;
	}
}

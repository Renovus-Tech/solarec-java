package tech.renovus.solarec.vo.custom.chart;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public class Chart {

	//--- Private properties --------------------
    private String type;
    private Integer location;
    private String period;
    private String groupBy;
    @JsonFormat(pattern="yyyy-MM-dd") private Date from;
    @JsonFormat(pattern="yyyy-MM-dd") private Date to;
    private List<Integer> id = null;

    //--- Constructors --------------------------
    public Chart(String type) {
    	this.type = type;
    }
    
    //--- Public methods ------------------------
    public void populate(ChartFilter filter) {
    	this.location	= filter.getLocation();
    	this.period		= filter.getPeriod();
    	this.groupBy	= filter.getGroupBy();
    	this.from		= filter.getFrom();
    	this.to			= filter.getTo();
    	this.id			= filter.getGenerators();
	}
    
    //--- Getters and Setters -------------------
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }
}

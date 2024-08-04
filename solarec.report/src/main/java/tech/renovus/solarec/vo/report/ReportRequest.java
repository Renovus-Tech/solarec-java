package tech.renovus.solarec.vo.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;

public class ReportRequest {
	
	//--- Public constants ----------------------
	public static final String TYPE_WEEKLY		= "WEEKLY";
	public static final String TYPE_CUSTOM		= "CUSTOM";

	public static final String TYPE_TEST		= "test";
	
	//--- Private properties --------------------
	private Integer cliId;
	private String locName;
	private Integer locId;
	private Integer typeId;
	private String type;
	private String date;
	private String dateTo;
	
	private boolean sendByEmail;
	private List<String> emails;
	private List<String> emailsCC;
	private List<String> emailsBCC;
	private List<String> sections;
	
	private String subjectLabel = "email.report.subject";
	private String contentLabel = "email.report.content";
	
	//--- Public methods ------------------------
	public Date getTheDate() { return DateUtil.stringToDate(this.date, DateUtil.FMT_PARAMETER_DATE); }
	public Date getTheDateTo() { return DateUtil.stringToDate(this.dateTo, DateUtil.FMT_PARAMETER_DATE); }
	
	public void setDate(Date date) { this.date = DateUtil.formatDateTime(date, DateUtil.FMT_PARAMETER_DATE); }
	
	public void addEmail(String email) {
		if (StringUtil.isEmpty(email)) {
			return;
		}
		if (this.emails == null) {
			this.emails = new ArrayList<>(1);
		}
		this.emails.add(email);
	}
	
	public void addEmailCC(String email) {
		if (StringUtil.isEmpty(email)) {
			return;
		}
		if (this.emailsCC == null) {
			this.emailsCC = new ArrayList<>(1);
		}
		this.emailsCC.add(email);
	}
	
	public void addEmailBCC(String email) {
		if (StringUtil.isEmpty(email)) {
			return;
		}
		if (this.emailsBCC == null) {
			this.emailsBCC = new ArrayList<>(1);
		}
		this.emailsBCC.add(email);
	}
	
	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isSendByEmail() {
		return sendByEmail;
	}
	public void setSendByEmail(boolean sendByEmail) {
		this.sendByEmail = sendByEmail;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public List<String> getSections() {
		return sections;
	}
	public void setSections(List<String> sections) {
		this.sections = sections;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public List<String> getEmailsCC() {
		return emailsCC;
	}
	public void setEmailsCC(List<String> emailsCC) {
		this.emailsCC = emailsCC;
	}
	public List<String> getEmailsBCC() {
		return emailsBCC;
	}
	public void setEmailsBCC(List<String> emailsBCC) {
		this.emailsBCC = emailsBCC;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getSubjectLabel() {
		return subjectLabel;
	}
	public void setSubjectLabel(String subjectLabel) {
		this.subjectLabel = subjectLabel;
	}
	public String getContentLabel() {
		return contentLabel;
	}
	public void setContentLabel(String contentLabel) {
		this.contentLabel = contentLabel;
	}
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
}

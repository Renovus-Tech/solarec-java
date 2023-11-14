package tech.renvous.solarec.util.db;

import java.util.Date;

import tech.renvous.solarec.util.StringUtil;

/**
 * Represents an element of a database table row. Has information about what must be done in order to 
 * keep the information persisted in the database.
 *
 */
public abstract class BaseDbVo {
	
	//--- Public constatns ----------------------
	/** Must do nothing in the database. When the object is created, it's <code>syncType</code> will be this one.*/
	public static final int SYNC_INIT			= 0;

	/** Must insert the object in the database.*/
	public static final int SYNC_INSERT			= 1;
	
	/** Must update the object in the database.*/
	public static final int SYNC_UPDATE			= 2;
	
	/** Must delete the object in the database.*/
	public static final int SYNC_DELETE			= 3;
	
	/** Must search the object in the database, to see if the object must be inserted or updated. */
	public static final int SYNC_INSERT_UPDATE	= 4;
	
	/** Must insert the object in the database, but avoid creation of audit registry. **/
	public static final int SYNC_INSERT_NO_AUDIT	= 5;
	
	/** Must update the object in the database, but avoid creation of audit registry. **/
	public static final int SYNC_UPDATE_NO_AUDIT	= 6;
	
	//--- Private attribtues --------------------
	private int syncType = BaseDbVo.SYNC_INIT;
	private Date auditDate;
	private String auditUser;

	//--- Abstract methods ----------------------
	public abstract boolean isSame(Object obj);

	//--- Getters and Setters -------------------
	public String getLblDeps() { return StringUtil.EMPTY_STRING; }
	
	//--- Getters and Setters -------------------
	public int getSyncType() {
		return this.syncType;
	}
	public void setSyncType(int syncType) {
		this.syncType = syncType;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
}

package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbUsersVo;

public class UsersVo extends DbUsersVo {
	
	//--- Flags ---------------------------------
	public static final int FLAG_RECEIVE_REPORT_BY_EMAIL		= 0;
	public static final int FLAG_RECEIVE_REPORT_BY_EMAIL_BCC	= 1;

	//--- Constructors --------------------------
	public UsersVo() {
	}

	public UsersVo(Integer usrId) {
		this.setPk(usrId);
	}

}
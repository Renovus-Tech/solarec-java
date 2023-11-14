package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseUsersDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.db.data.dao.wrapper.UsersRowWrapper;
import tech.renovus.solarec.db.data.vo.UsersVo;

@Repository
public class UsersDaoImpl extends BaseUsersDao implements UsersDao {
	
	//--- Private constants ---------------------
	private final static String SQL_FIND_BY_EMAIL						= "SELECT * FROM users WHERE usr_email = :usrEmail ";
	private final static String SQL_FIND_BY_UUID						= "SELECT * FROM users WHERE usr_pwd_reset_uuid = :usr_pwd_reset_uuid ";
	private final static String SQL_UPDATE_LOGIN_DATE					= "UPDATE users SET usr_date_login= :usr_date_login WHERE usr_id_auto = :usr_id_auto";
	private final static String SQL_GET_ALL_FOR_LOCATION_REPORT_TYPE	= "select u.* from location l, loc_user lu, loc_usr_rep_type lurp, users u where l.loc_id_auto = lu.loc_id and lu.cli_id = lurp.cli_id and lu.loc_id = lurp.loc_id and lu.usr_id = lurp.usr_id and lu.usr_id = u.usr_id_auto and l.loc_flags like '1%' and lurp.loc_usr_rep_type_flags like '1%' and lu.cli_id = :cli_id and lu.loc_id = :loc_id and lurp.rep_type_id = :rep_type_id";
	
	//--- Constructors --------------------------
	@Autowired public UsersDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public UsersVo findBy(String usrEmail) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_BY_EMAIL, 
					new MapSqlParameterSource()
						.addValue("usrEmail", usrEmail),
					UsersRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public void setLoginDate(Integer usrId, Date date) {
		this.jdbc.update(
			SQL_UPDATE_LOGIN_DATE, 
			new MapSqlParameterSource()
				.addValue("usr_date_login", date)
				.addValue("usr_id_auto", usrId)
		);
	}

	@Override public Collection<UsersVo> getAllForLocationReport(Integer cliId, Integer locId, Integer repTypeId) {
		 return this.jdbc.query(
				 SQL_GET_ALL_FOR_LOCATION_REPORT_TYPE,
				 new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("loc_id", locId)
					.addValue("rep_type_id", repTypeId),
				 UsersRowWrapper.getInstance()
			); 
	}

	@Override 	public UsersVo findByResetUuid(String uuid) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_BY_UUID, 
					new MapSqlParameterSource()
						.addValue("usr_pwd_reset_uuid", uuid),
					UsersRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}  
}

package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocUsrRepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocUsrRepTypeDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocUsrRepTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.LocUsrRepTypeVo;

@Repository
public class LocUsrRepTypeDaoImpl extends BaseLocUsrRepTypeDao implements LocUsrRepTypeDao {
	
	//--- Private properties --------------------
	private static final String SQL_FIND_ALL_FOR_USER	= "SELECT * FROM loc_usr_rep_type WHERE usr_id = :usr_id";
	
	//--- Constructors --------------------------
	@Autowired public LocUsrRepTypeDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
		
		this.SQL_INSERT += this.SQL_ON_CONFLICT_PK_UPDATE;
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<LocUsrRepTypeVo> getAllForUser(Integer usrId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_USER,
				new MapSqlParameterSource().addValue("usr_id", usrId),
				LocUsrRepTypeRowWrapper.getInstance()
			);
	}  

}

package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocAlertDao;
import tech.renovus.solarec.vo.db.data.LocAlertVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class LocAlertDaoImpl extends BaseLocAlertDao implements LocAlertDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public LocAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void insert(Collection<LocAlertVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		for (LocAlertVo vo : vos) if (vo != null) this.insert(vo);
	} 

}

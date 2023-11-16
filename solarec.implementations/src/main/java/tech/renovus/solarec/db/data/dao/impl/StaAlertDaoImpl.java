package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseStaAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaAlertDao;
import tech.renovus.solarec.vo.db.data.StaAlertVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class StaAlertDaoImpl extends BaseStaAlertDao implements StaAlertDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public StaAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void insert(Collection<StaAlertVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		for (StaAlertVo vo : vos) if (vo != null) this.insert(vo);
	} 
}

package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenAlertDao;
import tech.renovus.solarec.db.data.vo.GenAlertVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class GenAlertDaoImpl extends BaseGenAlertDao implements GenAlertDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public GenAlertDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void insert(Collection<GenAlertVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		for (GenAlertVo vo : vos) if (vo != null) this.insert(vo);
	} 
}

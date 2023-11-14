package tech.renovus.solarec.db.data.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDao;
import tech.renovus.solarec.db.data.vo.LocDataVo;
import tech.renvous.solarec.util.CollectionUtil;

@Repository
public class LocDataDaoImpl extends BaseLocDataDao implements LocDataDao {
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public LocDataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
		
		this.SQL_INSERT += this.SQL_ON_CONFLICT_PK_UPDATE;
	}
	
	//--- Overridden methods --------------------
//	@Override public void insert(Collection<LocDataVo> vos) {
//		if (CollectionUtil.isEmpty(vos)) return;
//		for (LocDataVo vo : vos) if (vo != null) this.insert(vo);
//	}  
	
	@Override public void insert(Collection<LocDataVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		List<MapSqlParameterSource> params = new ArrayList<MapSqlParameterSource>();
		for (LocDataVo vo : vos)
			params.add(this.createInsertMapSqlParameterSource(vo));
		this.jdbc.batchUpdate(SQL_INSERT, params.toArray(new MapSqlParameterSource[0]));
	}
}

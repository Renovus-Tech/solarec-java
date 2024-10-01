package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocationRowWrapper;
import tech.renovus.solarec.vo.db.data.LocUserVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

@Repository
public class LocationDaoImpl extends BaseLocationDao implements LocationDao {

	//--- Private constants ---------------------
	private final static String SQL_SELECT_ALL					= "SELECT * FROM location WHERE cli_id = :cli_id ORDER BY loc_code";
	private final static String SQL_SELECT_ALL_BY_TYPE			= "SELECT * FROM location WHERE cli_id = :cli_id AND loc_type = :loc_type ORDER BY loc_code";
	private final static String SQL_FIND_ALL_FOR_USER			= "SELECT l.* FROM location l, loc_user lu WHERE l.cli_id = lu.cli_id and l.loc_id_auto = lu.loc_id AND lu.cli_id = :cli_id AND lu.usr_id = :usr_id ORDER BY l.loc_name";
	private final static String SQL_FIND_ALL_FOR_USER_FOR_TYPE	= "SELECT l.* FROM location l, loc_user lu WHERE l.cli_id = lu.cli_id and l.loc_id_auto = lu.loc_id AND lu.cli_id = :cli_id AND lu.usr_id = :usr_id AND loc_type = :loc_type ORDER BY loc_code";
	private final static String SQL_FIND_ALL_FOR_USER_BY_ACCESS	= "SELECT l.* FROM location l, loc_user lu WHERE l.cli_id = lu.cli_id and l.loc_id_auto = lu.loc_id AND lu.cli_id = :cli_id AND lu.usr_id = :usr_id ORDER BY lu.cli_user_date_access DESC NULLS LAST";
	private final static String SQL_FIND_FOR_USER				= "SELECT l.* FROM location l, loc_user lu WHERE l.cli_id = lu.cli_id and l.loc_id_auto = lu.loc_id AND lu.cli_id = :cli_id AND lu.loc_id = :loc_id AND lu.usr_id = :usr_id";
	
	private final static String SQL_UPDATE_DATA_DATE_MAX 		= "update location set loc_data_date_max = (select max(data_date) from loc_data where loc_data.cli_id = location.cli_id and loc_data.loc_id = location.loc_id_auto)";
	private final static String SQL_UPDATE_DATA_DATE_MIN 		= "update location set loc_data_date_min = (select min(data_date) from loc_data where loc_data.cli_id = location.cli_id and loc_data.loc_id = location.loc_id_auto)";

	private final static String SQL_GET_ALL_FOR_REPORT				= "SELECT l.* FROM client c, location l WHERE c.cli_flags like '1%' AND c.cli_id_auto = l.cli_id AND l.loc_flags like '1%'";
	private final static String SQL_GET_ALL_FOR_ALERT_CALCULATION	= "SELECT l.* FROM client c, location l WHERE c.cli_flags like '_1%' AND c.cli_id_auto = l.cli_id AND l.loc_flags like '__1%'";
	
	//--- Private properties --------------------
	
	//--- Constructors --------------------------
	@Autowired public LocationDaoImpl(NamedParameterJdbcTemplate jdbc) {  
       super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<LocationVo> findAll(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_ALL, 
				new MapSqlParameterSource()
					.addValue(LocationVo.COLUMN_CLI_ID, cliId), 
				LocationRowWrapper.getInstance()
			);
	}
	
	@Override public Collection<LocationVo> findAll(Integer cliId, String locType) {
		return this.jdbc.query(
				SQL_SELECT_ALL_BY_TYPE, 
				new MapSqlParameterSource()
					.addValue(LocationVo.COLUMN_CLI_ID, cliId) 
					.addValue(LocationVo.COLUMN_LOC_TYPE, locType), 
				LocationRowWrapper.getInstance()
			);
	}

	@Override public Collection<LocationVo> findAllForUser(Integer cliId, Integer usrId, boolean sortedByAccess) {
		return this.jdbc.query(
				sortedByAccess ? SQL_FIND_ALL_FOR_USER_BY_ACCESS : SQL_FIND_ALL_FOR_USER, 
				new MapSqlParameterSource()
					.addValue(LocationVo.COLUMN_CLI_ID, cliId)
					.addValue(LocUserVo.COLUMN_USR_ID, usrId),
				LocationRowWrapper.getInstance()
			);
	}

	@Override public LocationVo findForUser(Integer usrId, Integer cliId, Integer locId) {
		try {
			return this.jdbc.queryForObject(SQL_FIND_FOR_USER, new MapSqlParameterSource()
					.addValue(LocUserVo.COLUMN_USR_ID, usrId) 
					.addValue(LocationVo.COLUMN_CLI_ID, cliId) 
					.addValue(LocUserVo.COLUMN_LOC_ID, locId), 
					LocationRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public void updateDataDateMaxMin() {
		this.jdbc.update( SQL_UPDATE_DATA_DATE_MAX, new MapSqlParameterSource() );
		this.jdbc.update( SQL_UPDATE_DATA_DATE_MIN, new MapSqlParameterSource() );
	}

	@Override public Collection<LocationVo> getAllForReport() {
		return this.jdbc.query(
				SQL_GET_ALL_FOR_REPORT,
				LocationRowWrapper.getInstance()
			);
	}

	@Override
	public Collection<LocationVo> findAllForUser(Integer cliId, Integer usrId, String locType) {
		return this.jdbc.query(
			SQL_FIND_ALL_FOR_USER_FOR_TYPE, 
			new MapSqlParameterSource()
				.addValue(LocationVo.COLUMN_CLI_ID, cliId)
				.addValue(LocUserVo.COLUMN_USR_ID, usrId)
				.addValue(LocationVo.COLUMN_LOC_TYPE, locType),
			LocationRowWrapper.getInstance()
		);
	}

	@Override
	public Collection<LocationVo> getAllForAlertCalculation() {
		return this.jdbc.query(
				SQL_GET_ALL_FOR_ALERT_CALCULATION,
				LocationRowWrapper.getInstance()
			);
	}

}

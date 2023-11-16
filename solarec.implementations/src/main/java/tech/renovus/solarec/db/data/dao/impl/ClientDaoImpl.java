package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.wrapper.ClientRowWrapper;
import tech.renovus.solarec.vo.db.data.ClientVo;

@Repository
public class ClientDaoImpl extends BaseClientDao implements ClientDao {
	
	//--- Private constants ---------------------
	private final static String SQL_FIND_BY_EMAIL				= "SELECT * FROM client WHERE lower(cli_name) = lower(:cliName) ";
	private final static String SQL_FIND_ALL_FOR_USER			= "SELECT c.* FROM client c, cli_user cu WHERE c.cli_id_auto = cu.cli_id AND cu.usr_id = :usrId ORDER BY c.cli_name";
	private final static String SQL_FIND_ALL_FOR_USER_BY_ACCESS	= "SELECT c.* FROM client c, cli_user cu WHERE c.cli_id_auto = cu.cli_id AND cu.usr_id = :usrId ORDER BY cu.cli_user_date_access DESC NULLS LAST";
	
	//--- Constructors --------------------------
	@Autowired public ClientDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public ClientVo findBy(String cliName) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_BY_EMAIL, 
					new MapSqlParameterSource()
						.addValue("cliName", cliName),
					ClientRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public Collection<ClientVo> findAllForUser(Integer usrId, boolean sortedByAccess) {
		return this.jdbc.query(
				sortedByAccess ? SQL_FIND_ALL_FOR_USER_BY_ACCESS : SQL_FIND_ALL_FOR_USER, 
				new MapSqlParameterSource()
					.addValue("usrId", usrId),
				ClientRowWrapper.getInstance()
			);
	}  

}

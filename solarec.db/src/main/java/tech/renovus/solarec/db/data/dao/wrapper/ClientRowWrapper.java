package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.ClientVo;

public class ClientRowWrapper implements RowMapper<ClientVo> {

	//--- Constructors --------------------------
	private ClientRowWrapper() {}
	private static ClientRowWrapper instance = new ClientRowWrapper();
	public static ClientRowWrapper getInstance() { return ClientRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliDemoDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCtrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliGmt(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliSecCode(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliCertProvData(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliNameLegal(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliNameAddress(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public ClientVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ClientVo vo = new ClientVo();

		vo.setCliId(this.getResultSetCliId(resultSet, ClientVo.COLUMN_CLI_ID));
		vo.setCliDemoDate(this.getResultSetCliDemoDate(resultSet, ClientVo.COLUMN_CLI_DEMO_DATE));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, ClientVo.COLUMN_DATA_DEF_ID));
		vo.setCtrId(this.getResultSetCtrId(resultSet, ClientVo.COLUMN_CTR_ID));
		vo.setCliFlags(this.getResultSetCliFlags(resultSet, ClientVo.COLUMN_CLI_FLAGS));
		vo.setCliGmt(this.getResultSetCliGmt(resultSet, ClientVo.COLUMN_CLI_GMT));
		vo.setCliSecCode(this.getResultSetCliSecCode(resultSet, ClientVo.COLUMN_CLI_SEC_CODE));
		vo.setCliCertProvData(this.getResultSetCliCertProvData(resultSet, ClientVo.COLUMN_CLI_CERT_PROV_DATA));
		vo.setCliName(this.getResultSetCliName(resultSet, ClientVo.COLUMN_CLI_NAME));
		vo.setCliNameLegal(this.getResultSetCliNameLegal(resultSet, ClientVo.COLUMN_CLI_NAME_LEGAL));
		vo.setCliNameAddress(this.getResultSetCliNameAddress(resultSet, ClientVo.COLUMN_CLI_NAME_ADDRESS));

		return vo;
	}
}
package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseGenNeighbourVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_GEN_ID = "gen_id";
	 public static final String COLUMN_GEN_ID_NEIGHBOUR = "gen_id_neighbour";
	 public static final String COLUMN_GEN_ID_NEIGHBOUR_POSITION = "gen_id_neighbour_position";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer genId;
	 private Integer genIdNeighbour;
	 private Integer genIdNeighbourPosition;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		if (this.genIdNeighbour == null) {
			return false;
		}
		if (this.genIdNeighbourPosition == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGenNeighbourVo)) return false;
		
		BaseGenNeighbourVo aObj = (BaseGenNeighbourVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genIdNeighbour,aObj.genIdNeighbour)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.genIdNeighbour != null) hashCode += this.genIdNeighbour.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGenNeighbourVo)) return false;
		
		BaseGenNeighbourVo aObj = (BaseGenNeighbourVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genIdNeighbour,aObj.genIdNeighbour)) {
			return false;
		}
		if (!ClassUtil.equals(this.genIdNeighbourPosition,aObj.genIdNeighbourPosition)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId, Integer genIdNeighbour) {
		this.cliId = cliId;
		this.genId = genId;
		this.genIdNeighbour = genIdNeighbour;
	}

	public void setPk(BaseGenNeighbourVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getGenId(), aVo.getGenIdNeighbour());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public Integer getGenIdNeighbour() {
		return this.genIdNeighbour;
	}
	public void setGenIdNeighbour(Integer genIdNeighbour) {
		this.genIdNeighbour = genIdNeighbour;
	}

	public Integer getGenIdNeighbourPosition() {
		return this.genIdNeighbourPosition;
	}
	public void setGenIdNeighbourPosition(Integer genIdNeighbourPosition) {
		this.genIdNeighbourPosition = genIdNeighbourPosition;
	}

}
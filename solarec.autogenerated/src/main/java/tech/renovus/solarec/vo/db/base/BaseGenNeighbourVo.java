package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseGenNeighbourVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_GEN_ID_NEIGHBOUR = "gen_id_neighbour";
	public static final String COLUMN_GEN_ID_NEIGHBOUR_POSITION = "gen_id_neighbour_position";


	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer genId;
	private @Getter @Setter Integer genIdNeighbour;
	private @Getter @Setter Integer genIdNeighbourPosition;

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

	//--- Overriden methods ---------------------
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
		if (! this.equals(obj)) return false;
		
		BaseGenNeighbourVo aObj = (BaseGenNeighbourVo) obj;
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
			this.setPk(aVo.cliId, aVo.genId, aVo.genIdNeighbour);
		}
	}

}
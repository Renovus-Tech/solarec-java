package tech.renovus.solarec.vo.db.data;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.relation.DbDocumentVo;

public class DocumentVo extends DbDocumentVo {

	//--- Flags ---------------------------------
	public static final int FLAG_IS_OPEN	= 0;
	
	//--- Private properties --------------------
	private MultipartFile filePart;
	private Integer forcedLocId;
	
	//--- Constructors --------------------------
	public DocumentVo() {
	}

	public DocumentVo(Integer cliId, Integer docId) {
		this.setPk(cliId, docId);
	}
	
	//--- Public methods ------------------------
	public DocLocationVo getLocation(Integer locId) {
		if (locId == null) return null;
		if (CollectionUtil.isEmpty(this.locations)) return null;
		
		for (DocLocationVo vo : this.locations) if (ClassUtil.equals(vo.getLocId(), locId)) return vo;
		return null;
	}

	public void add(LocationVo vo) {
		if (vo == null) return;
		if (this.locations == null) this.locations = new ArrayList<>(1);
		this.locations.add(new DocLocationVo(this, vo));
	}
	
	public void add(GeneratorVo vo) {
		if (vo == null) return;
		if (this.generators == null) this.generators = new ArrayList<>(1);
		this.generators.add(new DocGeneratorVo(this, vo));
	}
	
	public void add(StationVo vo) {
		if (vo == null) return;
		if (this.stations == null) this.stations = new ArrayList<>(1);
		this.stations.add(new DocStationVo(this, vo));
	}
	
	public Date getDocDateFromAdded() {
		return this.getDocDateFrom() != null ? this.getDocDateFrom() : this.getDocDateAdded();
	}
	
	//--- Getters and Setters -------------------
	public MultipartFile getFilePart() {
		return filePart;
	}
	public void setFilePart(MultipartFile filePart) {
		this.filePart = filePart;
	}

	public Integer getForcedLocId() {
		return forcedLocId;
	}

	public void setForcedLocId(Integer forcedLocId) {
		this.forcedLocId = forcedLocId;
	}
}
package tech.renovus.solarec.business.impl.email;

import tech.renovus.solarec.util.FileUtil;

public class EmailFile {

	//--- Private properties --------------------
	private String name;
	private String path;
	
	//--- Constructors --------------------------
	public EmailFile(String name, String path) {
		this.name = name;
		this.path = path;
		
		String extension = FileUtil.getExtension(this.path);
		if (! this.name.toLowerCase().endsWith(extension.toLowerCase())) this.name += extension;
	}
	
	//--- Getters and Setters -------------------
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
}

package com.guoke.model.meta;

public class FieldInfo {
	private String name;
	private String display;
	private String type;
	private String mode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public boolean requireMode(String mode) {
		String m = this.getMode();
		if(m==null||m.isEmpty()) return true;
		return m.contains(mode);
	}
}

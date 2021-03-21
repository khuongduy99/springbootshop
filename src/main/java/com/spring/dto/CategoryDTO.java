package com.spring.dto;

public class CategoryDTO extends AbstractDTO<CategoryDTO>{
	private String name, alias;
	private boolean isAccessory;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public boolean isAccessory() {
		return isAccessory;
	}
	public void setAccessory(boolean isAccessory) {
		this.isAccessory = isAccessory;
	}
	
}

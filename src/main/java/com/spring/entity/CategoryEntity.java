package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@Column
	private String isAccessory;
	
	@OneToMany(mappedBy = "category")
	private List<BrandEntity> listBrand;
	
	@OneToMany(mappedBy = "category")
	private List<ClassifyEntity> listClassify;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsAccessory() {
		return isAccessory;
	}

	public void setIsAccessory(String isAccessory) {
		this.isAccessory = isAccessory;
	}

	public List<BrandEntity> getListBrand() {
		return listBrand;
	}

	public void setListBrand(List<BrandEntity> listBrand) {
		this.listBrand = listBrand;
	}

	public List<ClassifyEntity> getListClassify() {
		return listClassify;
	}

	public void setListClassify(List<ClassifyEntity> listClassify) {
		this.listClassify = listClassify;
	}
	
}

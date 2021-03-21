package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@Column(columnDefinition = "boolean default false")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isAccessory = false;
	
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

	public boolean isAccessory() {
		return isAccessory;
	}

	public void setAccessory(boolean isAccessory) {
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

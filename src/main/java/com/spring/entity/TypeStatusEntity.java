package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "type_status")
public class TypeStatusEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "typeStatus")
	private List<StatusEntity> listStatus;
	

	public List<StatusEntity> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<StatusEntity> listStatus) {
		this.listStatus = listStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

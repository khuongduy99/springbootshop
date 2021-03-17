package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class StatusEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@ManyToOne
    @JoinColumn(name="type_id")
    private TypeStatusEntity typeStatus;
	
	public TypeStatusEntity getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(TypeStatusEntity typeStatus) {
		this.typeStatus = typeStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

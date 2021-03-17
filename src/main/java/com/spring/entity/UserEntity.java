package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
	
	@Column
	private String fullName;
	
	@Column
	private String email;
	
	@Column
	private String password;

	@OneToMany(mappedBy = "user")
	private List<ReviewEntity> listReview;
	
	@ManyToMany(mappedBy = "listUser")
    private List<RoleEntity> listRole = new ArrayList<RoleEntity>();

	public List<RoleEntity> getListRole() {
		return listRole;
	}

	public void setListRole(List<RoleEntity> listRole) {
		this.listRole = listRole;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ReviewEntity> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewEntity> listReview) {
		this.listReview = listReview;
	}
	
	
}

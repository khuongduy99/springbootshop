package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity {
	
	@Column
	private String fullName;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String gender;
	
	@Column
	private String address;
	
	@Column
	private String note;
	
	@Column
	private double total_money;

	@OneToMany(mappedBy = "bill")
	private List<BillDetailEntity> listBillDetail;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getTotal_money() {
		return total_money;
	}

	public void setTotal_money(double total_money) {
		this.total_money = total_money;
	}

	public List<BillDetailEntity> getListBillDetail() {
		return listBillDetail;
	}

	public void setListBillDetail(List<BillDetailEntity> listBillDetail) {
		this.listBillDetail = listBillDetail;
	}
	
	
}

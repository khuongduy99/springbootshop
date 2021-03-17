package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "filter")
public class FilterEntity extends BaseEntity {
	
	@Column
	private String name;

	@ManyToOne
    @JoinColumn(name="classify_id")
    private ClassifyEntity classify;
	
	@ManyToMany(mappedBy = "listFilter")
    private List<ProductEntity> listProduct = new ArrayList<ProductEntity>();
 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassifyEntity getClassify() {
		return classify;
	}

	public void setClassify(ClassifyEntity classify) {
		this.classify = classify;
	}

	public List<ProductEntity> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductEntity> listProduct) {
		this.listProduct = listProduct;
	}

}

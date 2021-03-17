package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class BrandEntity extends BaseEntity {
	
	@Column
	private String name;

	@ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;
	
	@OneToMany(mappedBy = "brand")
	private List<ProductEntity> listProduct;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<ProductEntity> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductEntity> listProduct) {
		this.listProduct = listProduct;
	}
	
	
	
}

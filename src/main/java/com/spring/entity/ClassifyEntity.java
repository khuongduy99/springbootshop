package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classify")
public class ClassifyEntity extends BaseEntity {
	
	@Column
	private String name;

	@ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;
	
	@OneToMany(mappedBy = "classify")
	private List<FilterEntity> listFilter;

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

	public List<FilterEntity> getListFilter() {
		return listFilter;
	}

	public void setListFilter(List<FilterEntity> listFilter) {
		this.listFilter = listFilter;
	}

}

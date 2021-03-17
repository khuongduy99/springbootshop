package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

	@Column
	private String name;

	@Column
	private double price;

	@Column
	private double sale;

	@Column
	private int qty;

	@Column(columnDefinition = "boolean default false")
	private boolean isNew;

	@Column(columnDefinition = "boolean default false")
	private double isHot;

	@Column
	private long views;

	@Column
	private int star;

	@Column(columnDefinition = "TEXT")
	private String promotions;

	@Column(columnDefinition = "TEXT")
	private String article;

	@Column(columnDefinition = "TEXT")
	private String specifications;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "filter_product", joinColumns = @JoinColumn(name = "filter_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<FilterEntity> listFilter = new ArrayList<FilterEntity>();

	@OneToMany(mappedBy = "product")
	private List<ImageOfProductEntity> listImage;
	
	@OneToMany(mappedBy = "product")
	private List<ReviewEntity> listReview;

	public String getName() {
		return name;
	}

	public List<ReviewEntity> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewEntity> listReview) {
		this.listReview = listReview;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public double getIsHot() {
		return isHot;
	}

	public void setIsHot(double isHot) {
		this.isHot = isHot;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getPromotions() {
		return promotions;
	}

	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

	public List<FilterEntity> getListFilter() {
		return listFilter;
	}

	public void setListFilter(List<FilterEntity> listFilter) {
		this.listFilter = listFilter;
	}

	public List<ImageOfProductEntity> getListImage() {
		return listImage;
	}

	public void setListImage(List<ImageOfProductEntity> listImage) {
		this.listImage = listImage;
	}
	
}

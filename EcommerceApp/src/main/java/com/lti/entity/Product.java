package com.lti.entity;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue	
	int productId;
	String productName;
	int stock;
	boolean approved;
	
	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	@ManyToOne
	@JoinColumn(name="category_Id")
	Category category;
	
	@OneToOne
	@JoinColumn(name="productDescription_Id")
	ProductDescription productDescription;
	
	
	@ManyToOne
	@JoinColumn(name="retailer_Id")
	Retailer retailer;
	
    @OneToOne(mappedBy = "product")
    OrderItem orderitems;
    
	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public ProductDescription getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}


	public Retailer getRetailer() {
		return retailer;
	}


	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}
	
	
	

}

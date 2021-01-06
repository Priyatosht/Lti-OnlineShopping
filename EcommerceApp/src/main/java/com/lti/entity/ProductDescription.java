package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductDescription {

	@Id
	@GeneratedValue

	int productDescriptionId;
	double productPrice;
	String productSize;
	String color;
	String productImg;
	String brand;
	String descriptiontext;

	@OneToOne(mappedBy = "productDescription")
	Product product;

	public int getProductDescriptionId() {
		return productDescriptionId;
	}

	public void setProductDescriptionId(int productDescriptionId) {
		this.productDescriptionId = productDescriptionId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescriptiontext() {
		return descriptiontext;
	}

	public void setDescriptiontext(String descriptiontext) {
		this.descriptiontext = descriptiontext;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}

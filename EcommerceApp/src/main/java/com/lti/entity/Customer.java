package com.lti.entity;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	
	int customerId;
	String customerName;
	String mobileNumber;
	String emailId;
	int age;
	String gender;
	String password;
	boolean approved;
	
	@OneToMany(mappedBy = "customer")
	List<Address> address;
	
	@OneToOne(mappedBy = "customer")
	Cart cart;
	
	@OneToMany(mappedBy = "customer")
	List<Order> order;
	
//	@OneToOne(mappedBy = "customer")
//	Wishlist wishlist;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

//	public Wishlist getWishlist() {
//		return wishlist;
//	}
//
//	public void setWishlist(Wishlist wishlist) {
//		this.wishlist = wishlist;
//	}
//	
	


}

package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_order")
public class Order {
	
	@Id
	@SequenceGenerator(name = "seq_order", initialValue = 301, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
	long orderId;
	
	@ManyToOne
	@JoinColumn(name="customer_Id")
	Customer customer;

	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<OrderItem> orderItem;


	public long getOrderId() {
		return orderId;
	}


	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<OrderItem> getOrderItem() {
		return orderItem;
	}


	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	
	

}

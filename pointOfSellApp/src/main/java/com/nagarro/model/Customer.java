package com.nagarro.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private long customerId;
	private String customerName;
	private String customerMail;
	private String customerMobile;
	
	@OneToMany
	private Set<Order> order = new HashSet<>();
	
	@OneToMany
	private Set<Cart> cart = new HashSet<>();
	
	/**
	 * @return the order
	 */
	public Set<Order> getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	/**
	 * @return the cart
	 */
	public Set<Cart> getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	public Customer() {
		
	}
	
	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerMail
	 */
	public String getCustomerMail() {
		return customerMail;
	}
	/**
	 * @param customerMail the customerMail to set
	 */
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	/**
	 * @return the customerMobile
	 */
	public String getCustomerMobile() {
		return customerMobile;
	}
	/**
	 * @param customerMobile the customerMobile to set
	 */
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	
	
}

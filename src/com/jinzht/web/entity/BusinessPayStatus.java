package com.jinzht.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * BusinessPayStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_pay_status", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"businessOrders"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BusinessPayStatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private String name;
	private Set<BusinessOrder> businessOrders = new HashSet<BusinessOrder>(0);

	// Constructors

	/** default constructor */
	public BusinessPayStatus() {
	}

	/** minimal constructor */
	public BusinessPayStatus(Integer statusId) {
		this.statusId = statusId;
	}

	/** full constructor */
	public BusinessPayStatus(Integer statusId, String name,
			Set<BusinessOrder> businessOrders) {
		this.statusId = statusId;
		this.name = name;
		this.businessOrders = businessOrders;
	}

	// Property accessors
	@Id
	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessPayStatus")
	public Set<BusinessOrder> getBusinessOrders() {
		return this.businessOrders;
	}

	public void setBusinessOrders(Set<BusinessOrder> businessOrders) {
		this.businessOrders = businessOrders;
	}

}
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
 * BusinessPayType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_pay_type", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"businessOrders"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class BusinessPayType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private Set<BusinessOrder> businessOrders = new HashSet<BusinessOrder>(0);

	// Constructors

	/** default constructor */
	public BusinessPayType() {
	}

	/** minimal constructor */
	public BusinessPayType(Integer typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public BusinessPayType(Integer typeId, String name,
			Set<BusinessOrder> businessOrders) {
		this.typeId = typeId;
		this.name = name;
		this.businessOrders = businessOrders;
	}

	// Property accessors
	@Id
	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessPayType")
	public Set<BusinessOrder> getBusinessOrders() {
		return this.businessOrders;
	}

	public void setBusinessOrders(Set<BusinessOrder> businessOrders) {
		this.businessOrders = businessOrders;
	}

}
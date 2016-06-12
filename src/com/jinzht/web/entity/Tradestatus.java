package com.jinzht.web.entity;
// default package

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
 * Tradestatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tradestatus", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"traderecords"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Tradestatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private String name;
	private Short isValid;
	private Set<Traderecord> traderecords = new HashSet<Traderecord>(0);

	// Constructors

	/** default constructor */
	public Tradestatus() {
	}

	/** minimal constructor */
	public Tradestatus(Integer statusId) {
		this.statusId = statusId;
	}

	/** full constructor */
	public Tradestatus(Integer statusId, String name, Short isValid,
			Set<Traderecord> traderecords) {
		this.statusId = statusId;
		this.name = name;
		this.isValid = isValid;
		this.traderecords = traderecords;
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

	@Column(name = "isValid")
	public Short getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tradestatus")
	public Set<Traderecord> getTraderecords() {
		return this.traderecords;
	}

	public void setTraderecords(Set<Traderecord> traderecords) {
		this.traderecords = traderecords;
	}

}
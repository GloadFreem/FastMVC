package com.jinzht.web.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tradetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tradetype", catalog = "jinzht2016")
public class Tradetype implements java.io.Serializable {

	// Fields

	private Integer tradeTypeId;
	private String name;
	private Boolean isvalid;
	private Set<Traderecord> traderecords = new HashSet<Traderecord>(0);

	// Constructors

	/** default constructor */
	public Tradetype() {
	}

	/** full constructor */
	public Tradetype(String name, Boolean isvalid, Set<Traderecord> traderecords) {
		this.name = name;
		this.isvalid = isvalid;
		this.traderecords = traderecords;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trade_type_id", unique = true, nullable = false)
	public Integer getTradeTypeId() {
		return this.tradeTypeId;
	}

	public void setTradeTypeId(Integer tradeTypeId) {
		this.tradeTypeId = tradeTypeId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isvalid")
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tradetype")
	public Set<Traderecord> getTraderecords() {
		return this.traderecords;
	}

	public void setTraderecords(Set<Traderecord> traderecords) {
		this.traderecords = traderecords;
	}

}
package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Traderecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "traderecord", catalog = "jinzht2016")
public class Traderecord implements java.io.Serializable {

	// Fields

	private Integer tradeId;
	private Users users;
	private Chargetype chargetype;
	private Tradetype tradetype;
	private Float mount;
	private Short tradeType;
	private Integer tradeDate;

	// Constructors

	/** default constructor */
	public Traderecord() {
	}

	/** minimal constructor */
	public Traderecord(Chargetype chargetype) {
		this.chargetype = chargetype;
	}

	/** full constructor */
	public Traderecord(Users users, Chargetype chargetype, Tradetype tradetype,
			Float mount, Short tradeType, Integer tradeDate) {
		this.users = users;
		this.chargetype = chargetype;
		this.tradetype = tradetype;
		this.mount = mount;
		this.tradeType = tradeType;
		this.tradeDate = tradeDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trade_id", unique = true, nullable = false)
	public Integer getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Chargetype getChargetype() {
		return this.chargetype;
	}

	public void setChargetype(Chargetype chargetype) {
		this.chargetype = chargetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trade_type_id")
	public Tradetype getTradetype() {
		return this.tradetype;
	}

	public void setTradetype(Tradetype tradetype) {
		this.tradetype = tradetype;
	}

	@Column(name = "mount", precision = 12, scale = 0)
	public Float getMount() {
		return this.mount;
	}

	public void setMount(Float mount) {
		this.mount = mount;
	}

	@Column(name = "trade_type")
	public Short getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(Short tradeType) {
		this.tradeType = tradeType;
	}

	@Column(name = "trade_date")
	public Integer getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Integer tradeDate) {
		this.tradeDate = tradeDate;
	}

}
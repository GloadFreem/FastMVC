package com.jinzht.web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Chargetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chargetype", catalog = "jinzht2016")
public class Chargetype implements java.io.Serializable {

	// Fields

	private Integer rechargeStatusId;
	private Integer name;
	private Timestamp isvalid;
	private Traderecord traderecord;

	// Constructors

	/** default constructor */
	public Chargetype() {
	}

	/** full constructor */
	public Chargetype(Integer name, Timestamp isvalid, Traderecord traderecord) {
		this.name = name;
		this.isvalid = isvalid;
		this.traderecord = traderecord;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "recharge_status_id", unique = true, nullable = false)
	public Integer getRechargeStatusId() {
		return this.rechargeStatusId;
	}

	public void setRechargeStatusId(Integer rechargeStatusId) {
		this.rechargeStatusId = rechargeStatusId;
	}

	@Column(name = "name")
	public Integer getName() {
		return this.name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	@Column(name = "isvalid", length = 0)
	public Timestamp getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Timestamp isvalid) {
		this.isvalid = isvalid;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "chargetype")
	public Traderecord getTraderecord() {
		return this.traderecord;
	}

	public void setTraderecord(Traderecord traderecord) {
		this.traderecord = traderecord;
	}

}
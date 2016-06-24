package com.jinzht.web.entity;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Traderecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "traderecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"Tradetype","users","chargetype"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Traderecord implements java.io.Serializable {

	// Fields

	private Integer tradeId;
	private Users users;
	private Tradetype tradetype;
	private Float mount;
	private Short tradeType;
	private Date tradeDate;
	private String tradeCode;
	private Tradestatus tradestatus;
	

	// Constructors

	/** default constructor */
	public Traderecord() {
	}


	/** full constructor */
	public Traderecord(Users users ,Tradetype tradetype,
			Float mount, Short tradeType,  Date tradeDate,Tradestatus tradestatus) {
		this.users = users;
		this.tradetype = tradetype;
		this.mount = mount;
		this.tradeType = tradeType;
		this.tradeDate = tradeDate;
		this.tradestatus = tradestatus;
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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trade_status_id")
	public Tradestatus getTradestatus() {
		return this.tradestatus;
	}

	public void setTradestatus(Tradestatus tradestatus) {
		this.tradestatus = tradestatus;
	}

}
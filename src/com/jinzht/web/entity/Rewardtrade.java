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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Rewardtrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rewardtrade", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"rewardsystem","readed","tradeType","rewardTradeId","tradeType","looper"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Rewardtrade implements java.io.Serializable {

	// Fields

	private Integer rewardTradeId;
	private Rewardtradetype rewardtradetype;
	private Rewardsystem rewardsystem;
	private Integer tradeType;
	private String desc;
	private Integer count;
	private Date tradeDate;
	private boolean readed;

	// Constructors

	/** default constructor */
	public Rewardtrade() {
	}

	/** full constructor */
	public Rewardtrade(Rewardtradetype rewardtradetype,
			Rewardsystem rewardsystem, Integer tradeType, Integer count,String desc,Date tradeDate,boolean readed) {
		this.rewardtradetype = rewardtradetype;
		this.rewardsystem = rewardsystem;
		this.tradeType = tradeType;
		this.count = count;
		this.desc = desc;
		this.tradeDate = tradeDate;
		this.readed = readed;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reward_trade_id", unique = true, nullable = false)
	public Integer getRewardTradeId() {
		return this.rewardTradeId;
	}

	public void setRewardTradeId(Integer rewardTradeId) {
		this.rewardTradeId = rewardTradeId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reward_type_id")
	public Rewardtradetype getRewardtradetype() {
		return this.rewardtradetype;
	}

	public void setRewardtradetype(Rewardtradetype rewardtradetype) {
		this.rewardtradetype = rewardtradetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reward_id")
	public Rewardsystem getRewardsystem() {
		return this.rewardsystem;
	}

	public void setRewardsystem(Rewardsystem rewardsystem) {
		this.rewardsystem = rewardsystem;
	}

	@Column(name = "trade_type")
	public Integer getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	@Column(name = "count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "trade_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name="readed")
	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	@Column(name="content")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
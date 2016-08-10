package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rewardtrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rewardtrade", catalog = "jinzht2016")
public class Rewardtrade implements java.io.Serializable {

	// Fields

	private Integer rewardTradeId;
	private Rewardtradetype rewardtradetype;
	private Rewardsystem rewardsystem;
	private Integer tradeType;
	private Integer count;

	// Constructors

	/** default constructor */
	public Rewardtrade() {
	}

	/** full constructor */
	public Rewardtrade(Rewardtradetype rewardtradetype,
			Rewardsystem rewardsystem, Integer tradeType, Integer count) {
		this.rewardtradetype = rewardtradetype;
		this.rewardsystem = rewardsystem;
		this.tradeType = tradeType;
		this.count = count;
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

	@ManyToOne(fetch = FetchType.LAZY)
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

}
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Rewardsystem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rewardsystem", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Rewardsystem implements java.io.Serializable {

	// Fields

	private Integer rewardId;
	private Users users;
	private Integer count;
	private Set<Rewardtrade> rewardtrades = new HashSet<Rewardtrade>(0);

	// Constructors

	/** default constructor */
	public Rewardsystem() {
	}

	/** full constructor */
	public Rewardsystem(Users users, Integer count,
			Set<Rewardtrade> rewardtrades) {
		this.users = users;
		this.count = count;
		this.rewardtrades = rewardtrades;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reward_id", unique = true, nullable = false)
	public Integer getRewardId() {
		return this.rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rewardsystem")
	public Set<Rewardtrade> getRewardtrades() {
		return this.rewardtrades;
	}

	public void setRewardtrades(Set<Rewardtrade> rewardtrades) {
		this.rewardtrades = rewardtrades;
	}

}
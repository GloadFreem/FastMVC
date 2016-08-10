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
 * Rewardtradetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rewardtradetype", catalog = "jinzht2016")
public class Rewardtradetype implements java.io.Serializable {

	// Fields

	private Integer rewardTypeId;
	private String name;
	private Set<Rewardtrade> rewardtrades = new HashSet<Rewardtrade>(0);

	// Constructors

	/** default constructor */
	public Rewardtradetype() {
	}

	/** full constructor */
	public Rewardtradetype(String name, Set<Rewardtrade> rewardtrades) {
		this.name = name;
		this.rewardtrades = rewardtrades;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reward_type_id", unique = true, nullable = false)
	public Integer getRewardTypeId() {
		return this.rewardTypeId;
	}

	public void setRewardTypeId(Integer rewardTypeId) {
		this.rewardTypeId = rewardTypeId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rewardtradetype")
	public Set<Rewardtrade> getRewardtrades() {
		return this.rewardtrades;
	}

	public void setRewardtrades(Set<Rewardtrade> rewardtrades) {
		this.rewardtrades = rewardtrades;
	}

}
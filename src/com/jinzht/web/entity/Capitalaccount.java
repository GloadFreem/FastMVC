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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Capitalaccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "capitalaccount", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Capitalaccount implements java.io.Serializable {

	// Fields

	private Integer capitalAccountId;
	private Users users;
	private Integer mount;
	private Integer usableMoney;
	private Integer unusableMoney;

	// Constructors

	/** default constructor */
	public Capitalaccount() {
	}

	/** full constructor */
	public Capitalaccount(Users users, Integer mount, Integer usableMoney,
			Integer unusableMoney) {
		this.users = users;
		this.mount = mount;
		this.usableMoney = usableMoney;
		this.unusableMoney = unusableMoney;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "capital_account_id", unique = true, nullable = false)
	public Integer getCapitalAccountId() {
		return this.capitalAccountId;
	}

	public void setCapitalAccountId(Integer capitalAccountId) {
		this.capitalAccountId = capitalAccountId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "mount")
	public Integer getMount() {
		return this.mount;
	}

	public void setMount(Integer mount) {
		this.mount = mount;
	}

	@Column(name = "usable_money")
	public Integer getUsableMoney() {
		return this.usableMoney;
	}

	public void setUsableMoney(Integer usableMoney) {
		this.usableMoney = usableMoney;
	}

	@Column(name = "unusable_money")
	public Integer getUnusableMoney() {
		return this.unusableMoney;
	}

	public void setUnusableMoney(Integer unusableMoney) {
		this.unusableMoney = unusableMoney;
	}

}
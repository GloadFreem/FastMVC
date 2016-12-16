package com.jinzht.web.entity;

import java.sql.Timestamp;
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

/**
 * Propertycharges entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "propertycharges", catalog = "ak_zhsq")
public class Propertycharges implements java.io.Serializable {

	// Fields

	private Integer chargeId;
	private User user;
	private String name;
	private Timestamp endDate;
	private Double price;
	private String status;
	private Set<Charge> charges = new HashSet<Charge>(0);

	// Constructors

	/** default constructor */
	public Propertycharges() {
	}

	/** full constructor */
	public Propertycharges(User user, String name, Timestamp endDate,
			Double price, String status, Set<Charge> charges) {
		this.user = user;
		this.name = name;
		this.endDate = endDate;
		this.price = price;
		this.status = status;
		this.charges = charges;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "charge_id", unique = true, nullable = false)
	public Integer getChargeId() {
		return this.chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "end_date", length = 19)
	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "propertycharges")
	public Set<Charge> getCharges() {
		return this.charges;
	}

	public void setCharges(Set<Charge> charges) {
		this.charges = charges;
	}

}
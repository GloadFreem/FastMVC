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
 * Charge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "charge", catalog = "ak_zhsq")
public class Charge implements java.io.Serializable {

	// Fields

	private Integer idcharge;
	private Propertycharges propertycharges;
	private Charges charges;
	private Double amount;
	private String extr;

	// Constructors

	/** default constructor */
	public Charge() {
	}

	/** full constructor */
	public Charge(Propertycharges propertycharges, Charges charges,
			Double amount, String extr) {
		this.propertycharges = propertycharges;
		this.charges = charges;
		this.amount = amount;
		this.extr = extr;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcharge", unique = true, nullable = false)
	public Integer getIdcharge() {
		return this.idcharge;
	}

	public void setIdcharge(Integer idcharge) {
		this.idcharge = idcharge;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "charge_id")
	public Propertycharges getPropertycharges() {
		return this.propertycharges;
	}

	public void setPropertycharges(Propertycharges propertycharges) {
		this.propertycharges = propertycharges;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detail_id")
	public Charges getCharges() {
		return this.charges;
	}

	public void setCharges(Charges charges) {
		this.charges = charges;
	}

	@Column(name = "amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "extr")
	public String getExtr() {
		return this.extr;
	}

	public void setExtr(String extr) {
		this.extr = extr;
	}

}
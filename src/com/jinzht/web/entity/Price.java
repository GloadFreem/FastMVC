package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Price entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "price", catalog = "ak_zhsq")
public class Price implements java.io.Serializable {

	// Fields

	private Integer priceId;
	private Short price;

	// Constructors

	/** default constructor */
	public Price() {
	}

	/** full constructor */
	public Price(Short price) {
		this.price = price;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "price_id", unique = true, nullable = false)
	public Integer getPriceId() {
		return this.priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	@Column(name = "price")
	public Short getPrice() {
		return this.price;
	}

	public void setPrice(Short price) {
		this.price = price;
	}

}
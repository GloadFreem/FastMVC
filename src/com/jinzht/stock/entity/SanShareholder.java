package com.jinzht.stock.entity;

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

/**
 * SanShareholder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "san_shareholder", catalog = "jinzht2016")
@JsonIgnoreProperties({"sanCompany"})
public class SanShareholder implements java.io.Serializable {

	// Fields

	private Integer id;
	private SanCompany sanCompany;
	private String name;
	private String stock;
	private String percent;

	// Constructors

	/** default constructor */
	public SanShareholder() {
	}

	/** full constructor */
	public SanShareholder(SanCompany sanCompany, String name, String stock,
			String percent) {
		this.sanCompany = sanCompany;
		this.name = name;
		this.stock = stock;
		this.percent = percent;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	public SanCompany getSanCompany() {
		return this.sanCompany;
	}

	public void setSanCompany(SanCompany sanCompany) {
		this.sanCompany = sanCompany;
	}

	@Column(name = "name", length = 16777215)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "stock")
	public String getStock() {
		return this.stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	@Column(name = "percent")
	public String getPercent() {
		return this.percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

}
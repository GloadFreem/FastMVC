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
 * SanProfit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "san_profit", catalog = "jinzht2016")
@JsonIgnoreProperties({"sanCompany"})
public class SanProfit implements java.io.Serializable {

	// Fields

	private Integer id;
	private SanCompany sanCompany;
	private String profit;
	private String year;

	// Constructors

	/** default constructor */
	public SanProfit() {
	}

	/** full constructor */
	public SanProfit(SanCompany sanCompany, String profit, String year) {
		this.sanCompany = sanCompany;
		this.profit = profit;
		this.year = year;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public SanCompany getSanCompany() {
		return this.sanCompany;
	}

	public void setSanCompany(SanCompany sanCompany) {
		this.sanCompany = sanCompany;
	}

	@Column(name = "profit", length = 45)
	public String getProfit() {
		return this.profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	@Column(name = "year", length = 45)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
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
 * SanIncome entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "san_income", catalog = "jinzht2016")
@JsonIgnoreProperties({"sanCompany"})
public class SanIncome implements java.io.Serializable {

	// Fields

	private Integer id;
	private SanCompany sanCompany;
	private String income;
	private String year;

	// Constructors

	/** default constructor */
	public SanIncome() {
	}

	/** full constructor */
	public SanIncome(SanCompany sanCompany, String income, String year) {
		this.sanCompany = sanCompany;
		this.income = income;
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

	@Column(name = "income", length = 45)
	public String getIncome() {
		return this.income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	@Column(name = "year", length = 45)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
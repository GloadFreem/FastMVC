package com.jinzht.web.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Autrhrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "autrhrecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"authentic"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Autrhrecord implements java.io.Serializable {

	// Fields

	private AutrhrecordId id;
	private Authentic authentic;
	private Date authDate;

	// Constructors

	/** default constructor */
	public Autrhrecord() {
	}

	/** full constructor */
	public Autrhrecord(AutrhrecordId id, Authentic authentic, Date authDate) {
		this.id = id;
		this.authentic = authentic;
		this.authDate = authDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "authRecordId", column = @Column(name = "auth_record_id", nullable = false)),
			@AttributeOverride(name = "companyName", column = @Column(name = "company_name", nullable = false)) })
	public AutrhrecordId getId() {
		return this.id;
	}

	public void setId(AutrhrecordId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_record_id", nullable = false, insertable = false, updatable = false)
	public Authentic getAuthentic() {
		return this.authentic;
	}

	public void setAuthentic(Authentic authentic) {
		this.authentic = authentic;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "auth_date", nullable = false, length = 0)
	public Date getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

}
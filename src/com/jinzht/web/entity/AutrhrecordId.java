package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * AutrhrecordId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
@JsonIgnoreProperties(value={""})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AutrhrecordId implements java.io.Serializable {

	// Fields

	private Integer authRecordId;
	private String companyName;

	// Constructors

	/** default constructor */
	public AutrhrecordId() {
	}

	/** full constructor */
	public AutrhrecordId(Integer authRecordId, String companyName) {
		this.authRecordId = authRecordId;
		this.companyName = companyName;
	}

	// Property accessors

	@Column(name = "auth_record_id", nullable = false)
	public Integer getAuthRecordId() {
		return this.authRecordId;
	}

	public void setAuthRecordId(Integer authRecordId) {
		this.authRecordId = authRecordId;
	}

	@Column(name = "company_name", nullable = false)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AutrhrecordId))
			return false;
		AutrhrecordId castOther = (AutrhrecordId) other;

		return ((this.getAuthRecordId() == castOther.getAuthRecordId()) || (this
				.getAuthRecordId() != null
				&& castOther.getAuthRecordId() != null && this
				.getAuthRecordId().equals(castOther.getAuthRecordId())))
				&& ((this.getCompanyName() == castOther.getCompanyName()) || (this
						.getCompanyName() != null
						&& castOther.getCompanyName() != null && this
						.getCompanyName().equals(castOther.getCompanyName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAuthRecordId() == null ? 0 : this.getAuthRecordId()
						.hashCode());
		result = 37
				* result
				+ (getCompanyName() == null ? 0 : this.getCompanyName()
						.hashCode());
		return result;
	}

}
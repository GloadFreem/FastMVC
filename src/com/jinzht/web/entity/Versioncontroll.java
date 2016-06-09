package com.jinzht.web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Versioncontroll entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "versioncontroll", catalog = "jinzht2016")
@JsonIgnoreProperties(value={""})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Versioncontroll implements java.io.Serializable {

	// Fields

	private Integer versionId;
	private String versionStr;
	private String content;
	private String url;
	private Short isForce;
	private Timestamp updateTime;
	private Short platform;

	// Constructors

	/** default constructor */
	public Versioncontroll() {
	}

	/** minimal constructor */
	public Versioncontroll(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/** full constructor */
	public Versioncontroll(String versionStr, String content, String url,
			Short isForce, Timestamp updateTime, Short platform) {
		this.versionStr = versionStr;
		this.content = content;
		this.url = url;
		this.isForce = isForce;
		this.updateTime = updateTime;
		this.platform = platform;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "version_id", unique = true, nullable = false)
	public Integer getVersionId() {
		return this.versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	@Column(name = "version_str", length = 45)
	public String getVersionStr() {
		return this.versionStr;
	}

	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "is_force")
	public Short getIsForce() {
		return this.isForce;
	}

	public void setIsForce(Short isForce) {
		this.isForce = isForce;
	}

	@Column(name = "update_time", nullable = false, length = 0)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "platform")
	public Short getPlatform() {
		return this.platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

}
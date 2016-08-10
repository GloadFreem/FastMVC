package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Businessplan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "businessplan", catalog = "jinzht2016")
public class Businessplan implements java.io.Serializable {

	// Fields

	private Integer buinessPlanId;
	private Project project;
	private String url;
	private String content;

	// Constructors

	/** default constructor */
	public Businessplan() {
	}

	/** minimal constructor */
	public Businessplan(Project project) {
		this.project = project;
	}

	/** full constructor */
	public Businessplan(Project project, String url, String content) {
		this.project = project;
		this.url = url;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "buiness_plan_id", unique = true, nullable = false)
	public Integer getBuinessPlanId() {
		return this.buinessPlanId;
	}

	public void setBuinessPlanId(Integer buinessPlanId) {
		this.buinessPlanId = buinessPlanId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
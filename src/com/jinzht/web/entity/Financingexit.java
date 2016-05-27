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
 * Financingexit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "financingexit", catalog = "jinzht2016")
public class Financingexit implements java.io.Serializable {

	// Fields

	private Integer financingExitId;
	private Project project;
	private String url;
	private Integer projectId;
	private String context;

	// Constructors

	/** default constructor */
	public Financingexit() {
	}

	/** minimal constructor */
	public Financingexit(Project project) {
		this.project = project;
	}

	/** full constructor */
	public Financingexit(Project project, String url, Integer projectId,
			String context) {
		this.project = project;
		this.url = url;
		this.projectId = projectId;
		this.context = context;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "financing_exit_id", unique = true, nullable = false)
	public Integer getFinancingExitId() {
		return this.financingExitId;
	}

	public void setFinancingExitId(Integer financingExitId) {
		this.financingExitId = financingExitId;
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

	@Column(name = "project_id")
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "context")
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
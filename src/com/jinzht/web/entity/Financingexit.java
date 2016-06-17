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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Financingexit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "financingexit", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Financingexit implements java.io.Serializable {

	// Fields

	private Integer financingExitId;
	private Project project;
	private String url;
	private Integer projectId;
	private String content;
	private String icon;

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
			String content,String icon) {
		this.project = project;
		this.url = url;
		this.projectId = projectId;
		this.content = content;
		this.icon = icon;
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

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="icon")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
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
 * Financialstanding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "financialstanding", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project","users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Financialstanding implements java.io.Serializable {

	// Fields

	private Integer financeId;
	private Project project;
	private Integer projectId;
	private String url;
	private String content;
	private String icon;

	// Constructors

	/** default constructor */
	public Financialstanding() {
	}

	/** minimal constructor */
	public Financialstanding(Project project) {
		this.project = project;
	}

	/** full constructor */
	public Financialstanding(Project project, Integer projectId, String url,
			String content,String icon) {
		this.project = project;
		this.projectId = projectId;
		this.url = url;
		this.content = content;
		this.icon = icon;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "finance_id", unique = true, nullable = false)
	public Integer getFinanceId() {
		return this.financeId;
	}

	public void setFinanceId(Integer financeId) {
		this.financeId = financeId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "project_id")
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
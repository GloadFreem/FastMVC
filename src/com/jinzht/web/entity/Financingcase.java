package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Financingcase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "financingcase", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Financingcase implements java.io.Serializable {

	// Fields

	private Integer financingCaseId;
	private Project project;
	private String url;
	private String content;
	private String icon;

	// Constructors

	/** default constructor */
	public Financingcase() {
	}


	/** full constructor */
	public Financingcase(Project project, String url, String content,String icon) {
		this.project = project;
		this.url = url;
		this.content = content;
		this.setIcon(icon);
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "financing_case_id", unique = true, nullable = false)
	public Integer getFinancingCaseId() {
		return this.financingCaseId;
	}

	public void setFinancingCaseId(Integer financingCaseId) {
		this.financingCaseId = financingCaseId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
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
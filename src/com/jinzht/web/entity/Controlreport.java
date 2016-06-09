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
 * Controlreport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "controlreport", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Controlreport implements java.io.Serializable {

	// Fields

	private Integer reportId;
	private Project project;
	private String url;
	private String content;
	private Integer projectId;

	// Constructors

	/** default constructor */
	public Controlreport() {
	}

	/** minimal constructor */
	public Controlreport(Project project) {
		this.project = project;
	}

	/** full constructor */
	public Controlreport(Project project, String url, String content,
			Integer projectId) {
		this.project = project;
		this.url = url;
		this.content = content;
		this.projectId = projectId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "report_id", unique = true, nullable = false)
	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
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

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "project_id")
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
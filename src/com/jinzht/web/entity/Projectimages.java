package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Projectimages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "projectimages", catalog = "jinzht2016")
public class Projectimages implements java.io.Serializable {

	// Fields

	private Integer projectImageId;
	private Project project;
	private String imageUrl;

	// Constructors

	/** default constructor */
	public Projectimages() {
	}

	/** full constructor */
	public Projectimages(Project project, String imageUrl) {
		this.project = project;
		this.imageUrl = imageUrl;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_image_id", unique = true, nullable = false)
	public Integer getProjectImageId() {
		return this.projectImageId;
	}

	public void setProjectImageId(Integer projectImageId) {
		this.projectImageId = projectImageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
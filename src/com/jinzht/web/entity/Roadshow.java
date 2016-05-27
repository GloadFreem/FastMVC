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
 * Roadshow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roadshow", catalog = "jinzht2016")
public class Roadshow implements java.io.Serializable {

	// Fields

	private Integer roadShowId;
	private Project project;
	private Roadshowplan roadshowplan;

	// Constructors

	/** default constructor */
	public Roadshow() {
	}

	/** full constructor */
	public Roadshow(Project project, Roadshowplan roadshowplan) {
		this.project = project;
		this.roadshowplan = roadshowplan;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "road_show_id", unique = true, nullable = false)
	public Integer getRoadShowId() {
		return this.roadShowId;
	}

	public void setRoadShowId(Integer roadShowId) {
		this.roadShowId = roadShowId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id")
	public Roadshowplan getRoadshowplan() {
		return this.roadshowplan;
	}

	public void setRoadshowplan(Roadshowplan roadshowplan) {
		this.roadshowplan = roadshowplan;
	}

}
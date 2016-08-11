package com.jinzht.web.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Scene entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "scene", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Scene implements java.io.Serializable {

	// Fields

	private Integer sceneId;
	private Project project;
	private String audioPath;
	private long totlalTime;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Set<Scenecomment> scenecomments = new HashSet<Scenecomment>(0);
	private Set<Audiorecord> audiorecords = new HashSet<Audiorecord>(0);

	// Constructors

		/** default constructor */
		public Scene() {
		}

		/** full constructor */
		public Scene(Project project, String audioPath, long totlalTime,
				Timestamp beginTime, Timestamp endTime,
				Set<Scenecomment> scenecomments, Set<Audiorecord> audiorecords) {
			this.project = project;
			this.audioPath = audioPath;
			this.totlalTime = totlalTime;
			this.beginTime = beginTime;
			this.endTime = endTime;
			this.scenecomments = scenecomments;
			this.audiorecords = audiorecords;
		}

		// Property accessors
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "scene_id", unique = true, nullable = false)
		public Integer getSceneId() {
			return this.sceneId;
		}

		public void setSceneId(Integer sceneId) {
			this.sceneId = sceneId;
		}

		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "project_id")
		public Project getProject() {
			return this.project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		@Column(name = "audio_path")
		public String getAudioPath() {
			return this.audioPath;
		}

		public void setAudioPath(String audioPath) {
			this.audioPath = audioPath;
		}

		@Column(name = "totlal_time")
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")

		public long getTotlalTime() {
			return this.totlalTime;
		}

		public void setTotlalTime(long totlalTime) {
			this.totlalTime = totlalTime;
		}

		@Column(name = "begin_time", length = 19)
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")

		public Date getBeginTime() {
			return this.beginTime;
		}

		public void setBeginTime(Timestamp beginTime) {
			this.beginTime = beginTime;
		}

		@Column(name = "end_time", length = 19)
		public Timestamp getEndTime() {
			return this.endTime;
		}

		public void setEndTime(Timestamp endTime) {
			this.endTime = endTime;
		}

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "scene")
		public Set<Scenecomment> getScenecomments() {
			return this.scenecomments;
		}

		public void setScenecomments(Set<Scenecomment> scenecomments) {
			this.scenecomments = scenecomments;
		}

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "scene")
		public Set<Audiorecord> getAudiorecords() {
			return this.audiorecords;
		}

		public void setAudiorecords(Set<Audiorecord> audiorecords) {
			this.audiorecords = audiorecords;
		}

}
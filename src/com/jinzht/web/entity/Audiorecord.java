package com.jinzht.web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Audiorecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "audiorecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"scene"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Audiorecord implements java.io.Serializable {

	// Fields

	private Integer playId;
	private Scene scene;
	private Integer playTime;
	private String imageUrl;

	// Constructors

	/** default constructor */
	public Audiorecord() {
	}

	/** full constructor */
	public Audiorecord(Scene scene, Integer playTime, String imageUrl) {
		this.scene = scene;
		this.playTime = playTime;
		this.imageUrl = imageUrl;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "play_id", unique = true, nullable = false)
	public Integer getPlayId() {
		return this.playId;
	}

	public void setPlayId(Integer playId) {
		this.playId = playId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Column(name = "play_time")
	public Integer getPlayTime() {
		return this.playTime;
	}

	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
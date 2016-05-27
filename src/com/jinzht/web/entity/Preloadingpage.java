package com.jinzht.web.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Preloadingpage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "preloadingpage", catalog = "jinzht2016")
public class Preloadingpage implements java.io.Serializable {

	// Fields

	private Integer startPageId;
	private String description;
	private String imagePath;
	private Timestamp updateTime;
	private Timestamp showTime;
	private Short platform;

	// Constructors

	/** default constructor */
	public Preloadingpage() {
	}

	/** minimal constructor */
	public Preloadingpage(Timestamp updateTime, Timestamp showTime) {
		this.updateTime = updateTime;
		this.showTime = showTime;
	}

	/** full constructor */
	public Preloadingpage(String description, String imagePath,
			Timestamp updateTime, Timestamp showTime, Short platform) {
		this.description = description;
		this.imagePath = imagePath;
		this.updateTime = updateTime;
		this.showTime = showTime;
		this.platform = platform;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "start_page_id", unique = true, nullable = false)
	public Integer getStartPageId() {
		return this.startPageId;
	}

	public void setStartPageId(Integer startPageId) {
		this.startPageId = startPageId;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image_path", length = 200)
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "update_time", nullable = false, length = 0)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "show_time", nullable = false, length = 0)
	public Timestamp getShowTime() {
		return this.showTime;
	}

	public void setShowTime(Timestamp showTime) {
		this.showTime = showTime;
	}

	@Column(name = "platform")
	public Short getPlatform() {
		return this.platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

}
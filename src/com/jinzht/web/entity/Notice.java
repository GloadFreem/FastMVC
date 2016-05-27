package com.jinzht.web.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notice", catalog = "jinzht2016")
public class Notice implements java.io.Serializable {

	// Fields

	private Integer noticeId;
	private String description;
	private String noticeType;
	private Timestamp nodiceTime;
	private Short platform;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** full constructor */
	public Notice(String description, String noticeType, Timestamp nodiceTime,
			Short platform) {
		this.description = description;
		this.noticeType = noticeType;
		this.nodiceTime = nodiceTime;
		this.platform = platform;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "notice_id", unique = true, nullable = false)
	public Integer getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "notice_type", length = 200)
	public String getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	@Column(name = "nodice_time", length = 0)
	public Timestamp getNodiceTime() {
		return this.nodiceTime;
	}

	public void setNodiceTime(Timestamp nodiceTime) {
		this.nodiceTime = nodiceTime;
	}

	@Column(name = "platform")
	public Short getPlatform() {
		return this.platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

}
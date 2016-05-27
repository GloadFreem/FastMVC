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

/**
 * Share entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share", catalog = "jinzht2016")
public class Share implements java.io.Serializable {

	// Fields

	private Integer shareId;
	private Sharetype sharetype;
	private Timestamp shareDate;
	private String content;
	private String url;

	// Constructors

	/** default constructor */
	public Share() {
	}

	/** full constructor */
	public Share(Sharetype sharetype, Timestamp shareDate, String content,
			String url) {
		this.sharetype = sharetype;
		this.shareDate = shareDate;
		this.content = content;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "share_id", unique = true, nullable = false)
	public Integer getShareId() {
		return this.shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "share_type_id")
	public Sharetype getSharetype() {
		return this.sharetype;
	}

	public void setSharetype(Sharetype sharetype) {
		this.sharetype = sharetype;
	}

	@Column(name = "share_date", length = 0)
	public Timestamp getShareDate() {
		return this.shareDate;
	}

	public void setShareDate(Timestamp shareDate) {
		this.shareDate = shareDate;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
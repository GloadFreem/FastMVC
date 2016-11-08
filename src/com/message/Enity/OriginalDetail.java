package com.message.Enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * OriginalDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "original_detail", catalog = "jinzht2016")
@JsonIgnoreProperties({"original"})
public class OriginalDetail implements java.io.Serializable {

	// Fields

	private Integer detailId;
	private Original original;
	private String content;
	private String source;
	private String sourceUrl;

	// Constructors

	/** default constructor */
	public OriginalDetail() {
	}

	/** full constructor */
	public OriginalDetail(Original original, String content, String source,
			String sourceUrl) {
		this.original = original;
		this.content = content;
		this.source = source;
		this.sourceUrl = sourceUrl;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "detail_id", unique = true, nullable = false)
	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info_id")
	public Original getOriginal() {
		return this.original;
	}

	public void setOriginal(Original original) {
		this.original = original;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "source", length = 65535)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "source_url", length = 65535)
	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

}
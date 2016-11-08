package com.jinzht.web.filter;
// default package

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jinzht.web.entity.Contenttype;

/**
 * Weburlrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "weburlrecord", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"content","tag"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Weburlrecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private Contenttype contenttype;
	private String title;
	private String tag;
	private String image;
	private String orignal;
	private String url;
	private boolean flag;
	private String content;
	private Date createDate;

	// Constructors

	/** default constructor */
	public Weburlrecord() {
	}

	/** full constructor */
	public Weburlrecord(Contenttype contenttype, String title, String tag,
			String url, String content, Date createDate) {
		this.contenttype = contenttype;
		this.title = title;
		this.tag = tag;
		this.url = url;
		this.content = content;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "record_id", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	public Contenttype getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(Contenttype contenttype) {
		this.contenttype = contenttype;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "tag", nullable = false, length = 45)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "url", nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_date", nullable = false, length = 19)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" ) 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getOrignal() {
		return orignal;
	}

	public void setOrignal(String orignal) {
		this.orignal = orignal;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
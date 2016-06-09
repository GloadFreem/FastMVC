package com.jinzht.web.entity;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OrderBy;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Publiccontent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "publiccontent", catalog = "jinzht2016")
//@JsonIgnoreProperties(value={"users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Publiccontent implements java.io.Serializable {

	// Fields

	private Integer publicContentId;
	private boolean flag = false;
	private Users users;
	private String content;
	private Date publicDate;
	private Set<Contentimages> contentimageses = new HashSet<Contentimages>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Contentprise> contentprises = new HashSet<Contentprise>(0);
	private Integer shareCount=0; //转发数
	private Integer commentCount=0; //评论数
	private Integer priseCount=0;//点赞数
	
	// Constructors

	/** default constructor */
	public Publiccontent() {
	}

	/** full constructor */
	public Publiccontent(Users users, String content,
			Set<Contentimages> contentimageses, Set<Comment> comments,
			Set<Contentprise> contentprises,Date publicDate) {
		this.users = users;
		this.content = content;
		this.contentimageses = contentimageses;
		this.comments = comments;
		this.contentprises = contentprises;
		this.publicDate = publicDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "public_content_id", unique = true, nullable = false)
	public Integer getPublicContentId() {
		return this.publicContentId;
	}

	public void setPublicContentId(Integer publicContentId) {
		this.publicContentId = publicContentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	@OrderBy("imageId desc")
	public Set<Contentimages> getContentimageses() {
		return this.contentimageses;
	}

	public void setContentimageses(Set<Contentimages> contentimageses) {
		this.contentimageses = contentimageses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	@OrderBy("commentId desc")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publiccontent")
	@OrderBy("priseId desc")
	public Set<Contentprise> getContentprises() {
		return this.contentprises;
	}

	public void setContentprises(Set<Contentprise> contentprises) {
		this.contentprises = contentprises;
	}

	public Date getPublicDate() {
		return publicDate;
	}

	@Column(name = "public_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getPriseCount() {
		return priseCount;
	}

	public void setPriseCount(Integer priseCount) {
		this.priseCount = priseCount;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
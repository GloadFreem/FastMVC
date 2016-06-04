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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"publiccontent"})
public class Comment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Users usersByUserId;
	private Users usersByAtUserId;
	private Publiccontent publiccontent;
	private String content;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(Users usersByUserId, Users usersByAtUserId,
			Publiccontent publiccontent, String content) {
		this.usersByUserId = usersByUserId;
		this.usersByAtUserId = usersByAtUserId;
		this.publiccontent = publiccontent;
		this.content = content;
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsersByUserId() {
		return this.usersByUserId;
	}

	public void setUsersByUserId(Users usersByUserId) {
		this.usersByUserId = usersByUserId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "at_user_id")
	public Users getUsersByAtUserId() {
		return this.usersByAtUserId;
	}

	public void setUsersByAtUserId(Users usersByAtUserId) {
		this.usersByAtUserId = usersByAtUserId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "public_content_id")
	public Publiccontent getPubliccontent() {
		return this.publiccontent;
	}

	public void setPubliccontent(Publiccontent publiccontent) {
		this.publiccontent = publiccontent;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
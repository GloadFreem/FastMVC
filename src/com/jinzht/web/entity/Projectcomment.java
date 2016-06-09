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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Projectcomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "projectcomment", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"project","users"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Projectcomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Project project;
	private String content;
	private Users users;

	// Constructors

	/** default constructor */
	public Projectcomment() {
	}

	/** full constructor */
	public Projectcomment(Project project, String content) {
		this.project = project;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
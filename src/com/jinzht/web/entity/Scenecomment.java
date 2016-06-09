package com.jinzht.web.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Scenecomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "scenecomment", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"users","scene"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Scenecomment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Users users;
	private Scene scene;
	private String content;
	private Short isvalid;

	// Constructors

	/** default constructor */
	public Scenecomment() {
	}

	/** minimal constructor */
	public Scenecomment(Integer commentId) {
		this.commentId = commentId;
	}

	/** full constructor */
	public Scenecomment(Integer commentId, Users users, Scene scene,
			String content, Short isvalid) {
		this.commentId = commentId;
		this.users = users;
		this.scene = scene;
		this.content = content;
		this.isvalid = isvalid;
	}

	// Property accessors
	@Id
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "isvalid")
	public Short getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

}
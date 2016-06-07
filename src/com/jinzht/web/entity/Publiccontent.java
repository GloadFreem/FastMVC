package com.jinzht.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Publiccontent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "publiccontent", catalog = "jinzht2016")
public class Publiccontent implements java.io.Serializable {

	// Fields

	private Integer publicContentId;
	private Users users;
	private String content;
	private Set<Contentimages> contentimageses = new HashSet<Contentimages>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Contentprise> contentprises = new HashSet<Contentprise>(0);

	// Constructors

	/** default constructor */
	public Publiccontent() {
	}

	/** minimal constructor */
	public Publiccontent(Users users) {
		this.users = users;
	}

	/** full constructor */
	public Publiccontent(Users users, String content,
			Set<Contentimages> contentimageses, Set<Comment> comments,
			Set<Contentprise> contentprises) {
		this.users = users;
		this.content = content;
		this.contentimageses = contentimageses;
		this.comments = comments;
		this.contentprises = contentprises;
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

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publiccontent")
	public Set<Contentimages> getContentimageses() {
		return this.contentimageses;
	}

	public void setContentimageses(Set<Contentimages> contentimageses) {
		this.contentimageses = contentimageses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publiccontent")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publiccontent")
	public Set<Contentprise> getContentprises() {
		return this.contentprises;
	}

	public void setContentprises(Set<Contentprise> contentprises) {
		this.contentprises = contentprises;
	}

}
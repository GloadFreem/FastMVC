package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProjectRange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project_range", catalog = "jinzht2016")
public class ProjectRange implements java.io.Serializable {

	// Fields

	private Integer idRange;
	private String desc;
	private Integer from;
	private Integer to;
	private String key;

	// Constructors

	/** default constructor */
	public ProjectRange() {
	}

	/** full constructor */
	public ProjectRange(String desc, Integer from, Integer to, String key) {
		this.desc = desc;
		this.from = from;
		this.to = to;
		this.key = key;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_range", unique = true, nullable = false)
	public Integer getIdRange() {
		return this.idRange;
	}

	public void setIdRange(Integer idRange) {
		this.idRange = idRange;
	}

	@Column(name = "desc")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "from")
	public Integer getFrom() {
		return this.from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	@Column(name = "to")
	public Integer getTo() {
		return this.to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	@Column(name = "key", length = 45)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}